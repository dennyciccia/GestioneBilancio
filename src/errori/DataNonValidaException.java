/**
 * @file DataNonValidaException.java
 * FIle contenente la classe DataNonValidaException usata per gestire l'errore generato dalla data non valida
 */
package errori;

@SuppressWarnings("serial")

public class DataNonValidaException extends RuntimeException{
	/**
	 * Messaggio associato all'eccezione
	 */
	private String message;
	
	/**
	 * Costruttore senza parametri
	 * Inizializza l'istanza con il messaggio associato all'eccezione "La data non è valida"
	 */
	public DataNonValidaException() {
		super("La data non è valida");
		message = "La data non è valida";
	}
	
	/**
	 * Getter per il messaggio associato all'eccezione
	 */
	public String getMessage() {
		return message;
	}
}
