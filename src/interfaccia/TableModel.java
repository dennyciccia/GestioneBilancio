/**
 * @file TableModel.java
 * File contenente la classe TableModel che gestisce la tabella.
 */
package interfaccia;

import java.text.DecimalFormat;

import javax.swing.table.AbstractTableModel;
import dati.ElencoVoci;

@SuppressWarnings("serial")

public class TableModel extends AbstractTableModel{
	private String[] columnNames = {"Data", "Descrizione", "Ammontare"}; ///< Nomi delle colonne
	private ElencoVoci elencoVoci; ///< Riferimento all'elenco delle voci
	private DecimalFormat df = new DecimalFormat("0.00"); ///< Formato di visualizzazione dei Double

	/**
	 * Costruttore con parametri.
	 * Inizializza l'elenco delle voci con quello passato come parametro.
	 * @param elencoVoci Riferimento allì oggetto {@link ElencoVoci}
	 */
	public TableModel(ElencoVoci elencoVoci) {   
		this.elencoVoci = elencoVoci;
	}
	
	/**
	 * Metodo per ottenere il numero di righe della tabella (dim. elenco + 1 riga di intestazione + 1 riga del totale).
	 * @return Numero di righe della tabella
	 */
	@Override
	public int getRowCount() {
		return elencoVoci.length() + 2;
	}

	/**
	 * Metodo per ottenere il numero di colonne della tabella.
	 * @return Numero di colonne della tabella
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * Metodo per ottenere il valore del campo in una data posizione.
	 * @param rowIndex Indice della riga dell'elemento
	 * @param columnIndex Indice della colonna dell'elemento
	 * @return Valore del campo
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		//Prima riga con i titoli
		if(rowIndex == 0) {
			return columnNames[columnIndex];
		
		//Ultima riga con il totale
		} else if(rowIndex == elencoVoci.length() + 1) {
			switch(columnIndex) {
				case 0:
					return new String("");
				case 1:
					return new String("Totale:");
				case 2:
					return new String(df.format(elencoVoci.sommaAmmontare()));
				default:
					return null;
			}
		
		//Altre righe con le voci del bilancio
		} else {
			switch(columnIndex) {
				case 0:
					return elencoVoci.get(rowIndex-1).getData();
				case 1:
					return elencoVoci.get(rowIndex-1).getDescrizione();
				case 2:
					return df.format(elencoVoci.get(rowIndex-1).getAmmontare());
				default:
					return null;
			}
		}
	}
	
	/**
	 * Setter del riferimento all'elenco delle voci.
	 * @param elencoVoci Riferimento all'oggetto {@link ElencoVoci}
	 */
	public void setElencoVoci(ElencoVoci elencoVoci) {
		this.elencoVoci = elencoVoci;
	}
}
