/**
 * @file Esportatore.java
 * File contenente la classe astratta Esportatore che rappresenta un oggetto che può esportare in diversi formati
 */
package esportazioneDati;

import dati.ElencoVoci;

public abstract class Esportatore {
	private String nomefile; ///< Nome del file in cui esportare
	private ElencoVoci elencoVoci; ///< Oggetto {@link ElencoVoci} da esportare
	
	/**
	 * Costruttore con parametri
	 * Inizializza il nome del file e l'elenco delle voci con le istanze passate come parametro
	 * @param nomefile Stringa rappresentante il nome del file in cui esportare
	 * @param elencoVoci Oggetto {@link ElencoVoci} che contiene l'elenco da esportare
	 */
	public Esportatore(String nomefile, ElencoVoci elencoVoci) {
		this.setNomefile(nomefile);
		this.setElencoVoci(elencoVoci);
	}
	
	/**
	 * Getter del nome del file
	 * @return Stringa rappresentante il nome del file in cui esportare
	 */
	public String getNomefile() {
		return nomefile;
	}

	/**
	 * Setter del nome del file in cui esportare 
	 * @param nomefile Stringa rappresentante il nome del file
	 */
	public void setNomefile(String nomefile) {
		this.nomefile = nomefile;
	}

	/**
	 * Getter dell'elenco delle voci da esportare
	 * @return Oggetto {@link ElencoVoci} da esportare
	 */
	public ElencoVoci getElencoVoci() {
		return elencoVoci;
	}
	
	/**
	 * Setter del nome dell'elenco delle voci da esportare 
	 * @param elencoVoci Istanza di {@link ElencoVoci} da esportare
	 */
	public void setElencoVoci(ElencoVoci elencoVoci) {
		this.elencoVoci = elencoVoci;
	}

	/**
	 * Metodo astratto non implementato per esportare l'oggetto {@link ElencoVoci} nel file 'nomefile'
	 */
	public abstract void esporta();
}
