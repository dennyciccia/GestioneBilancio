/**
 * @file PannelloModifica.java
 * File contenente la classe PannelloModifica che gestisce l'interfaccia grafica per modificare una voce di bilancio.
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
import javax.swing.JSeparator;
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

public class PannelloModifica extends PannelloModificatore implements ActionListener {	
	//Componenti interfaccia
	private JLabel lblModifica, lblDataOld, lblDescrizioneOld, lblAmmontareOld;
	private JTextField txtDescrizioneOld;
	private JSpinner spDataOld, spAmmontareOld;
	private JLabel lblOld, lblNew;
	private JLabel lblDataNew, lblDescrizioneNew, lblAmmontareNew;
	private JTextField txtDescrizioneNew;
	private JSpinner spDataNew, spAmmontareNew;
	private JButton btnModifica, btnAnnulla;
	
	/**
	 * Costruttore dell'interfaccia, inizializza il riferimento al pannello iniziale con quello passato come parametro.
	 * @param panIniz Riferimento al {@link PannelloIniziale}
	 */
	public PannelloModifica(PannelloIniziale panIniz){
		super(panIniz);
		
		//Modelli spinner
		SpinnerDateModel dateModelOld = new SpinnerDateModel();
		SpinnerNumberModel numberModelOld = new SpinnerNumberModel(0.0, -MAX_AMMONTARE, MAX_AMMONTARE, 0.1);
		SpinnerDateModel dateModelNew = new SpinnerDateModel();
		SpinnerNumberModel numberModelNew = new SpinnerNumberModel(0.0, -MAX_AMMONTARE, MAX_AMMONTARE, 0.1);
		
		//Titolo
		JPanel panelTitolo = new JPanel();
		lblModifica = new JLabel("Modifica voce");
		panelTitolo.add(lblModifica);
		
		add(panelTitolo);
		
		//Campi input vecchia voce
		JPanel panelOld = new JPanel();
		lblOld = new JLabel("Vecchia voce");
		panelOld.add(lblOld);
		
		add(panelOld);
		
		JPanel panelDataOld = new JPanel();
		lblDataOld = new JLabel("Data:");
		panelDataOld.add(lblDataOld);
		spDataOld = new JSpinner(dateModelOld);
		panelDataOld.add(spDataOld);
		
		add(panelDataOld);
		
		JPanel panelDescrOld = new JPanel();
		lblDescrizioneOld = new JLabel("Descrizione:");
		panelDescrOld.add(lblDescrizioneOld);
		txtDescrizioneOld = new JTextField();
		panelDescrOld.add(txtDescrizioneOld);
		
		add(panelDescrOld);
		
		JPanel panelAmmontareOld = new JPanel();
		lblAmmontareOld = new JLabel("Ammontare: €");
		panelAmmontareOld.add(lblAmmontareOld);
		spAmmontareOld = new JSpinner(numberModelOld);
		panelAmmontareOld.add(spAmmontareOld);
		
		add(panelAmmontareOld);
		
		add(new JSeparator());
		
		//Campi input nuova voce
		JPanel panelNew = new JPanel();
		lblNew = new JLabel("Nuova voce");
		panelNew.add(lblNew);
		
		add(panelNew);
		
		JPanel panelDataNew = new JPanel();
		lblDataNew = new JLabel("Data:");
		panelDataNew.add(lblDataNew);
		spDataNew = new JSpinner(dateModelNew);
		panelDataNew.add(spDataNew);
		
		add(panelDataNew);
		
		JPanel panelDescrNew = new JPanel();
		lblDescrizioneNew = new JLabel("Descrizione:");
		panelDescrNew.add(lblDescrizioneNew);
		txtDescrizioneNew = new JTextField();
		panelDescrNew.add(txtDescrizioneNew);
		
		add(panelDescrNew);
		
		JPanel panelAmmontareNew = new JPanel();
		lblAmmontareNew = new JLabel("Ammontare: €");
		panelAmmontareNew.add(lblAmmontareNew);
		spAmmontareNew = new JSpinner(numberModelNew);
		panelAmmontareNew.add(spAmmontareNew);
		
		add(panelAmmontareNew);
				
		//Bottoni
		JPanel panelBtn = new JPanel();
		btnModifica = new JButton("Modifica");
		panelBtn.add(btnModifica);
		btnAnnulla = new JButton("Annulla");
		panelBtn.add(btnAnnulla);
		
		add(panelBtn);
		
		//Impostazioni allineamento
		panelOld.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelDataOld.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelDescrOld.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAmmontareOld.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelNew.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelDataNew.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelDescrNew.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAmmontareNew.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//Altre impostazioni componenti
		spDataOld.setEditor(new JSpinner.DateEditor(spDataOld, "dd/MM/yyyy"));
		
		txtDescrizioneOld.setColumns(20);

		JComponent spAmmontareOldEditor = spAmmontareOld.getEditor();
		JFormattedTextField ftfAmmontareOld = ((JSpinner.DefaultEditor) spAmmontareOldEditor).getTextField();
		ftfAmmontareOld.setColumns(5);

		spDataNew.setEditor(new JSpinner.DateEditor(spDataNew, "dd/MM/yyyy"));
		
		txtDescrizioneNew.setColumns(20);
		
		JComponent spAmmontareNewEditor = spAmmontareNew.getEditor();
		JFormattedTextField ftfAmmontareNew = ((JSpinner.DefaultEditor) spAmmontareNewEditor).getTextField();
		ftfAmmontareNew.setColumns(5);
		
		//Eventi
		btnModifica.addActionListener(this);
		btnAnnulla.addActionListener(this);
	}

	/**
	 * Metodo per gestire l'evento associato alla pressione di bottoni.
	 * In base al bottone premuto si modifica la voce o si torna indietro.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//In base al bottone premuto si modifica la voce o si torna indietro
		switch(e.getActionCommand()) {
			case "Modifica":{
					try {
						modificaVoce();
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
	 * Metodo per modificare una voce del bilancio richiamando il metodo modifica di {@link ElencoVoci}.
	 * @throws AmmontareNonValidoException se l'ammontare è uguale a 0
	 * @throws DescrizioneVuotaException se la descrizione è vuota
	 * @throws VoceNonPresenteException se la voce da modificare non è presente nell'elenco
	 */
	private void modificaVoce() throws AmmontareNonValidoException, DescrizioneVuotaException, VoceNonPresenteException {
		Date dataOld = (Date) spDataOld.getValue();
		String descrizioneOld = txtDescrizioneOld.getText();
		Double ammontareOld = (Double) spAmmontareOld.getValue();
		Date dataNew = (Date) spDataNew.getValue();
		String descrizioneNew = txtDescrizioneNew.getText();
		Double ammontareNew = (Double) spAmmontareNew.getValue();
		
		VoceDiBilancio vdbOld = new VoceDiBilancio(Data.parse(dataOld), descrizioneOld, ammontareOld);
		VoceDiBilancio vdbNew = new VoceDiBilancio(Data.parse(dataNew), descrizioneNew, ammontareNew);
		panIniz.getElencoVoci().modifica(vdbOld, vdbNew);
	}
}
