package it.univpm.progetto.exceptions;


/**
 * Classe che gestisce eventuali errori durante lo stream di lettura dall'API di Twitter
 * 
 * @author Ivan Pacenti
 *
 */
public class InputStreamException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Stampa la stringa inserita come messaggio,
	 * per specificare meglio l'errore commesso 
	 * dall'utente
	 * 
	 * @param msg messaggio personalizzato
	 */
	public InputStreamException(String message) {
		super(message);
		
	}
	
}
