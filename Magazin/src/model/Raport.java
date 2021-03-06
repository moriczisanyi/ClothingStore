package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Raport {
	public Raport() {

	}

	public boolean generareRaportCSV(String magazin) {
		ProdusPersistenta pers = new ProdusPersistenta();
		ArrayList<Produs> dateProduse = pers.accesListaProduseRaport(magazin);
		try (PrintWriter writer = new PrintWriter(new File("ReportProduse".concat(magazin).concat(".csv")))) {

			StringBuilder sb = new StringBuilder();
			sb.append("Nume");
			sb.append(',');
			sb.append("Producator");
			sb.append(',');
			sb.append("Pret");
			sb.append(',');
			sb.append("Disponibilitate");
			sb.append(',');
			sb.append("Cale catre imagine");
			sb.append('\n');

			for (Produs data : dateProduse) {
				sb.append(data.accesNume());
				sb.append(',');
				sb.append(data.accesProducator());
				sb.append(',');
				sb.append(data.accesPret());
				sb.append(',');
				sb.append(data.accesDisponibilitate());
				sb.append(',');
				sb.append(data.accesImagine());
				sb.append('\n');

			}

			writer.write(sb.toString());

			return true;

		} catch (FileNotFoundException e) {
			return false;
		}
	}

	public boolean generareRaportJson(String magazin) {
		ProdusPersistenta pers = new ProdusPersistenta();
		ArrayList<Produs> dateProduse = pers.accesListaProduseRaport(magazin);
		JSONArray produsList = new JSONArray();
		
		for(Produs item: dateProduse)
		{
			JSONObject detaliiProdus = new JSONObject();
			detaliiProdus.put("nume", item.accesNume());
			detaliiProdus.put("producator", item.accesProducator());
			detaliiProdus.put("pret", item.accesPret());
			detaliiProdus.put("disponibilitate", item.accesDisponibilitate());
			detaliiProdus.put("cale imagine", item.accesImagine());
			
			JSONObject produsObject = new JSONObject();
			produsObject.put("Produs", detaliiProdus);
			
			produsList.put(produsObject);
		}
		
		

		try (FileWriter file = new FileWriter("Raport".concat(magazin).concat(".json") )) {
			file.write(produsList.toString(5));
			file.flush();
			return true;

		} catch (IOException e) {
			return false;
		}
	}
}
