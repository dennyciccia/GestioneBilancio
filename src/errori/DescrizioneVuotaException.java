/**
 * @file DescrizioneVuotaException.java
 * FIle contenente la classe DescrizioneVuotaException usata per gestire l'errore generato dalla descrizione vuota
 */
package errori;

@SuppressWarnings("serial")

public class DescrizioneVuotaException extends Exception{
	/**
	 * Messaggio associato all'eccezione
	 */
	private String message;
	
	/**
	 * Costruttore senza parametri
	 * Inizializza l'istanza con il messaggio associato all'eccezione "La descrizione non può essere vuota"
	 */
	public DescrizioneVuotaException() {
		super("La descrizione non può essere vuota");
		message = "La descrizione non può essere vuota";
	}
	
	/**
	 * Getter per il messaggio associato all'eccezione
	 */
	public String getMessage() {
		return message;
	}
}
