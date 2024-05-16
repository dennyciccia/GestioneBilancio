/**
 * @file PannelloSalvaCarica.java
 * File contenente la classe astratta PannelloSalvaCarica che rappresenta un'interfaccia grafica per salvare o caricare il bilancio.
 * Implementa l'interfaccia ActionListener per gestire gli eventi associati alla pressione dei bottoni.
 */
package salvataggioDati;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interfaccia.PannelloIniziale;

@SuppressWarnings("serial")

public abstract class PannelloSalvaCarica extends JPanel implements ActionListener{
	protected PannelloIniziale panIniz; ///< Riferimento al {@link PannelloIniziale}
	
	//Componenti interfaccia
	private JLabel lblTitolo, lblNomeFile;
	protected JTextField txtNomeFile;
	protected JButton btnSalvaCarica;
	private JButton btnAnnulla;
	
	/**
	 * Costruttore dell'interfaccia, inizializza il riferimento al pannello iniziale con quello passato come parametro.
	 * @param panIniz Riferimento al {@link PannelloIniziale}
	 */
	public PannelloSalvaCarica(PannelloIniziale panIniz) {
		//Riferimento al frame iniziale
		this.panIniz = panIniz;
		
		//Determinazione tipo di istanza
		String tipo = this instanceof PannelloSalva ? "Salva" : "Carica";
		
		//Layout
		setLayout(new BorderLayout());
		
		//Titolo
		JPanel panelTitolo = new JPanel();
		lblTitolo = new JLabel(tipo + " bilancio");
		panelTitolo.add(lblTitolo);
		
		add(panelTitolo, BorderLayout.NORTH);
		
		//Input
		JPanel panelC = new JPanel();
		panelC.setLayout(new BorderLayout());
		
		JPanel panelInput = new JPanel();
		lblNomeFile = new JLabel("Nome file: ");
		panelInput.add(lblNomeFile);
		txtNomeFile = new JTextField();
		panelInput.add(txtNomeFile);
		
		panelC.add(panelInput, BorderLayout.NORTH);
		
		//Bottoni
		JPanel panelBtn = new JPanel();
		btnSalvaCarica = new JButton(tipo);
		panelBtn.add(btnSalvaCarica);
		btnAnnulla = new JButton("Annulla");
		panelBtn.add(btnAnnulla);
		
		panelC.add(panelBtn, BorderLayout.CENTER);
		
		add(panelC, BorderLayout.CENTER);
		
		//Impostazioni allineamento
		panelInput.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//Altre impostazioni componenti
		txtNomeFile.setColumns(20);
		
		//Eventi
		btnSalvaCarica.addActionListener(this);
		btnAnnulla.addActionListener(this);
	}
	
	/**
	 * Metodo per la gestione dell'evento associato alla pressione di un bottone.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Annulla"))
			panIniz.getFrame().cambiaScheda("PannelloIniziale");
	}
}
