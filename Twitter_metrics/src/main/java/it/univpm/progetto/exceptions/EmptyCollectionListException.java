/**
 * 
 */
package it.univpm.progetto.exceptions;


/**Classe utilizzata per gestire eventuali liste vuote in output
 * @author Ivan Pacenti
 *
 */
public class EmptyCollectionListException extends Exception {
	
	private static final long serialVersionUID = 1L;

		public EmptyCollectionListException(String msg)
		{
			super (msg);
		}

}
