/**
 * @file PannelloEsporta.java
 * File contenente la classe PannelloEsporta che gestisce l'interfaccia grafica per esportare il bilancio.
 * Implementa l'interfaccia ActionListener per gestire gli eventi associati alla pressione di bottoni.
 */
package esportazioneDati;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interfaccia.PannelloIniziale;

@SuppressWarnings("serial")

public class PannelloEsporta extends JPanel implements ActionListener{
	private final String[] estensioni = {"CSV", "txt"}; ///< Estensioni in cui è possibile esportare
	
	private PannelloIniziale panIniz; ///< Riferimento al {@link PannelloIniziale}
	private JFileChooser chooser; ///< Interfaccia per scegliere il percorso dove salvare il file
	private Esportatore exporter; ///< Oggetto che si occupa di esportare

	//Componenti interfaccia
	private JLabel lblTitolo, lblFormato;
	private JComboBox<String> cbFormato;
	private JButton btnEsporta, btnAnnulla;
	
	/**
	 * Costruttore dell'interfaccia, inzializza il rierimento al pannello iniziale con quello passato come parametro
	 * @param panIniz Riferimento al {@link PannelloIniziale}
	 */
	public PannelloEsporta(PannelloIniziale panIniz) {
		//Riferimento al pannello iniziale
		this.panIniz = panIniz;
		
		//Layout
		setLayout(new BorderLayout());
		
		//Titolo
		JPanel panelTitolo = new JPanel();
		lblTitolo = new JLabel("Esporta bilancio");
		panelTitolo.add(lblTitolo);
		
		add(panelTitolo, BorderLayout.NORTH);
		
		JPanel panelC = new JPanel();
		panelC.setLayout(new BorderLayout());
		
		//Selezione formato
		JPanel panelFormato = new JPanel();
		lblFormato = new JLabel("Formato:");
		cbFormato = new JComboBox<String>(estensioni);
		panelFormato.add(lblFormato);
		panelFormato.add(cbFormato);
		
		panelC.add(panelFormato, BorderLayout.NORTH);
		
		//Bottoni
		JPanel panelBtn = new JPanel();
		btnEsporta = new JButton("Esporta");
		btnAnnulla = new JButton("Annulla");
		panelBtn.add(btnEsporta);
		panelBtn.add(btnAnnulla);
		
		panelC.add(panelBtn, BorderLayout.CENTER);
		
		add(panelC, BorderLayout.CENTER);
		
		//Altre impostazioni componenti
		chooser = new JFileChooser();
		chooser.setDialogTitle("Export");
		chooser.setApproveButtonText("Export in this folder");
		chooser.setApproveButtonToolTipText("Export in this folder");
		
		//Eventi
		btnEsporta.addActionListener(this);
		btnAnnulla.addActionListener(this);
	}

	/**
	 * Metodo per la gestione dell'evento associato alla pressione dei bottoni
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Esporta":
			esporta();
			break;
		case "Annulla":
			panIniz.getFrame().cambiaScheda("PannelloIniziale");
			break;
		}
	}
	
	/**
	 * Metodo per far scegliere la directory in cui esportare il file, crea l'istanza di {@link Esportatore} specializzata nel formato richiesto e esporta il bilancio
	 */
	private void esporta() {
		if(chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			String nomefile = chooser.getSelectedFile().getAbsolutePath() + "." + cbFormato.getSelectedItem().toString().toLowerCase();
			
			if(cbFormato.getSelectedItem().equals("CSV")) {
				exporter = new EsportatoreCSV(nomefile, panIniz.getElencoVoci());
				exporter.esporta();
			} else if(cbFormato.getSelectedItem().equals("txt")) {
				exporter = new EsportatoreTxt(nomefile, panIniz.getElencoVoci());
				exporter.esporta();
			}
			
			JOptionPane.showMessageDialog(panIniz.getFrame(), "Bilancio esportato in " + nomefile, "Esportazione", JOptionPane.INFORMATION_MESSAGE);
			panIniz.getFrame().cambiaScheda("PannelloIniziale");
		}
	}
}
