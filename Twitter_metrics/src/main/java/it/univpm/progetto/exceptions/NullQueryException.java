/**
 * 
 */
package it.univpm.progetto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author ivan
 *
 */
public class NullQueryException extends ResponseStatusException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public  NullQueryException(HttpStatus message,String mes) {
		super(message,mes);
	}
	
	
}
