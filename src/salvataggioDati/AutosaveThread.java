/**
 * @file AutosaveThread.java
 * File contenente la classe AutosaveThread che implementa un thread che salva automaticamente il bilancio periodicamente.
 */
package salvataggioDati;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import interfaccia.PannelloIniziale;

public class AutosaveThread extends Thread {
	private final int timeout = 1 * 60 * 1000; //n° secondi * 1000 ms ///< Tempo di sleep del thread
	
	private PannelloIniziale panIniz; ///< Riferimento al {@link PannelloIniziale}
	
	/**
	 * Costruttore con parametri.
	 * Inizializza il riferimento al pannello iniziale con quello passato come parametro.
	 * @param panIniz Riferimento al {@link PannelloIniziale}
	 */
	public AutosaveThread(PannelloIniziale panIniz) {
		this.panIniz = panIniz;
	}
	
	/**
	 * Metodo run che viene eseguito quando il thread viene avviato.
	 * Salva l'elenco delle voci nel file autosave.dat periodicamente.
	 */
	@Override
	public void run() {
		File f = new File("");
		String nomefile = f.getAbsolutePath() + File.separator + "files" + File.separator + "autosave.dat";
		
		while(true) {
			//Aspetta il tempo indicato
			try { sleep(timeout); } catch (InterruptedException e) {}
			
			//Oggetto stream
			ObjectOutputStream oos = null;

			//Serializza elenco voci
			try {
				oos = new ObjectOutputStream(new FileOutputStream(nomefile));
				oos.writeObject(panIniz.getElencoVoci());
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
