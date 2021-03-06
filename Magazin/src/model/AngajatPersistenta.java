package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AngajatPersistenta {
	public String numeFisier;
	
	public AngajatPersistenta()
	{
		
	}
	public boolean salvare(Angajat angajat, Magazin magazin) throws  IOException

	{
		try {

			this.numeFisier=magazin.accesNume().concat("angajati.xml");
			File file = new File(numeFisier);
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document;
			Element root;
			if(file.exists())
			{
				document = documentBuilder.parse(numeFisier);
				root = document.getDocumentElement();
			}
			else
			{
				
			
			document = documentBuilder.newDocument();
			root = document.createElement("Magazin");
			document.appendChild(root);
			}
			
			
			// root element
			ArrayList<Angajat> angajati = new ArrayList<Angajat>();
			angajati.add(angajat);

			// employee element
			for (Angajat ang : angajati) {

				Element angajatxml = document.createElement("Angajat");

				root.appendChild(angajatxml);

				Element nume = document.createElement("nume");
				nume.appendChild(document.createTextNode(ang.accesNume()));
				angajatxml.appendChild(nume);

				Element username = document.createElement("username");
				username.appendChild(document.createTextNode(ang.accesUsername()));
				angajatxml.appendChild(username);

				Element parola = document.createElement("parola");
				parola.appendChild(document.createTextNode(ang.accesParola()));
				angajatxml.appendChild(parola);

				Element varsta = document.createElement("varsta");
				varsta.appendChild(document.createTextNode(Integer.toString(ang.accesVarsta())));
				angajatxml.appendChild(varsta);
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(numeFisier));


			transformer.transform(domSource, streamResult);

			System.out.println("Done creating XML File");
			return true;

		} catch (ParserConfigurationException pce) {
			
			pce.printStackTrace();
			return false;
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
			return false;
		} catch (SAXException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean stergere(Angajat angajat, Magazin magazin)
	{
		this.numeFisier=magazin.accesNume().concat("angajati.xml");
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(numeFisier);
			NodeList angajati = document.getElementsByTagName("Angajat");
			for (int i = 0; i < angajati.getLength(); i++) {
				Element ang = (Element) angajati.item(i);
				Element numeAngajat = (Element) ang.getElementsByTagName("nume").item(0);
				if (numeAngajat.getTextContent().equalsIgnoreCase(angajat.accesNume()) ) {
					numeAngajat.getParentNode().getParentNode().removeChild(angajati.item(i));
					break;
				}
				

			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(numeFisier);
			transformer.transform(domSource, streamResult);
			return true;
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	public boolean actualizare(Angajat angajat, Angajat angajatNou, Magazin magazin)
	{
		this.numeFisier=magazin.accesNume().concat("angajati.xml");
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(numeFisier);
			NodeList angajati = document.getElementsByTagName("Angajat");
			for (int i = 0; i < angajati.getLength(); i++) {
				Element ang = (Element) angajati.item(i);
				Element numeAngajat = (Element) ang.getElementsByTagName("nume").item(0);
				Element usernameAngajat = (Element) ang.getElementsByTagName("username").item(0);
				Element parolaAngajat = (Element) ang.getElementsByTagName("parola").item(0);
				Element varstaAngajat = (Element) ang.getElementsByTagName("varsta").item(0);
				if (numeAngajat.getTextContent().equalsIgnoreCase(angajat.accesNume()) ) {
					
					numeAngajat.setTextContent(angajatNou.accesNume());
					usernameAngajat.setTextContent(angajatNou.accesUsername());
					parolaAngajat.setTextContent(angajatNou.accesParola());
					varstaAngajat.setTextContent(Integer.toString(angajatNou.accesVarsta() ));
					break;
				}
				

			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(numeFisier);
			transformer.transform(domSource, streamResult);
			return true;
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
			return false;
		}
	}
	public boolean accesDateAutentificare(String username, String parola, String magazin)
	{
		this.numeFisier=magazin.concat("angajati.xml");
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(numeFisier);
			NodeList angajati = document.getElementsByTagName("Angajat");
			for (int i = 0; i < angajati.getLength(); i++) {
				Element ang = (Element) angajati.item(i);
				Element usernameAngajat = (Element) ang.getElementsByTagName("username").item(0);
				Element parolaAngajat = (Element) ang.getElementsByTagName("parola").item(0);
				if (usernameAngajat.getTextContent().contentEquals(username) && parolaAngajat.getTextContent().contentEquals(parola) ) {
					
					return true;
				}
				

			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(numeFisier);
			transformer.transform(domSource, streamResult);
			return false;
		} catch (Exception e) {
			
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	public ArrayList<String> accesListaAngajati(String magazin)
	{
		this.numeFisier=magazin.concat("angajati.xml");
		ArrayList<String> listaAngajati = new ArrayList<String>();
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(numeFisier);
			NodeList angajati = document.getElementsByTagName("Angajat");
			
			for (int i = 0; i < angajati.getLength(); i++) {
				Element ang = (Element) angajati.item(i);
				Element numeAngajat = (Element) ang.getElementsByTagName("nume").item(0);
				listaAngajati.add(numeAngajat.getTextContent());
				
			}
			return listaAngajati;

		} catch (Exception e) {
			
			
			listaAngajati.add("");
			return listaAngajati;
		}
	}
}

