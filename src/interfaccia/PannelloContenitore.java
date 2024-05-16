/**
 * @file PannelloContenitore.java
 * File contenente la classe PannelloContenitore usata per gestire la navigazione tra le schede dell'interfaccia grafica.
 */
package interfaccia;

import java.awt.CardLayout;
import javax.swing.JPanel;

import dati.ElencoVoci;
import esportazioneDati.PannelloEsporta;
import modificaDati.PannelloAggiungi;
import modificaDati.PannelloModifica;
import modificaDati.PannelloModificatore;
import modificaDati.PannelloRimuovi;
import salvataggioDati.PannelloCarica;
import salvataggioDati.PannelloSalva;
import salvataggioDati.PannelloSalvaCarica;

@SuppressWarnings("serial")

public class PannelloContenitore extends JPanel {
	private PannelloIniziale panIniz; ///< Riferimento al {@link PannelloIniziale}
	
	/**
	 * Costruttore con parametri, inizializza il riferimento al {@link PannelloIniziale} 
	 * e passa ai pannelli che contiene i riferimenti al'elenco delle voci e al frame iniziale.
	 * @param l Layout del pannello
	 * @param elencoVoci Riferimento all'oggetto {@link ElencoVoci}
	 * @param frame Riferimento all'oggetto {@link FrameIniziale}
	 */
	public PannelloContenitore(CardLayout l, ElencoVoci elencoVoci, FrameIniziale frame) {
		setLayout(l);
		
		panIniz = new PannelloIniziale(elencoVoci, frame);
		PannelloModificatore panAdd = new PannelloAggiungi(panIniz);
		PannelloModificatore panMod = new PannelloModifica(panIniz);
		PannelloModificatore panRim = new PannelloRimuovi(panIniz);
		PannelloSalvaCarica panSave = new PannelloSalva(panIniz);
		PannelloSalvaCarica panLoad = new PannelloCarica(panIniz);
		PannelloEsporta panExp = new PannelloEsporta(panIniz);
		
		add(panIniz, "PannelloIniziale");
		add(panAdd, "PannelloAggiungi");
		add(panMod, "PannelloModifica");
		add(panRim, "PannelloRimuovi");
		add(panSave, "PannelloSalva");
		add(panLoad, "PannelloCarica");
		add(panExp, "PannelloEsporta");
	}
	
	/**
	 * Getter del riferimento al pannello iniziale.
	 * @return Riferimento al {@link PannelloIniziale}
	 */
	public PannelloIniziale getPanIniz() {
		return panIniz;
	}
}
