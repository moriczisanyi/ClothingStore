package presenter;

import model.Magazin;
import model.Produs;
import model.ProdusPersistenta;
import model.Raport;
import view.GUIAutentificare;
import view.IAngajatView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;


public class PAngajat {
	private IAngajatView angajat_v;
	public PAngajat(IAngajatView ang)
	{
		this.angajat_v=ang;
	}
	
	
	
	
	public void Logout()
	{
		new GUIAutentificare();
	}
	public String accesImagine()
	{
		ProdusPersistenta angPers = new ProdusPersistenta();
		if(!(angajat_v.boxProdusText().contentEquals("")) )
		{
			String[] splitter= angajat_v.boxProdusText().split("-");
			Produs produs= new Produs(splitter[0], splitter[1]);
			return angPers.accesImagine(produs, angajat_v.numeMagazinText());
		}
		return null;
	}
	public boolean generareRaportCSV()
	{
		Raport raport = new Raport();
		return raport.generareRaportCSV(angajat_v.numeMagazinText());
	}
	public boolean generareRaportJson()
	{
		Raport raport = new Raport();
		return raport.generareRaportJson(angajat_v.numeMagazinText());
	}
	public ArrayList<String> accesListaProduse()
	{
		ProdusPersistenta angPers = new ProdusPersistenta();
		return angPers.accesListaProduse(angajat_v.numeMagazinText());
	}
	public Map<String,Long> statisticaProducatori()
	{
		ProdusPersistenta angPers = new ProdusPersistenta();
		ArrayList<String> dateProdus=angPers.accesListaProduse(angajat_v.numeMagazinText());
		ArrayList<String> dateProducator= new ArrayList<String>();
		for(String item: dateProdus)
		{
			String[] splitter= item.split("-");
			dateProducator.add(splitter[1]);
		}
		Map<String,Long> rez = dateProducator.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		return rez;
	}
	public Map<String,Long> statisticaPret()
	{
		ProdusPersistenta angPers = new ProdusPersistenta();
		ArrayList<String> datePret=angPers.accesListaPreturi(angajat_v.numeMagazinText());
		ArrayList<String> statistica= new ArrayList<String>();
		for(String item: datePret)
		{
			double pret = Double.parseDouble(item);
			if( 0<= pret && pret < 10)
			{
				statistica.add("0.00-9.99");
			}else if(10 <= pret && pret <20)
			{
				statistica.add("10.00-19.99");
			}else if(20 <= pret && pret <30)
			{
				statistica.add("20.00-29.99");
			}else if(30 <= pret && pret <40)
			{
				statistica.add("30.00-39.99");
			}else if(40 <= pret && pret <50)
			{
				statistica.add("40.00-49.99");
			}else statistica.add(">= 50");
			
		}
		Map<String,Long> rez = statistica.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		return rez;
	}
	
	public Map<String,Long> statisticaDisponibilitate()
	{
		ProdusPersistenta angPers = new ProdusPersistenta();
		ArrayList<String> dateDisponibilitate=angPers.accesListaDisponibilitate(angajat_v.numeMagazinText());
		ArrayList<String> statistica= new ArrayList<String>();
		for(String item: dateDisponibilitate)
		{
			int stock = Integer.parseInt(item);
			if( 0<= stock && stock < 10)
			{
				statistica.add("0-9");
			}else if(10 <= stock && stock <20)
			{
				statistica.add("10-19");
			}else if(20 <= stock && stock <30)
			{
				statistica.add("20-29");
			}else if(30 <= stock && stock <40)
			{
				statistica.add("30-39");
			}else if(40 <= stock && stock <50)
			{
				statistica.add("40-49");
			}else statistica.add(">= 50");
			
		}
		Map<String,Long> rez = statistica.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		return rez;
	}
	
	public ArrayList<String> accesListaProducatori()
	{
		ProdusPersistenta angPers = new ProdusPersistenta();
		ArrayList<String> dateProdus=angPers.accesListaProduse(angajat_v.numeMagazinText());
		ArrayList<String> dateProducator= new ArrayList<String>();
		dateProducator.add("fara filtru");
		if(dateProdus.size()==1)
			return dateProducator;
		for(String item: dateProdus)
		{
			String[] splitter= item.split("-");
			dateProducator.add(splitter[1]);
		}
		dateProducator= new ArrayList<String>(dateProducator.stream().distinct().collect(Collectors.toList()));
		return dateProducator;
	}
	
	public ArrayList<String> accesListaProduseListare()
	{
		ProdusPersistenta angPers = new ProdusPersistenta();
		String filtruPret= angajat_v.boxPretText();
		String filtruProducator = angajat_v.boxProducatorText();
		String filtruDisponibilitate = angajat_v.boxDisponibilitateText();
		if(filtruPret.equalsIgnoreCase("fara filtru")) filtruPret=null;
		if(filtruProducator.equalsIgnoreCase("fara filtru")) filtruProducator=null;
		if(filtruDisponibilitate.equalsIgnoreCase("fara filtru")) filtruDisponibilitate=null;
		return angPers.accesListaProduseListare(angajat_v.numeMagazinText(), filtruProducator, filtruPret, filtruDisponibilitate);
	}
	public boolean adaugare()
	{
		String numeProdus=angajat_v.numeText();
		String producator=angajat_v.producatorText();
		String pret=angajat_v.pretText();
		String disponibilitate=angajat_v.disponibilitateText();
		String numeMagazin=angajat_v.numeMagazinText();
		String fisierImagine=angajat_v.adaugaImagineText();
		
		if(numeProdus.contentEquals("") || producator.contentEquals("") || pret.contentEquals("") || disponibilitate.contentEquals("") || fisierImagine==null)
		{
			return false;
		}

		ProdusPersistenta prodPers= new ProdusPersistenta();
		try
		{
			Double.parseDouble(pret);
		}
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null, "Eroare: formatul pretului trebuie sa fie x.y","Error", JOptionPane.ERROR_MESSAGE);
		}
		Produs produs= new Produs(numeProdus,producator,Double.parseDouble(pret),Integer.parseInt(disponibilitate),fisierImagine);
		try {
			prodPers.salvare(produs, numeMagazin);
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	public boolean stergere()
	{
		
		String temp = angajat_v.boxProdusText();
		String[] dateProdus= new String[2];
		dateProdus=temp.split("-");
		String numeMagazin= angajat_v.numeMagazinText();
		ProdusPersistenta prodPers = new ProdusPersistenta();
		Produs produs= new Produs(dateProdus[0],dateProdus[1]);
		Magazin magazin= new Magazin(numeMagazin);
		return prodPers.stergere(produs, magazin);

	}
	public boolean actualizare()
	{
		String numeProdus=angajat_v.numeText();
		String producator=angajat_v.producatorText();
		String pret=angajat_v.pretText();
		String disponibilitate= angajat_v.disponibilitateText();
		String numeMagazin= angajat_v.numeMagazinText();
		String temp=angajat_v.boxProdusText();
		String[] dateProdus= new String[2];
		String fisierImagine = angajat_v.adaugaImagineText();
		dateProdus=temp.split("-");
		ProdusPersistenta prodPers = new ProdusPersistenta();
		Produs produsNou= new Produs(numeProdus,producator,Double.parseDouble(pret),Integer.parseInt(disponibilitate),fisierImagine);
		Produs produs=new Produs(dateProdus[0],dateProdus[1]);
		Magazin magazin= new Magazin(numeMagazin);
		return prodPers.actualizare(produs, produsNou, magazin);
	}
}
