package it.univpm.progetto.exceptions;


/**
 * Classe utilizzata per gestire eventuali liste vuote in output.
 * 
 * @author Ivan Pacenti
 *
 */
public class EmptyCollectionListException extends Exception {
	
	private static final long serialVersionUID = 1L;

		/**
		 * Stampa la stringa inserita come messaggio,
		 * per specificare meglio l'errore commesso 
		 * dall'utente.
		 * 
		 * @param msg messaggio personalizzato.
		 */
		public EmptyCollectionListException(String msg)
		{
			super (msg);
		}

}
