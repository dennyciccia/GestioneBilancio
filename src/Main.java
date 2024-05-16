/**
 * @file Main.java
 * File contenente la classe Main che esegue il programma
 * 
 */
import dati.ElencoVoci;
import interfaccia.FrameIniziale;

public class Main {

	/**
	 * Crea un'instanza di {@link FrameIniziale} e gli passa un'istanza di {@link ElencoVoci}
	 * 
	 * @param args Argomenti passati al main tramite la linea di comando
	 */
	public static void main(String[] args) {
		new FrameIniziale(new ElencoVoci());
	}

}
