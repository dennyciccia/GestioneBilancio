/**
 * @file EsportatoreCSV.java
 * File contenente la classe EsportatoreCSV per gestire l'esportazione in formato CSV
 */
package esportazioneDati;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import dati.ElencoVoci;

public class EsportatoreCSV extends Esportatore {
	
	/**
	 * Costruttore con parametri
	 * Inizializza gli attributi con le istanze passate come parametri richiamando il costruttore della classe padre {@link Esportatore}
	 * @param nomefile Stringa rappresentante il nome del file in cui esportare
	 * @param elencoVoci Oggetto {@link ElencoVoci} che contiene l'elenco da esportare
	 */
	public EsportatoreCSV(String nomefile, ElencoVoci elencoVoci) {
		super(nomefile, elencoVoci);
	}

	/**
	 * Implementazione del metodo esporta() di {@link Esportatore} 
	 * Esporta in formato CSV l'oggetto {@link ElencoVoci} nel file 'nomefile'
	 */
	@Override
	public void esporta() {
		File file = new File(super.getNomefile());
		
		try {
			//Oggetto per scrivere sul file
			FileWriter fw = new FileWriter(file);
			
			//Intestazioni
			fw.write("Data,Descrizione,Ammontare\n");
			
			//Elenco delle voci
			for(int i=0; i<super.getElencoVoci().length(); i++) {
				fw.write(super.getElencoVoci().get(i).toCSV() + "\n");
			}
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
