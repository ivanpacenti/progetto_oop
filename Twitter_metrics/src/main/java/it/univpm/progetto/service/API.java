package it.univpm.progetto.service;

import java.io.InputStream;


import it.univpm.progetto.exceptions.InputStreamException;



/**
 * Interfaccia conenente un motodo per il download dei dati 
 * dall'API di twitter
 * 
 * @author Ivan Pacenti
 *
 */
public interface API {
	
	InputStream getData(String address) throws InputStreamException ;	
}
