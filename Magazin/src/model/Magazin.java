package model;

import java.util.ArrayList;

public class Magazin {
	private ArrayList<Angajat> angajati;
	private String nume;
	private ArrayList<Produs> produse;

	
	public Magazin(ArrayList<Angajat> angajati, String nume, ArrayList<Produs> produse) {
		super();
		this.angajati = angajati;
		this.nume = nume;
		this.produse = produse;
	}

	public Magazin(String nume) {
		this.nume=nume;
	}

	public ArrayList<Angajat> accesAngajati() {
		return angajati;
	}
	
	
	public ArrayList<Produs> accesProduse() {
		return produse;
	}

	public void setareProduse(ArrayList<Produs> produse) {
		this.produse = produse;
	}

	public void setareAngajati(ArrayList<Angajat> angajati) {
		this.angajati = angajati;
	}

	public String accesNume() {
		return nume;
	}

	public void setareNume(String nume) {
		this.nume = nume;
	}
	
	
	
}
