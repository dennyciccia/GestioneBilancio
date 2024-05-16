/**
 * @file PannelloIniziale.java
 * File contenente la classe PannelloIniziale che gestisce l'interfaccia grafica contenente la tabella e le opzioni per gestire la sua visualizzazione e la ricerca di un elemento al suo interno.
 * Implementa le interfacce ItemListener, ChangeListener e ActionListener per gestire gli eventi generati dai radio buttons, dagli spinner e dai bottoni.
 */
package interfaccia;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dati.Data;
import dati.ElencoVoci;
import dati.VoceDiBilancio;
import salvataggioDati.AutosaveThread;

@SuppressWarnings("serial")

public class PannelloIniziale extends JPanel implements ItemListener, ChangeListener, ActionListener{
	private TableModel tableModel; ///< Modello per rappresentare la tabella
	private FrameIniziale frame; ///< Riferimento al {@link FrameIniziale}
	private ElencoVoci elencoVoci; ///< Riferimento all'elenco delle voci
	private ElencoVoci elencoTemp; ///< Elenco delle voci temporaneo
	
	//Variabili per la ricerca
	private String testoPrecedente;
	private int indice;
	private boolean elencoCambiato;
	
	//Componenti interfaccia
	private JTable table;
	private JLabel lblMostra, lblRicerca;
	private JRadioButton rbGiorno, rbSettimana, rbMese, rbAnno, rbDaa, rbTutto;
	private JSpinner spGiorno, spSettimana, spMese, spAnno, spDa, spA;
	private JTextField txtCerca;
	private JButton btnCerca;
	
