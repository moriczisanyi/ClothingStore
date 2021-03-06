package presenter;

import java.io.IOException;
import java.util.ArrayList;

import model.Angajat;
import model.AngajatPersistenta;
import model.Magazin;
import view.GUIAutentificare;
import view.IAdminView;

public class PAdmin {
	private IAdminView admin;
	
	public PAdmin(IAdminView admin)
	{
		this.admin=admin;
	}
	
	
	public boolean Adaugare()
	{
		String username=admin.usernameText();
		String parola=admin.parolaText();
		String nume=admin.numeText();
		String varsta= admin.varstaText();
		String numeMagazin= admin.boxMagazinText();
		if(username.contentEquals("") || parola.contentEquals("") || nume.contentEquals("") || varsta.contentEquals(""))
		{
			return false;
		}

		AngajatPersistenta angPers = new AngajatPersistenta();
		Angajat angajat= new Angajat(nume,username,parola,Integer.parseInt(varsta));
		Magazin magazin= new Magazin(numeMagazin);
		try {
			angPers.salvare(angajat, magazin);
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	public boolean Stergere()
	{
		
		String nume = admin.boxAngajatText();
		String numeMagazin= admin.boxMagazinText();
		AngajatPersistenta angPers = new AngajatPersistenta();
		Angajat angajat= new Angajat(nume);
		Magazin magazin= new Magazin(numeMagazin);
		return angPers.stergere(angajat, magazin);
		
	}
	public boolean Actualizare()
	{
		String username=admin.usernameText();
		String parola=admin.parolaText();
		String nume=admin.numeText();
		String varsta= admin.varstaText();
		String numeMagazin= admin.boxMagazinText();
		String numeAngajatVechi=admin.boxAngajatText();
		AngajatPersistenta angPers = new AngajatPersistenta();
		Angajat angajatNou= new Angajat(nume,username,parola,Integer.parseInt(varsta));
		Angajat angajat=new Angajat(numeAngajatVechi);
		Magazin magazin= new Magazin(numeMagazin);
		return angPers.actualizare(angajat, angajatNou, magazin);
		
	}
	public ArrayList<String> accesListaAngajati()
	{
		AngajatPersistenta angPers = new AngajatPersistenta();
		return angPers.accesListaAngajati(admin.boxMagazinText());
	}
	public void Logout()
	{
		new GUIAutentificare();
	}
}
