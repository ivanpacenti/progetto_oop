package it.univpm.progetto.service;

import java.io.InputStream;


import it.univpm.progetto.exceptions.InputStreamException;


/**
 * @author ivan
 * @param <T>
 *
 */
public interface API {
	
	InputStream getData(String address) throws InputStreamException ;	
}
