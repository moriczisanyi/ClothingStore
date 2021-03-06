package model;

public class Produs {
	private String nume;
	private String producator;
	private double pret;
	private int disponibilitate;
	private String imagine;
	
	public Produs()
	{
		
	}
	public Produs(String nume)
	{
		this.nume=nume;
	}
	
	public Produs(String nume, String producator) {
		this.nume = nume;
		this.producator = producator;
	}
	public Produs(String nume, String producator, double pret, int disponibilitate, String imagine)
	{
		this.nume=nume;
		this.producator=producator;
		this.pret=pret;
		this.disponibilitate=disponibilitate;
		this.imagine=imagine;
	}
	
	
	public String accesNume() {
		return nume;
	}
	public void setareNume(String nume) {
		this.nume = nume;
	}
	public String accesProducator() {
		return producator;
	}
	public void setareProducator(String producator) {
		this.producator = producator;
	}
	public double accesPret() {
		return pret;
	}
	public void setarePret(double pret) {
		this.pret = pret;
	}
	public int accesDisponibilitate() {
		return disponibilitate;
	}
	public void setareDisponibilitate(int disponibilitate) {
		this.disponibilitate = disponibilitate;
	}
	public String accesImagine() {
		return imagine;
	}
	public void setareImageine(String imagine) {
		this.imagine = imagine;
	}
	
	
}
