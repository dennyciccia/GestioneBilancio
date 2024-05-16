/**
 * @file ElencoVoci.java
 * File contenente la classe ElencoVoci che utilizza un ArrayList per gestire una lista di oggetti {@link VoceDiBilancio}
 * Implementa l'interfaccia Serializable per poter essere serializzato
 */
package dati;

import java.io.Serializable;
import java.util.ArrayList;

import errori.AmmontareNonValidoException;
import errori.ArrayVuotoException;
import errori.DescrizioneVuotaException;
import errori.VoceNonPresenteException;

@SuppressWarnings("serial")

public class ElencoVoci implements Serializable{
	/**
	 * ArrayList contenente oggetti {@link VoceDiBilancio}
	 */
	private ArrayList<VoceDiBilancio> array;
	
	/**
	 * Costruttore senza parametri
	 * Inizializza l'array con un'istanza di un ArrayList<{@link VoceDiBilancio}> vuoto
	 */
	public ElencoVoci() {
		array = new ArrayList<VoceDiBilancio>();
	}
	
	/**
	 * Costruttore con parametri
	 * Inizializza l'array con un'istanza di un ArrayList<{@link VoceDiBilancio}> passato come parametro
	 * @param array Istanza di ArrayList
	 */
	public ElencoVoci(ArrayList<VoceDiBilancio> array) {
		this.array = array;
	}
	
	/**
	 * Metodo per aggiungere un oggetto {@link VoceDiBilancio} all'array
	 * Viene richiamato il metodo add() di ArrayList
	 * @param vdb Oggetto da aggiungere
	 */
	public void aggiungi(VoceDiBilancio vdb) {
		array.add(vdb);
	}
	
	/**
	 * Metodo per aggiungere una voce di bilancio all'array
	 * Viene creato un oggetto {@link VoceDiBilancio} con le informazioni passate come parametro e viene aggiungo all'array con il metodo add() di ArrayList
	 * @param data Oggetto Data contenente la data
	 * @param descrizione Stringa contenente la descrizione
	 * @param ammontare Double contenente l'ammontare
	 * @throws AmmontareNonValidoException se l'ammontare è minore di 0
	 * @throws DescrizioneVuotaException se la descrizione è vuota
	 */
	public void aggiungi(Data data, String descrizione, Double ammontare) throws AmmontareNonValidoException, DescrizioneVuotaException {
		array.add(new VoceDiBilancio(data,descrizione,ammontare));
	}
	
	/**
	 * Metodo per sostituire un oggetto VoceDibilancio contenuto nell'array con un altro
	 * Viene prima trovato l'indice dell'oggetto da sostituire e poi cambiato con il metodo set() di ArrayList
	 * @param vdbOld Oggetto {@link VoceDiBilancio} da sostituire
	 * @param vdbNew Oggetto {@link VoceDiBilancio} da inserire
	 * @throws VoceNonPresenteException se l'oggetto vdbOld non è contenuto nell'array
	 */
	public void modifica(VoceDiBilancio vdbOld, VoceDiBilancio vdbNew) throws VoceNonPresenteException {
		int i=0;
		boolean trovato = false;
		
		while(!trovato && i<array.size()) {
			if(array.get(i).equals(vdbOld)) {
				trovato = true;
			}
			i++;
		}
		
		if(trovato)
			array.set(i-1, vdbNew);
		else
			throw new VoceNonPresenteException();
	}
	
	/**
	 * Metodo per rimuovere un oggetto {@link VoceDiBilancio} dall'array
	 * @param vdb Oggetto {@link VoceDiBilancio} da rimuovere
	 * @throws VoceNonPresenteException se l'oggetto vdb non è contenuto nell'array
	 */
	public void rimuovi(VoceDiBilancio vdb) throws VoceNonPresenteException {
		if(array.contains(vdb))
			array.remove(vdb);
		else
			throw new VoceNonPresenteException();
	}
	
	/**
	 * Metodo per sommare tutti gli ammontari degli oggetti {@link VoceDiBilancio} contenuti nell'array
	 * @return Valore Double della somma degli ammontari
	 */
	public Double sommaAmmontare() {
		Double somma = 0.0;
		
		for(VoceDiBilancio vdb : array) {
			somma += vdb.getAmmontare();
		}
		
		return somma;
	}
	
	/**
	 * Metodo per ottenere il numero di elementi contenuti nell'array
	 * @return Valore intero della lughezza dell'array
	 */
	public int length() {
		return array.size();
	}
	
	/**
	 * Metodo per ottenere l'oggetto {@link VoceDiBilancio} all'indice specificato
	 * @param i Indice dell'oggetto
	 * @return Oggetto {@link VoceDiBilancio} all'indice specificato
	 * @throws ArrayVuotoException se l'array è vuoto
	 * @throws IndexOutOfBoundsException se l'indice è fuori dall'array
	 */
	public VoceDiBilancio get(int i) throws ArrayVuotoException, IndexOutOfBoundsException {
		if(array.isEmpty())
			throw new ArrayVuotoException();
		else
			return array.get(i);
	}
	
	/**
	 * Metodo per cercare degli oggetti {@link VoceDiBilancio} nell'array
	 * Viene controllato se la descrizione di ogni oggetto contiene la stringa passata come parametro
	 * @param s Stringa da cercare nelle descrizioni degli oggetti {@link VoceDiBilancio}
	 * @return Un oggetto ArrayList<Integer> contenente gli indici degli elementi nell'array che soddisfano la condizione
	 */
	public ArrayList<Integer> cerca(String s) {
		ArrayList<Integer> indici = new ArrayList<Integer>();
		
		for(int i=0; i<array.size(); i++) {
			if(array.get(i).getDescrizione().contains(s)) {
				indici.add(i);
			}
		}
		
		return indici;
	}
	
	/**
	 * Metodo per trasformare l'array in stringa 
	 * @return Stringa rappresentate l'array
	 */
	@Override
	public String toString() {
		return array.toString();
	}
}
