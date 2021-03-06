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

public class ProdusPersistenta {
	public String numeFisier;

	public ProdusPersistenta() {

	}

	public ArrayList<String> accesListaProduse(String magazin) {
		this.numeFisier = magazin.concat("produse.xml");
		ArrayList<String> listaProduse = new ArrayList<String>();
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(numeFisier);
			NodeList produse = document.getElementsByTagName("Produs");

			for (int i = 0; i < produse.getLength(); i++) {
				Element prod = (Element) produse.item(i);
				Element numeProdus = (Element) prod.getElementsByTagName("nume").item(0);
				Element numeProducator = (Element) prod.getElementsByTagName("producator").item(0);

				listaProduse.add(numeProdus.getTextContent().concat("-").concat(numeProducator.getTextContent()));

			}
			return listaProduse;

		} catch (Exception e) {

			listaProduse.add("");
			return listaProduse;
		}
	}
	
	public ArrayList<String> accesListaPreturi(String magazin) {
		this.numeFisier = magazin.concat("produse.xml");
		ArrayList<String> listaPreturi = new ArrayList<String>();
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(numeFisier);
			NodeList produse = document.getElementsByTagName("Produs");

			for (int i = 0; i < produse.getLength(); i++) {
				Element prod = (Element) produse.item(i);
				Element pret = (Element) prod.getElementsByTagName("pret").item(0);

				listaPreturi.add(pret.getTextContent());

			}
			return listaPreturi;

		} catch (Exception e) {

			return listaPreturi;
		}
	}
	
	public ArrayList<String> accesListaDisponibilitate(String magazin) {
		this.numeFisier = magazin.concat("produse.xml");
		ArrayList<String> listaDisponibilitate = new ArrayList<String>();
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(numeFisier);
			NodeList produse = document.getElementsByTagName("Produs");

			for (int i = 0; i < produse.getLength(); i++) {
				Element prod = (Element) produse.item(i);
				Element disp = (Element) prod.getElementsByTagName("disponibilitate").item(0);

				listaDisponibilitate.add(disp.getTextContent());

			}
			return listaDisponibilitate;

		} catch (Exception e) {

			return listaDisponibilitate;
		}
	}
	
	
	public String accesImagine(Produs produs, String magazin) {
		this.numeFisier = magazin.concat("produse.xml");
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(numeFisier);
			NodeList produse = document.getElementsByTagName("Produs");
			for (int i = 0; i < produse.getLength(); i++) {
				Element prod = (Element) produse.item(i);
				Element numeProdus = (Element) prod.getElementsByTagName("nume").item(0);
				Element producator = (Element) prod.getElementsByTagName("producator").item(0);
				Element imagine = (Element) prod.getElementsByTagName("imagine").item(0);
				if (numeProdus.getTextContent().equalsIgnoreCase(produs.accesNume())
						&& producator.getTextContent().equalsIgnoreCase(produs.accesProducator())) {
					return imagine.getTextContent();
				}

			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(numeFisier);
			transformer.transform(domSource, streamResult);
			return null;
		} catch (Exception e) {

			System.err.println(e.getMessage());
			return null;
		}
	}
	
	public ArrayList<Produs> accesListaProduseRaport(String magazin) {
		this.numeFisier = magazin.concat("produse.xml");
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(numeFisier);
			ArrayList<Produs> produseReturn = new ArrayList<Produs>();
			NodeList produse = document.getElementsByTagName("Produs");
			for (int i = 0; i < produse.getLength(); i++) {
				Element prod = (Element) produse.item(i);
				Element numeProdus = (Element) prod.getElementsByTagName("nume").item(0);
				Element producator = (Element) prod.getElementsByTagName("producator").item(0);
				Element pret = (Element) prod.getElementsByTagName("pret").item(0);
				Element disponibilitate = (Element) prod.getElementsByTagName("disponibilitate").item(0);
				Element imagine = (Element) prod.getElementsByTagName("imagine").item(0);
				Produs temp = new Produs(numeProdus.getTextContent(),producator.getTextContent(),
						Double.parseDouble(pret.getTextContent()),Integer.parseInt(disponibilitate.getTextContent()),imagine.getTextContent());
				produseReturn.add(temp);

			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(numeFisier);
			transformer.transform(domSource, streamResult);
			return produseReturn;
		} catch (Exception e) {

			System.err.println(e.getMessage());
			return null;
		}
	}
	public ArrayList<String> accesListaProduseListare(String magazin, String filtruProducator, String filtruPret,
			String filtruDisponibilitate) {
		this.numeFisier = magazin.concat("produse.xml");
		ArrayList<String> listaProduse = new ArrayList<String>();
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(numeFisier);
			NodeList produse = document.getElementsByTagName("Produs");

			for (int i = 0; i < produse.getLength(); i++) {
				Element prod = (Element) produse.item(i);
				Element numeProdus = (Element) prod.getElementsByTagName("nume").item(0);
				Element numeProducator = (Element) prod.getElementsByTagName("producator").item(0);
				Element pret = (Element) prod.getElementsByTagName("pret").item(0);
				Element disponibilitate = (Element) prod.getElementsByTagName("disponibilitate").item(0);
				boolean filtruPretPass=true;
				boolean filtruProducatorPass=true;
				boolean filtruDisponibilitatePass=true;
				if (filtruProducator == null && filtruPret == null && filtruDisponibilitate == null) {
					listaProduse.add(numeProdus.getTextContent().concat("-")
							.concat(numeProducator.getTextContent().concat("-Pret:").concat(pret.getTextContent()
									.concat("-nr.buc. :").concat(disponibilitate.getTextContent()))));
				}
				else
				{
					if(filtruPret!=null)
					{
						String[] valori=filtruPret.split("-");
						double filtruJos=Double.parseDouble(valori[0]);
						double filtruSus=Double.parseDouble(valori[1]);
						double pretProd=Double.parseDouble(pret.getTextContent());
						if(filtruJos<= pretProd && pretProd<=filtruSus)
						{
							filtruPretPass=true;
						}
						else
							filtruPretPass=false;
						
					}
					
					if(filtruProducator!=null)
					{
						if(filtruProducator.equalsIgnoreCase(numeProducator.getTextContent()))
						{
							filtruProducatorPass=true;
						}
						else
							filtruProducatorPass=false;
					}
					
					if(filtruDisponibilitate!=null)
					{
						String[] valori=filtruDisponibilitate.split("-");
						double filtruJos=Integer.parseInt(valori[0]);
						double filtruSus=Integer.parseInt(valori[1]);
						double dispProd=Integer.parseInt(disponibilitate.getTextContent());
						if(filtruJos<= dispProd && dispProd<=filtruSus)
						{
							filtruDisponibilitatePass=true;
						}
						else
							filtruDisponibilitatePass=false;
						
					}
					if(filtruDisponibilitatePass && filtruPretPass && filtruProducatorPass)
					{
						listaProduse.add(numeProdus.getTextContent().concat("-")
								.concat(numeProducator.getTextContent().concat("-Pret:").concat(pret.getTextContent()
										.concat("-nr.buc. :").concat(disponibilitate.getTextContent()))));
					}
					
					
				}

			}
			return listaProduse;

		} catch (Exception e) {

			listaProduse.add("");
			return listaProduse;
		}
	}

	public boolean salvare(Produs produs, String magazin) throws IOException

	{
		try {

			this.numeFisier = magazin.concat("produse.xml");
			File file = new File(numeFisier);
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document;
			Element root;
			if (file.exists()) {
				document = documentBuilder.parse(numeFisier);
				root = document.getDocumentElement();
			} else {

				document = documentBuilder.newDocument();
				root = document.createElement("Magazin");
				document.appendChild(root);
			}

			ArrayList<Produs> produse = new ArrayList<Produs>();
			produse.add(produs);

			for (Produs prod : produse) {

				Element angajatxml = document.createElement("Produs");

				root.appendChild(angajatxml);

				Element nume = document.createElement("nume");
				nume.appendChild(document.createTextNode(prod.accesNume()));
				angajatxml.appendChild(nume);

				Element username = document.createElement("producator");
				username.appendChild(document.createTextNode(prod.accesProducator()));
				angajatxml.appendChild(username);

				Element parola = document.createElement("pret");
				parola.appendChild(document.createTextNode(Double.toString(prod.accesPret())));
				angajatxml.appendChild(parola);

				Element varsta = document.createElement("disponibilitate");
				varsta.appendChild(document.createTextNode(Integer.toString(prod.accesDisponibilitate())));
				angajatxml.appendChild(varsta);
				
				Element fisierImagine = document.createElement("imagine");
				fisierImagine.appendChild(document.createTextNode(prod.accesImagine()));
				angajatxml.appendChild(fisierImagine);
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(numeFisier));

			transformer.transform(domSource, streamResult);

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

	public boolean stergere(Produs produs, Magazin magazin) {
		this.numeFisier = magazin.accesNume().concat("produse.xml");
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(numeFisier);
			NodeList produse = document.getElementsByTagName("Produs");
			for (int i = 0; i < produse.getLength(); i++) {
				Element prod = (Element) produse.item(i);
				Element numeAngajat = (Element) prod.getElementsByTagName("nume").item(0);
				Element producator = (Element) prod.getElementsByTagName("producator").item(0);
				if (numeAngajat.getTextContent().equalsIgnoreCase(produs.accesNume())
						&& producator.getTextContent().equalsIgnoreCase(produs.accesProducator())) {
					numeAngajat.getParentNode().getParentNode().removeChild(produse.item(i));
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

	public boolean actualizare(Produs produs, Produs produsNou, Magazin magazin) {
		this.numeFisier = magazin.accesNume().concat("produse.xml");
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(numeFisier);
			NodeList produse = document.getElementsByTagName("Produs");
			for (int i = 0; i < produse.getLength(); i++) {
				Element prod = (Element) produse.item(i);
				Element numeProdus = (Element) prod.getElementsByTagName("nume").item(0);
				Element producator = (Element) prod.getElementsByTagName("producator").item(0);
				Element pret = (Element) prod.getElementsByTagName("pret").item(0);
				Element disponibilitate = (Element) prod.getElementsByTagName("disponibilitate").item(0);
				Element imagine = (Element) prod.getElementsByTagName("imagine").item(0);
				if (numeProdus.getTextContent().equalsIgnoreCase(produs.accesNume())
						&& producator.getTextContent().equalsIgnoreCase(produs.accesProducator())) {

					numeProdus.setTextContent(produsNou.accesNume());
					producator.setTextContent(produsNou.accesProducator());
					pret.setTextContent(Double.toString(produsNou.accesPret()));
					disponibilitate.setTextContent(Integer.toString(produsNou.accesDisponibilitate()));
					imagine.setTextContent(produsNou.accesImagine());
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
}
