/**
 * @file FrameIniziale.java
 * File contenente il frame che contiene tutti i componenti dell'interfaccia grafica.
 * Implementa l'interfaccia ActionListener per gestire gli eventi associati alla pressione di bottoni.
 */
package interfaccia;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import dati.ElencoVoci;

@SuppressWarnings("serial")

public class FrameIniziale extends JFrame implements ActionListener{
	private final String APPNAME = "Gestione bilancio"; ///< Nome dell'applicazione
	
	private CardLayout layout; ///< Oggetto per gestire il layout
	private PannelloContenitore panelCont; ///< Pannello che contiene tutti i pannelli e li mostra uno alla volta
	
	//Componenti interfaccia
	private JMenuBar menuBar;
	private JMenu menuFile, menuTabella;
	private JMenuItem menuItemSalva, menuItemCarica, menuItemEsporta, menuItemStampa; //Voci menù file
	private JMenuItem menuItemAggiungi, menuItemModifica, menuItemRimuovi; //Voci menù tabella
	
	/**
	 * Costruttore dell'interfaccia grafica, inizializza il riferimento all'elenco delle voci con quello passato come parametro.
	 * @param elencoVoci riferimento all'oggetto {@link ElencoVoci}
	 */
	public FrameIniziale(ElencoVoci elencoVoci) {
		//Impostazioni frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(APPNAME);
		
		//creazione menù
		creaMenu();
		
		//creazione pannello contenitore
		panelCont = new PannelloContenitore(layout = new CardLayout(), elencoVoci, this);
		add(panelCont);
		
		//Impostazioni visualizzazione frame
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
	}
	
	/**
	 * Metodo che crea la barra dei menù per navigare nell'applicazione.
	 */
	private void creaMenu() {
		menuBar = new JMenuBar();
		
		//Menù File
		menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		menuItemSalva = new JMenuItem("Salva");
		menuFile.add(menuItemSalva);
		menuItemCarica = new JMenuItem("Carica");
		menuFile.add(menuItemCarica);
		
		menuFile.addSeparator();
		
		menuItemEsporta = new JMenuItem("Esporta");
		menuFile.add(menuItemEsporta);
		
		menuFile.addSeparator();
		
		menuItemStampa = new JMenuItem("Stampa");
		menuFile.add(menuItemStampa);
		
		//Menù Tabella
		menuTabella = new JMenu("Tabella");
		menuBar.add(menuTabella);
		
		menuItemAggiungi = new JMenuItem("Aggiungi voce");
		menuTabella.add(menuItemAggiungi);
		menuItemModifica = new JMenuItem("Modifica voce");
		menuTabella.add(menuItemModifica);
		menuItemRimuovi = new JMenuItem("Rimuovi voce");
		menuTabella.add(menuItemRimuovi);
		
		setJMenuBar(menuBar);
		
		//Eventi
		menuItemAggiungi.addActionListener(this);
		menuItemModifica.addActionListener(this);
		menuItemRimuovi.addActionListener(this);
		menuItemSalva.addActionListener(this);
		menuItemCarica.addActionListener(this);
		menuItemEsporta.addActionListener(this);
		menuItemStampa.addActionListener(this);
	}

	/**
	 * Metodo per la gestione dell'evento associato alla pressione dei bottoni.
	 * Cambia la scheda visualizzata.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case "Aggiungi voce":
				layout.show(panelCont,"PannelloAggiungi");
				break;
			case "Modifica voce":
				layout.show(panelCont,"PannelloModifica");
				break;
			case "Rimuovi voce":
				layout.show(panelCont,"PannelloRimuovi");
				break;
			case "Salva":
				layout.show(panelCont, "PannelloSalva");
				break;
			case "Carica":
				layout.show(panelCont, "PannelloCarica");
				break;
			case "Esporta":
				layout.show(panelCont, "PannelloEsporta");
				break;
			case "Stampa":
				panelCont.getPanIniz().stampaTabella();
				break;
		}
	}
	
	/**
	 * Metodo per spostarsi nelle schede dell'interfaccia grafica.
	 * @param scheda Nome della scheda in cui spostarsi
	 */
	public void cambiaScheda(String scheda) {
		layout.show(panelCont, scheda);
	}
}
