/**
 * @file PannelloCarica.java
 * File contenente la classe PannelloCarica che gestisce l'interfaccia grafica per caricare il bilancio da un file.
 */
package salvataggioDati;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dati.ElencoVoci;
import interfaccia.PannelloIniziale;

@SuppressWarnings("serial")

public class PannelloCarica extends PannelloSalvaCarica {
	
	/**
	 * Costruttore con parametri.
	 * inizializza il riferimento al pannello iniziale con quello passato come parametro richiamando il costruttore della classe padre.
	 * @param panIniz Riferimento al {@link PannelloIniziale}
	 */
	public PannelloCarica(PannelloIniziale panIniz) {
		super(panIniz);
		
		JPanel panelAvviso = new JPanel();
		JLabel lblAvviso = new JLabel("Il bilancio corrente verrà sovrascritto");
		panelAvviso.add(lblAvviso);
		add(panelAvviso, BorderLayout.SOUTH);
	}
	
	/**
	 * Metodo per la gestione dell'evento associato alla pressione di un bottone.
	 * In base al bottone premuto si carica il bilancio o si torna indietro.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Carica":
			carica();
			break;
		case "Annulla":
			super.actionPerformed(e);
			break;
		}
	}
	
	/**
	 * Metodo per caricare il bilancio dal file specificato dall'utente.
	 * Se il file esiste già viene chiesto se l'utente vuole sovrascriverlo.
	 */
	private void carica() {
		//Oggetto stream e file
		ObjectInputStream ois = null;
		File f = new File("");
		String nomefile = f.getAbsolutePath() + File.separator + "files" + File.separator + txtNomeFile.getText() + ".dat";
		
		//deserializzazione elenco voci
		try {
			ois = new ObjectInputStream(new FileInputStream(nomefile));
			panIniz.setElencoVoci((ElencoVoci) ois.readObject());
			ois.close();
			
			JOptionPane.showMessageDialog(panIniz.getFrame(), "Bilancio caricato da " + nomefile, "Caricamento", JOptionPane.INFORMATION_MESSAGE);
			panIniz.getFrame().cambiaScheda("PannelloIniziale");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(panIniz.getFrame(), "File non trovato", "Errore", JOptionPane.WARNING_MESSAGE);
		} catch(IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(panIniz.getFrame(), "Errore", "Errore", JOptionPane.WARNING_MESSAGE);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
