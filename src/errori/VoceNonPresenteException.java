/**
 * @file VoceNonPresenteException.java
 * FIle contenente la classe VoceNonPresenteException usata per gestire l'errore generato dal una voce non presente in una lista
 */
package errori;

@SuppressWarnings("serial")

public class VoceNonPresenteException extends Exception{
	/**
	 * Messaggio associato all'eccezione
	 */
	private String message;
	
	/**
	 * Costruttore senza parametri
	 * Inizializza l'istanza con il messaggio associato all'eccezione "La voce non è presente"
	 */
	public VoceNonPresenteException() {
		super("La voce non è presente");
		message = "La voce non è presente";
	}
	
	/**
	 * Getter per il messaggio associato all'eccezione
	 */
	public String getMessage() {
		return message;
	}
}
