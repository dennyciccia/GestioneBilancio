/**
 * @file PannelloRimuovi.java
 * File contenente la classe PannelloRimuovi che gestisce l'interfaccia grafica per rimuovere una voce di bilancio.
 * Implementa l'interfaccia ActionListener per gestire gli eventi associati alla pressione dei bottoni.
 */
package modificaDati;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import dati.Data;
import dati.ElencoVoci;
import dati.VoceDiBilancio;
import errori.AmmontareNonValidoException;
import errori.DescrizioneVuotaException;
import errori.VoceNonPresenteException;
import interfaccia.PannelloIniziale;

@SuppressWarnings("serial")

public class PannelloRimuovi extends PannelloModificatore implements ActionListener {
	//Componenti interfaccia
	private JLabel lblRimuovi, lblData, lblDescrizione, lblAmmontare;
	private JTextField txtDescrizione;
	private JSpinner spData, spAmmontare;
	private JButton btnRimuovi, btnAnnulla;
	
	/**
	 * Costruttore dell'interfaccia, inizializza il riferimento al pannello iniziale con quello passato come parametro.
	 * @param panIniz Riferimento al {@link PannelloIniziale}
	 */
	public PannelloRimuovi(PannelloIniziale panIniz){
		super(panIniz);
		
		//Modelli spinner
		SpinnerDateModel dateModel = new SpinnerDateModel();
		SpinnerNumberModel numberModel = new SpinnerNumberModel(0.0, -MAX_AMMONTARE, MAX_AMMONTARE, 0.1);
		
		//Titolo
		JPanel panelTitolo = new JPanel();
		lblRimuovi = new JLabel("Rimuovi voce");
		panelTitolo.add(lblRimuovi);
		
		add(panelTitolo);
		
		//Campi input
		JPanel panelData = new JPanel();
		lblData = new JLabel("Data:");
		panelData.add(lblData);
		spData = new JSpinner(dateModel);
		panelData.add(spData);
		
		add(panelData);
		
		JPanel panelDescr = new JPanel();
		lblDescrizione = new JLabel("Descrizione:");
		panelDescr.add(lblDescrizione);
		txtDescrizione = new JTextField();
		panelDescr.add(txtDescrizione);
		
		add(panelDescr);
		
		JPanel panelAmmontare = new JPanel();
		lblAmmontare = new JLabel("Ammontare: €");
		panelAmmontare.add(lblAmmontare);
		spAmmontare = new JSpinner(numberModel);
		panelAmmontare.add(spAmmontare);
		
		add(panelAmmontare);
				
		//Bottoni
		JPanel panelBtn = new JPanel();
		btnRimuovi = new JButton("Rimuovi");
		panelBtn.add(btnRimuovi);
		btnAnnulla = new JButton("Annulla");
		panelBtn.add(btnAnnulla);
		
		add(panelBtn);
		
		//Impostazioni allineamento
		panelData.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelDescr.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAmmontare.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//Altre impostazioni componenti
		spData.setEditor(new JSpinner.DateEditor(spData, "dd/MM/yyyy"));
		
		txtDescrizione.setColumns(20);

		JComponent spAmmontareEditor = spAmmontare.getEditor();
		JFormattedTextField ftfAmmontare = ((JSpinner.DefaultEditor) spAmmontareEditor).getTextField();
		ftfAmmontare.setColumns(5);
		
		//Eventi
		btnRimuovi.addActionListener(this);
		btnAnnulla.addActionListener(this);
		
	}

	/**
	 * Metodo per gestire l'evento associato alla pressione di bottoni.
	 * In base al bottone premuto si rimuove la voce o si torna indietro.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//In base al bottone premuto si aggiunge la voce o si torna indietro
		switch(e.getActionCommand()) {
			case "Rimuovi":{
				try {
					rimuoviVoce();
					panIniz.tableChanged();
					panIniz.getRbTutto().setSelected(true);
					panIniz.getFrame().cambiaScheda("PannelloIniziale"); //Ritorna alla home
				} catch (AmmontareNonValidoException | DescrizioneVuotaException | VoceNonPresenteException e1) {
					JOptionPane.showMessageDialog(panIniz.getFrame(), e1.getMessage(), "Errore", JOptionPane.WARNING_MESSAGE);
				}
			}
				break;
			case "Annulla":
				panIniz.getFrame().cambiaScheda("PannelloIniziale");	
				break;
		}	
	}
	
	/**
	 * Metodo per rimuovere una voce dal bilancio richiamando il metodo rimuovi di {@link ElencoVoci}.
	 * @throws AmmontareNonValidoException se l'ammontare è uguale a 0
	 * @throws DescrizioneVuotaException se la descrizione è vuota
	 * @throws VoceNonPresenteException se la voce da rimuovere non è presente nell'elenco
	 */
	private void rimuoviVoce() throws AmmontareNonValidoException, DescrizioneVuotaException, VoceNonPresenteException {
		Date data = (Date) spData.getValue();
		String descrizione = txtDescrizione.getText();
		Double ammontare = (Double) spAmmontare.getValue();
		
		VoceDiBilancio vdb = new VoceDiBilancio(Data.parse(data), descrizione, ammontare);
		panIniz.getElencoVoci().rimuovi(vdb);
	}
}
