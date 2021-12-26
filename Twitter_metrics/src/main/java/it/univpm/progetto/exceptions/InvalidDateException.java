/**
 * 
 */
package it.univpm.progetto.exceptions;



/**Classe utilizzata per gestire eventuali errori di inserimento di una data per filtrare i tweet
 * @author ivan
 *
 */
public class InvalidDateException extends Exception{


	private static final long serialVersionUID = 1L;

	public InvalidDateException(String s) {
		super(s);
		
	}

}
