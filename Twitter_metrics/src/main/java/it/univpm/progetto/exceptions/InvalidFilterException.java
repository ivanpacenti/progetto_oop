package it.univpm.progetto.exceptions;


/**Classe utilizzata per gestire eventuali inserimenti di campi invalidi per filtrare i tweet
 * @author Ivan Pacenti
 *
 */
public class InvalidFilterException extends Exception{


	private static final long serialVersionUID = 1L;

	public InvalidFilterException(String message) {
		super(message);
	}

}
