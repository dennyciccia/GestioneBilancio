/**
 * @file PannelloModificatore.java
 * File contenente la classe astratta PannelloModificatore che rappresenta l'interfaccia grafica per modificare il bilancio.
 */
package modificaDati;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import interfaccia.PannelloIniziale;

@SuppressWarnings("serial")

public abstract class PannelloModificatore extends JPanel{
	protected final double MAX_AMMONTARE = 100000.0; ///< Massimo valore che può avere l'ammontare
	
	protected PannelloIniziale panIniz; ///< Riferimento al {@link PannelloIniziale}
	
	/**
	 * Costruttore, inizializza il riferimento al pannello iniziale con quello passato come parametro.
	 * @param panIniz Riferimento al {@link PannelloIniziale}
	 */
	public PannelloModificatore(PannelloIniziale panIniz) {
		//Riferimento al pannello iniziale
		this.panIniz = panIniz;
		
		//Layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

}
