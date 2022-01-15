package it.univpm.progetto.exceptions;



/**
 * Classe utilizzata per gestire eventuali errori di inserimento di una data per filtrare i tweet.
 * 
 * @author ivan
 *
 */
public class InvalidDateException extends Exception{
private static final long serialVersionUID = 1L;
	
	/**
	 * Stampa la stringa inserita come messaggio,
	 * per specificare meglio l'errore commesso 
	 * dall'utente.
	 * 
	 * @param s messaggio personalizzato.
	 */
	public InvalidDateException(String s) {
		super(s);
		
	}

}
