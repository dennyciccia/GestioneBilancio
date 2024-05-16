/**
 * @file VoceDiBilancio.java
 * File cotenente la classe VoceDiBilancio che rappresenta l'entità voce di bilancio
 * Implementa l'interfaccia Serializable per poter essere serializzato
 */
package dati;

import java.io.Serializable;

import errori.AmmontareNonValidoException;
import errori.DescrizioneVuotaException;

@SuppressWarnings("serial")

public class VoceDiBilancio implements Serializable{
	private Data data; ///< Campo data della voce
	private String descrizione; ///< Campo descrizione della voce
	private Double ammontare; ///< Campo ammontare della voce
	
	/**
	 * Costruttore senza parametri
	 * Inizializza la data a null, la descrizione a stringa vuota e l'ammontare a 0.0
	 */
	public VoceDiBilancio() {
		this.data = null;
		this.descrizione = "";
		this.ammontare = 0.0;
	}
	
	/**
	 * Costruttore con parametri
	 * Inizializza l'istanza della voce con i campi passati come parametro
	 * @param data Campo data della voce
	 * @param descrizione Campo descrizione della voce
	 * @param ammontare Campo ammontare della voce
	 * @throws AmmontareNonValidoException se l'ammontare è uguale a 0
	 * @throws DescrizioneVuotaException se la descrizione è vuota
	 */
	public VoceDiBilancio(Data data, String descrizione, Double ammontare) throws AmmontareNonValidoException, DescrizioneVuotaException{
		setData(data);
		setDescrizione(descrizione);
		setAmmontare(ammontare);
	}

	/**
	 * Getter del campo data
	 * @return Oggetto {@link Data} rappresentante il campo data
	 */
	public Data getData() {
		return data;
	}

	/**
	 * Setter del campo data
	 * @param data Data da impostare
	 */
	public void setData(Data data) {
		this.data = data;
	}

	/**
	 * Getter del campo descrizione
	 * @return Stringa rappresentante il campo descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Setter del campo descrizione
	 * @param descrizione Descrizione da impostare
	 * @throws DescrizioneVuotaException se la descrizione è vuota
	 */
	public void setDescrizione(String descrizione) throws DescrizioneVuotaException {
		if(!descrizione.isBlank())
			this.descrizione = descrizione;
		else
			throw new DescrizioneVuotaException();
	}

	/**
	 * Getter del campo ammonatare
	 * @return Double rappresentante il campo ammontare
	 */
	public Double getAmmontare() {
		return ammontare;
	}

	/**
	 * Setter del campo ammontare
	 * @param ammontare Valore double dell'ammontare da impostare
	 * @throws AmmontareNonValidoException se l'ammontare è uguale a 0
	 */
	public void setAmmontare(Double ammontare) throws AmmontareNonValidoException {
		if(ammontare != 0.0)
			this.ammontare = ammontare;
		else
			throw new AmmontareNonValidoException();
	}
	
	/**
	 * Metodo per verificare che l'istanza corrente della voce di bilancio è uguale a un'altra
	 * Vengono confrontati i tre campi
	 * @param obj Oggetto da confrontare
	 * @return true se gli oggetti rappresentano la stessa voce, false altrimenti
	 */
	@Override
	public boolean equals(Object obj) {	
		VoceDiBilancio vdb = (VoceDiBilancio) obj;
		boolean data = this.data.equals(vdb.getData());
		boolean descr = this.descrizione.equals(vdb.getDescrizione());
		boolean ammont = this.ammontare.equals(vdb.getAmmontare());
		
		return data && descr && ammont;
	}
	
	/**
	 * Metodo per trasformare la voce di bilancio in una stringa in formato CSV (data,descrizione,ammontare)
	 * @return Stringa rappresentante la voce di bilancio
	 */
	public String toCSV() {
		return data.toString() + ",\"" + descrizione + "\"," + ammontare;
	}
	
	/**
	 * Metodo per trasformare la voce di bilancio in una stringa in formato txt (data descrizione ammontare)
	 * @return Stringa rappresentante la voce di bilancio
	 */
	public String toTxt() {
		return data.toString() + " \t\t \"" + descrizione + "\" \t\t " + ammontare;
	}
	
	/**
	 * Metodo per trasformare la voce di bilancio in una stringa
	 * @return Stringa rappresentante la voce di bilancio
	 */
	@Override
	public String toString() {
		return "VoceDiBilancio [data=" + data + ", descrizione=" + descrizione + ", ammontare=" + ammontare + "]";
	}
}
