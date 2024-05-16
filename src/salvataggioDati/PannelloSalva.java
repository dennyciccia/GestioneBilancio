/**
 * @file PannelloSalva.java
 * File contenente la classe PannelloSalva che gestisce l'interfaccia grafica per salvare il bilancio su un file.
 */
package salvataggioDati;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import interfaccia.PannelloIniziale;

@SuppressWarnings("serial")

public class PannelloSalva extends PannelloSalvaCarica {
	
	/**
	 * Costruttore con parametri.
	 * inizializza il riferimento al pannello iniziale con quello passato come parametro richiamando il costruttore della classe padre.
	 * @param panIniz Riferimento al {@link PannelloIniziale}
	 */
	public PannelloSalva(PannelloIniziale panIniz) {
		super(panIniz);
	}
	
	/**
	 * Metodo per la gestione dell'evento associato alla pressione di un bottone.
	 * In base al bottone premuto si salva il bilancio o si torna indietro.
	 */
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case "Salva":
				salva();
				break;
			case "Annulla":
				super.actionPerformed(e);
				break;
		}
	}
	
	/**
	 * Metodo per salvare il bilancio sul file specificato dall'utente.
	 * Se il file esiste già viene chiesto se l'utente vuole sovrascriverlo.
	 */
	private void salva() {
		//File
		File f = new File("");
		String nomefile = f.getAbsolutePath() + File.separator + "files" + File.separator + txtNomeFile.getText() + ".dat";
		
		//Controllo se il file esiste
		if(new File(nomefile).exists()) {
			//Chiedo se sovrascrivere
			int scelta = JOptionPane.showConfirmDialog(panIniz.getFrame(), "Sovrascrivere il file esistente?", "Sovrascrivere", JOptionPane.YES_NO_OPTION);
			
			//Se scelgie si
			if(scelta == 0) {
				try {
					serializza(nomefile);
					
					JOptionPane.showMessageDialog(panIniz.getFrame(), "Bilancio salvato in " + nomefile, "Salvataggio", JOptionPane.INFORMATION_MESSAGE);
					panIniz.getFrame().cambiaScheda("PannelloIniziale");
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(panIniz.getFrame(), "Errore", "Errore", JOptionPane.WARNING_MESSAGE);
				}	
			}
			//Se sceglie no non fare niente
		} else {
			try {
				serializza(nomefile);
				
				JOptionPane.showMessageDialog(panIniz.getFrame(), "Bilancio salvato in " + nomefile, "Salvataggio", JOptionPane.INFORMATION_MESSAGE);
				panIniz.getFrame().cambiaScheda("PannelloIniziale");
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(panIniz.getFrame(), "Errore", "Errore", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	/**
	 * Metodo che serializza l'elenco delle voci su file.
	 * @param nomefile Nome del file
	 * @throws FileNotFoundException se il file non è stato trovato
	 * @throws IOException se si è verificato un errore nell'I/O
	 */
	private void serializza(String nomefile) throws FileNotFoundException, IOException {
		//Oggetto stream
		ObjectOutputStream oos = null;

		oos = new ObjectOutputStream(new FileOutputStream(nomefile));
		//oos.writeObject(elencoVoci);
		oos.writeObject(panIniz.getElencoVoci());
		oos.close();		
	}
}
