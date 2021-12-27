package it.univpm.progetto.exceptions;

import java.io.IOException;


/**
 * Classe utilizzata per gestire eventuali errori di inserimento di un'ora per filtrare i tweet
 * 
 * @author Ivan Pacenti
 *
 */
public class InvalidHourException extends IOException{
	private static final long serialVersionUID = 1L;
	/**
	 * Stampa la stringa inserita come messaggio,
	 * per specificare meglio l'errore commesso 
	 * dall'utente
	 * 
	 * @param msg messaggio personalizzato
	 */
	public InvalidHourException(String message) {
		super(message);
	}
	
	

}
