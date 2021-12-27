package it.univpm.progetto.service;

import java.io.IOException;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import it.univpm.progetto.exceptions.InputStreamException;


/**
 * Classe che implementa l'interfaccia {@link it.univpm.progetto.service.API}.
 * <p>
 * Viene utilizzato un metodo che ritorna un InputStream
 * 
 * @author Ivan Pacenti
 *
 */
public class APIImpl implements API{
	
	private InputStream is;

	/**
	 * Metodo che si connette all'indirizzo dell'API di twitter
	 * passato come parametro e restituisce uno stream di dati.
	 * 
	 * @return uno stream di dati
	 * @throw InputStreamException lancia una eccezione personalizzata in caso di problemi nella lettura dei dati in input
	 */
	@Override
	public InputStream getData(String address) throws InputStreamException
	{

		try {

			URL url=new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			is = conn.getInputStream();

		} catch (IOException e) 
		{
			throw new InputStreamException("The user has a private profile or is non-existent");
		}
		return is;
	}
}
