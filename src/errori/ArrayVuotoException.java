/**
 * @file ArrayVuotoException.java
 * FIle contenente la classe ArrayVuotoException usata per gestire l'errore generato dall'array vuoto
 */
package errori;

@SuppressWarnings("serial")

public class ArrayVuotoException extends RuntimeException{
	/**
	 * Messaggio associato all'eccezione
	 */
	private String message;
	
	/**
	 * Costruttore senza parametri
	 * Inizializza l'istanza con il messaggio associato all'eccezione "L'array è vuoto"
	 */
	public ArrayVuotoException() {
		super("L'array è vuoto");
		message = "L'array è vuoto";
	}
	
	/**
	 * Getter per il messaggio associato all'eccezione
	 */
	public String getMessage() {
		return message;
	}
}
