/**
 * @file AmmontareNonValidoException.java
 * FIle contenente la classe AmmontareNonValidoException usata per gestire l'errore generato dall'ammontare non valido
 */
package errori;

@SuppressWarnings("serial")

public class AmmontareNonValidoException extends Exception{
	/**
	 * Messaggio associato all'eccezione
	 */
	private String message;
	
	/**
	 * Costruttore senza parametri
	 * Inizializza l'istanza con il messaggio associato all'eccezione "L'ammontare non può essere uguale a zero"
	 */
	public AmmontareNonValidoException() {
		super("L'ammontare non può essere uguale a zero");
		message = "L'ammontare non può essere uguale a zero";
	}
	
	/**
	 * Getter per il messaggio associato all'eccezione
	 */
	public String getMessage() {
		return message;
	}
}