	/**
	 * Costruttore dell'interfaccia grafica, inizializza i riferimenti all'elenco delle voci e al frame iniziale con quelli passati come parametro e avvia il thread per il salvataggio automatico. 
	 * @param elencoVoci Riferimento all'oggetto {@link ElencoVoci}
	 * @param frame Riferimento all'oggetto {@link FrameIniziale}
	 */
	public PannelloIniziale(ElencoVoci elencoVoci, FrameIniziale frame) {
		//Riferimento al frame iniziale e alla struttura dati
		this.frame = frame;
		this.elencoVoci = elencoVoci;
		
		//Elenco temporaneo per la visualizzazione
		elencoTemp = null;
		
		//Variabili per la ricerca
		testoPrecedente = null;
		elencoCambiato = false;
		
		//Avvio thread autosave
		AutosaveThread thread = new AutosaveThread(this);
		thread.start();
		
		//Layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Modelli per spinner
		SpinnerDateModel dateModelGiorno = new SpinnerDateModel();
		SpinnerDateModel dateModelSettimana = new SpinnerDateModel();
		SpinnerDateModel dateModelMese = new SpinnerDateModel();
		SpinnerDateModel dateModelAnno = new SpinnerDateModel();
		SpinnerDateModel dateModelDa = new SpinnerDateModel();
		SpinnerDateModel dateModelA = new SpinnerDateModel();
		
		//Pannello con tabella
		JPanel panelTable = new JPanel();
		tableModel = new TableModel(elencoVoci);
		table = new JTable(tableModel);
		panelTable.add(table);
		add(panelTable);
		
		add(new JSeparator());
		
		//Pannello con i controlli per gestire la visualizzazione
		JPanel panelShow = new JPanel();
		panelShow.setLayout(new BoxLayout(panelShow, BoxLayout.Y_AXIS));
		
		JPanel panelMostra = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lblMostra = new JLabel("Mostra");
		panelMostra.add(lblMostra);
		panelShow.add(panelMostra);
		
		JPanel panelGiorno = new JPanel();
		rbGiorno = new JRadioButton("Giorno:");
		panelGiorno.add(rbGiorno);
		spGiorno = new JSpinner(dateModelGiorno);
		panelGiorno.add(spGiorno);
		panelShow.add(panelGiorno);
		
		JPanel panelSettimana = new JPanel();
		rbSettimana = new JRadioButton("Settimana:");
		panelSettimana.add(rbSettimana);
		spSettimana = new JSpinner(dateModelSettimana);
		panelSettimana.add(spSettimana);
		panelShow.add(panelSettimana);
		
		JPanel panelMese = new JPanel();
		rbMese = new JRadioButton("Mese:");
		panelMese.add(rbMese);
		spMese = new JSpinner(dateModelMese);
		panelMese.add(spMese);
		panelShow.add(panelMese);

		JPanel panelAnno = new JPanel();
		rbAnno = new JRadioButton("Anno:");
		panelAnno.add(rbAnno);
		spAnno = new JSpinner(dateModelAnno);
		panelAnno.add(spAnno);
		panelShow.add(panelAnno);
		
		JPanel panelDaa = new JPanel();
		rbDaa = new JRadioButton("Da");
		panelDaa.add(rbDaa);
		spDa = new JSpinner(dateModelDa);
		panelDaa.add(spDa);
		JLabel lblA = new JLabel("a");
		panelDaa.add(lblA);
		spA = new JSpinner(dateModelA);
		panelDaa.add(spA);
		panelShow.add(panelDaa);
		
		JPanel panelTutto = new JPanel();
		rbTutto = new JRadioButton("Tutto");
		panelTutto.add(rbTutto);
		panelShow.add(panelTutto);
		
		add(panelShow);
		
		add(new JSeparator());
		
		//Pannello ricerca
		JPanel panelSearch = new JPanel();
		panelSearch.setLayout(new BoxLayout(panelSearch, BoxLayout.Y_AXIS));
		
		JPanel panelRicerca = new JPanel();
		lblRicerca = new JLabel("Ricerca");
		panelRicerca.add(lblRicerca);
		panelSearch.add(panelRicerca);
		
		JPanel panelText = new JPanel();
		txtCerca = new JTextField();
		panelText.add(txtCerca);
		btnCerca = new JButton("Cerca");
		panelText.add(btnCerca);				
		panelSearch.add(panelText);
		
		add(panelSearch);
		
		//Impostazioni allineamento
		panelMostra.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelGiorno.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelSettimana.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelMese.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAnno.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelDaa.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTutto.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelRicerca.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelText.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//Altre impostazioni componenti
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(rbGiorno);
		radioGroup.add(rbSettimana);
		radioGroup.add(rbMese);
		radioGroup.add(rbAnno);
		radioGroup.add(rbDaa);
		radioGroup.add(rbTutto);
		
		spGiorno.setEditor(new JSpinner.DateEditor(spGiorno, "dd/MM/yyyy"));
		spSettimana.setEditor(new JSpinner.DateEditor(spSettimana, "dd/MM/yyyy"));
		spMese.setEditor(new JSpinner.DateEditor(spMese, "MM/yyyy"));
		spAnno.setEditor(new JSpinner.DateEditor(spAnno, "yyyy"));
		spDa.setEditor(new JSpinner.DateEditor(spDa, "dd/MM/yyyy"));
		spA.setEditor(new JSpinner.DateEditor(spA, "dd/MM/yyyy"));
		
		spGiorno.setEnabled(false);
		spSettimana.setEnabled(false);
		spMese.setEnabled(false);
		spAnno.setEnabled(false);
		spDa.setEnabled(false);
		spA.setEnabled(false);
		
		rbTutto.setSelected(true);
		
		txtCerca.setColumns(20);
		
		//Eventi
		rbGiorno.addItemListener(this);
		rbSettimana.addItemListener(this);
		rbMese.addItemListener(this);
		rbAnno.addItemListener(this);
		rbDaa.addItemListener(this);
		rbTutto.addItemListener(this);
		
		spGiorno.addChangeListener(this);
		spSettimana.addChangeListener(this);
		spMese.addChangeListener(this);
		spAnno.addChangeListener(this);
		spDa.addChangeListener(this);
		spA.addChangeListener(this);
		
		btnCerca.addActionListener(this);
	}
	
	/**
	 * Metodo per notificare al tableModel che il contenuto della tabella è cambiato e per riadattare le dimensioni del frame.
	 */
	public void tableChanged() {
		//Notifica che la tabella è cambiata
		tableModel.fireTableDataChanged();
		
		//Ridimensiona il frame iniziale
		frame.pack();
	}
	
