/**
 * @file Data.java
 * File contenente la classe Data usata per gestire le date considerando solo il giorno, il mese e l'anno
 * Implementa l'interfaccia Serializable per poter essere serializzato
 */
package dati;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import errori.DataNonValidaException;

@SuppressWarnings("serial")

public class Data implements Serializable {
	/**
	 * Attributo usato per memorizzare la data completa
	 */
	private Calendar cal;

	/**
	 * Costruttore senza parametri
	 * Inizializza cal con un'istanza di Calendar della data odierna
	 */
	public Data() {
		cal = Calendar.getInstance();
	}

	/**
	 * Costruttore con parametro
	 * Inizializza cal con l'istanza di Calendar data
	 * 
	 * @param cal Istanza di Calendar
	 */
	public Data(Calendar cal) {
		this.cal = cal;
	}

	/**
	 * Costruttore con parametri
	 * Inizializza cal con un'istanza di Calendar e imposta il giorno, il mese e l'anno con i valori dati
	 * 
	 * @param day   Giorno da impostare
	 * @param month Mese da impostare
	 * @param year  Anno da impostare
	 */
	public Data(int day, int month, int year) {
		cal = Calendar.getInstance();

		setDay(day);
		setMonth(month);
		setYear(year);
	}

	/**
	 * Getter del giorno del mese
	 * @return Valore intero del giorno del mese
	 */
	public int getDay() {
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Setter del giorno del mese
	 * @param day Valore intero del giorno del mese
	 * 
	 * @throws DataNonValidaException se il giorno è minore di 1 o maggiore di 31
	 */
	public void setDay(int day) {
		if (day >= 1 && day <= 31)
			cal.set(Calendar.DAY_OF_MONTH, day);
		else
			throw new DataNonValidaException();
	}

	/**
	 * Getter del mese
	 * @return Valore intero del mese
	 */
	public int getMonth() {
		return cal.get(Calendar.MONTH);
	}

	/**
	 * Settere del mese
	 * @param month Valore intero del mese
	 * @throws DataNonValidaException se il mese è minore di 1 o maggiore di 31
	 */
	public void setMonth(int month) {
		if (month >= 1 && month <= 12)
			cal.set(Calendar.MONTH, month - 1);
		else
			throw new DataNonValidaException();
	}

	/**
	 * Getter dell'anno
	 * @return Valore intero dell'anno
	 */
	public int getYear() {
		return cal.get(Calendar.YEAR);
	}

	/**
	 * Setter dell'anno
	 * @param year Valore intero dell'anno
	 */
	public void setYear(int year) {
		cal.set(Calendar.YEAR, year);
	}

	/**
	 * Metodo per verificare che l'istanza corrente della data sia uguale a un'altra data
	 * Vengono confrontati solo il giorno, il mese e l'anno delle due date
	 * @param anotherData Data da confrontare
	 * @return true se le date sono uguali, false se le date sono diverse
	 */
	public boolean equals(Data anotherData) {
		boolean day = this.getDay() == anotherData.getDay();
		boolean month = this.getMonth() == anotherData.getMonth();
		boolean year = this.getYear() == anotherData.getYear();

		return day && month && year;
	}

	/**
	 * Metodo per verificare se l'istanza corrente della data viene prima di un altra data
	 * Vengono confrontati il giorno, il mese e l'anno delle due date
	 * @param anotherData Data da confrontare
	 * @return true se la condizione è verificata, false altrimenti
	 */
	public boolean before(Data anotherData) {
		// Si guarda l'anno
		if (getYear() < anotherData.getYear())
			return true;
		else if (getYear() > anotherData.getYear())
			return false;
		else {
			// Si guarda il mese
			if (getMonth() < anotherData.getMonth())
				return true;
			else if (getMonth() > anotherData.getMonth())
				return false;
			else {
				// Si guarda il giorno
				if (getDay() < anotherData.getDay())
					return true;
				else if (getDay() > anotherData.getDay())
					return false;
				else
					return false;
			}
		}
	}

	/**
	 * Metodo per verificare se l'istanza corrente della data viene dopo di un altra data
	 * Vengono confrontati il giorno, il mese e l'anno delle due date
	 * @param anotherData Data da confrontare
	 * @return true se la condizione è verificata, false altrimenti
	 */
	public boolean after(Data anotherData) {
		// Si guarda l'anno
		if (getYear() > anotherData.getYear())
			return true;
		else if (getYear() < anotherData.getYear())
			return false;
		else {
			// Si guarda il mese
			if (getMonth() > anotherData.getMonth())
				return true;
			else if (getMonth() < anotherData.getMonth())
				return false;
			else {
				// Si guarda il giorno
				if (getDay() > anotherData.getDay())
					return true;
				else if (getDay() < anotherData.getDay())
					return false;
				else
					return false;
			}
		}
	}

	/**
	 * Metodo per aggiungere giorni alla data
	 * @param offset Numero di giorni da aggiungere
	 */
	public void addDays(int offset) {
		cal.add(Calendar.DAY_OF_MONTH, offset);
	}

	/**
	 * Metodo per aggiungere mesi alla data
	 * @param offset Numero di mesi da aggiungere
	 */
	public void addMonths(int offset) {
		cal.add(Calendar.MONTH, offset);
	}

	/**
	 * Metodo per aggiungere anni alla data
	 * @param offset Numero di anni da aggiungere
	 */
	public void addYears(int offset) {
		cal.add(Calendar.YEAR, offset);
	}

	/**
	 * Metodo per trasformare la data in stringa nel formato gg/mm/aaaa 
	 * @return Stringa rappresentate la data
	 */
	@Override
	public String toString() {
		return getDay() + "/" + (getMonth() + 1) + "/" + getYear();
	}

	/**
	 * Metodo statico per convertire un'istanza di Date in un'istanza di Data
	 * Viene creata un'istanza di Calendar impostando la data passata come parametro
	 * @param date Istanza di Date da convertire in Data
	 * @return istanza di Data rappresentante la stessa data passata come parametro
	 */
	public static Data parse(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return new Data(cal);
	}
}
