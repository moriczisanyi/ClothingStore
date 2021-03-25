package model;

public class Angajat {
	private String nume;
	private String username;
	private String parola;
	private int varsta;
	
	public Angajat()
	{
		
	}
	public Angajat(String nume)
	{
		this.nume=nume;
	}
	public Angajat(String nume, String username, String parola, int varsta)
	{
		this.nume=nume;
		this.username=username;
		this.parola=parola;
		this.varsta=varsta;
	}
	public Angajat(Angajat angajat)
	{
		this.nume=angajat.nume;
		this.parola=angajat.parola;
		this.username=angajat.username;
		this.varsta=angajat.varsta;
	}
	public String accesNume()
	{
		return this.nume;
	}
	public String accesUsername()
	{
		return this.username;
	}
	public String accesParola()
	{
		return this.parola;
	}
	public int accesVarsta()
	{
		return this.varsta;
	}
	public void setareNume(String nume)
	{
		this.nume=nume;
	}
	public void setareUsername(String username)
	{
		this.username=username;
	}
	public void setareParola(String parola)
	{
		this.parola=parola;
	}
	public void setareVarsta(int varsta)
	{
		this.varsta=varsta;
	}
}