	/**
	 * Getter del riferimento al {@link FrameIniziale}.
	 * @return Riferimento al {@link FrameIniziale}
	 */
	public FrameIniziale getFrame() {
		return frame;
	}

	/**
	 * Getter del radio button per visualizzare tutto il contenuto della tabella.
	 * @return L'oggetto JRadioButton
	 */
	public JRadioButton getRbTutto() {
		return rbTutto;
	}
	
	/**
	 * Getter del modello per gestire la tabella.
	 * @return L'oggetto TableModel
	 */
	public TableModel getTableModel() {
		return tableModel;
	}
	
	/**
	 * Getter dell'elenco delle voci.
	 * @return Riferimento all'oggetto {@link ElencoVoci}
	 */
	public synchronized ElencoVoci getElencoVoci() {
		return elencoVoci;
	}
	
	/**
	 * Setter dell'elenco delle voci.
	 * @param elencoVoci Oggetto {@link ElencoVoci}
	 */
	public void setElencoVoci(ElencoVoci elencoVoci) {
		this.elencoVoci = elencoVoci;
		tableModel.setElencoVoci(elencoVoci);
		tableChanged();
	}
	
	/**
	 * Metodo per stampare la tabella tramite una stampante richiamando il metodo print() della tabella.
	 */
	public void stampaTabella() {
		try {
			table.print();
		} catch (PrinterException e) {
			JOptionPane.showMessageDialog(getFrame(), e.getMessage(), "Errore", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
	}

	/**
	 * Metodo per gestire l'evento associato alla pressione di un Radio Button.
	 * In base al bottone premuto si crea una struttura che contiene solo le voci da visualizzare.
	 * In base al contenuto degli spinner si decide l'intervallo di giorni da mostrare nella tabella.
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getSource();
		
		if(source.equals(rbGiorno)) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				spGiorno.setEnabled(true);
				Data giorno = Data.parse((Date) spGiorno.getValue());
				visualizza(giorno,giorno);
			} else {
				spGiorno.setEnabled(false);
			}
		}
		
		if(source.equals(rbSettimana)) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				spSettimana.setEnabled(true);
				Data giornoInizio = Data.parse((Date) spSettimana.getValue());
				Data giornoFine = Data.parse((Date) spSettimana.getValue());
				giornoFine.addDays(6);
				visualizza(giornoInizio,giornoFine);
			} else {
				spSettimana.setEnabled(false);
			}
		}
		
		if(source.equals(rbMese)) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				spMese.setEnabled(true);
				Data giornoInizio = Data.parse((Date) spMese.getValue());
				giornoInizio.setDay(1);
				Data giornoFine = Data.parse((Date) spMese.getValue());
				giornoFine.setDay(1);
				giornoFine.addMonths(1);
				giornoFine.addDays(-1);
				visualizza(giornoInizio,giornoFine);
			} else {
				spMese.setEnabled(false);
			}
		}
		
		if(source.equals(rbAnno)) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				spAnno.setEnabled(true);
				Data giornoInizio = Data.parse((Date) spAnno.getValue());
				giornoInizio.setDay(1);
				giornoInizio.setMonth(1);
				Data giornoFine = Data.parse((Date) spAnno.getValue());
				giornoFine.setDay(1);
				giornoFine.setMonth(1);
				giornoFine.addYears(1);
				giornoFine.addDays(-1);
				visualizza(giornoInizio,giornoFine);
			} else {
				spAnno.setEnabled(false);
			}
		}
		
		if(source.equals(rbDaa)) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				spDa.setEnabled(true);
				spA.setEnabled(true);
				Data giornoInizio = Data.parse((Date) spDa.getValue());
				Data giornoFine = Data.parse((Date) spA.getValue());
				visualizza(giornoInizio,giornoFine);
			} else {
				spDa.setEnabled(false);
				spA.setEnabled(false);
			}
		}
		
		if(source.equals(rbTutto)) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				tableModel.setElencoVoci(elencoVoci);
				elencoTemp = null;
				tableChanged();
				elencoCambiato = true;
			}
		}
	}

	/**
	 * Metodo per gestire l'evento associato al cambiamento del contenuto degli Spinner.
	 * In base al bottone premuto si crea una struttura che contiene solo le voci da visualizzare.
	 * In base al contenuto degli spinner si decide l'intervallo di giorni da mostrare nella tabella.
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		Object source = e.getSource();
		
		if(source.equals(spGiorno)) {
			Data giorno = Data.parse((Date) spGiorno.getValue());
			visualizza(giorno,giorno);
		}
		
		if(source.equals(spSettimana)) {
			Data giornoInizio = Data.parse((Date) spSettimana.getValue());
			Data giornoFine = Data.parse((Date) spSettimana.getValue());
			giornoFine.addDays(6);
			visualizza(giornoInizio,giornoFine);
		}
		
		if(source.equals(spMese)) {
			Data giornoInizio = Data.parse((Date) spMese.getValue());
			giornoInizio.setDay(1);
			Data giornoFine = Data.parse((Date) spMese.getValue());
			giornoFine.setDay(1);
			giornoFine.addMonths(1);
			giornoFine.addDays(-1);
			visualizza(giornoInizio,giornoFine);
		}
		
		if(source.equals(spAnno)) {
			Data giornoInizio = Data.parse((Date) spAnno.getValue());
			giornoInizio.setDay(1);
			giornoInizio.setMonth(1);
			Data giornoFine = Data.parse((Date) spAnno.getValue());
			giornoFine.setDay(1);
			giornoFine.setMonth(1);
			giornoFine.addYears(1);
			giornoFine.addDays(-1);
			visualizza(giornoInizio,giornoFine);
		}
		
		if(source.equals(spDa)) {
			Data giornoInizio = Data.parse((Date) spDa.getValue());
			Data giornoFine = Data.parse((Date) spA.getValue());
			visualizza(giornoInizio,giornoFine);
		}
		
		if(source.equals(spA)) {
			Data giornoInizio = Data.parse((Date) spDa.getValue());
			Data giornoFine = Data.parse((Date) spA.getValue());
			visualizza(giornoInizio,giornoFine);
		}
	}

	/**
	 * Metodo per visualizzare i contenuti della tabella in un dato intervallo di giorni.
	 * @param giornoInizio Oggetto {@link Data} rappresentante il primo giorno dell'intervallo
	 * @param giornoFine Oggetto {@link Data} rappresentante l'ultimo giorno dell'intervallo
	 */
	private void visualizza(Data giornoInizio, Data giornoFine) {
		elencoTemp = new ElencoVoci();
		
		for(int i=0; i<elencoVoci.length(); i++) {
			VoceDiBilancio vdb = elencoVoci.get(i);
			if((vdb.getData().after(giornoInizio) && vdb.getData().before(giornoFine)) || vdb.getData().equals(giornoInizio) || vdb.getData().equals(giornoFine))
				elencoTemp.aggiungi(vdb);
		}
		
		tableModel.setElencoVoci(elencoTemp);
		tableChanged();
		elencoCambiato = true;
	}

	/**
	 * Metodo per gestire l'evento associato alla pressione del bottone.
	 * Implementa la ricerca di una voce nella tabella.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String testo = txtCerca.getText();
		ArrayList<Integer> listaIndici;
		
		if(elencoTemp != null)
			listaIndici = elencoTemp.cerca(testo);
		else
			listaIndici = elencoVoci.cerca(testo);
		
		//Azzera la selezione
		table.removeRowSelectionInterval(0, tableModel.getRowCount()-1);
		
		//Se il testo attuale non è uguale al testo precedente o se l'elenco è cambiato
		//evidenzia il primo elemento della nuova ricerca e imposta il testo precedente
		if(!testo.equals(testoPrecedente) || elencoCambiato){
			indice = 0;
			if(!listaIndici.isEmpty()) {
				table.addRowSelectionInterval(listaIndici.get(indice)+1, listaIndici.get(indice)+1);	
				testoPrecedente = testo;				
			}
			
		//Altrimenti vai avanti nella lista di indici
		} else {
			//Se il prossimo indice è fuori dal range torna all'inizio
			if(indice + 1 == listaIndici.size())
				indice = 0;
			else
				indice++;
			
			table.addRowSelectionInterval(listaIndici.get(indice)+1, listaIndici.get(indice)+1);
		}
		
		elencoCambiato = false;
	}
}
