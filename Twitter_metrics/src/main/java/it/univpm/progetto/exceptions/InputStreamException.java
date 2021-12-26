/**
 * 
 */
package it.univpm.progetto.exceptions;

import java.io.IOException;
import java.net.MalformedURLException;


/**Classe che gestisce eventuali errori durante lo stream di lettura dall'API di Twitter
 * @author Ivan Pacenti
 *
 */
public class InputStreamException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public InputStreamException(String message) {
		super(message);
		
	}
	
}
