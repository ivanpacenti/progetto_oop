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
	
	/**
	 * Metodo che si connette all'indirizzo dell'API di twitter
	 * passato come parametro e restituisce uno stream di dati.
	 * 
	 * @return uno stream di dati
	 * @throw InputStreamException lancia una eccezione personalizzata in caso di problemi nella lettura dei dati in input
	 */
	InputStream getData(String address) throws InputStreamException ;	
}
