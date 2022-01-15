package it.univpm.progetto.filter;

import java.text.ParseException;

import java.util.List;
import java.util.Map;

import it.univpm.progetto.exceptions.EmptyCollectionListException;
import it.univpm.progetto.exceptions.InvalidDateException;
import it.univpm.progetto.exceptions.InvalidFilterException;
import it.univpm.progetto.exceptions.InvalidHourException;
import it.univpm.progetto.model.Tweet;



/**
 * Interfaccia contenente metodi per il filtraggio.<p>
 * Implementata in DataService.
 * 
 * @author Ivan Pacenti
 *
 */
public interface Filter{
	
	/**
	 * Metodo che passa i valori alla classe FilterUtils, controllando che il valore insierito come parametro sia numerico.
	 * 
	 * @param fieldName Campo del tweet che vogliamo utilizzare come filtro.
	 * @param operator Operatore per filtrare, accettati solo > < ==.
	 * @param str Stringa contenente valore per filtrare i dati, sarà successivamente convertita eventualmente in formato numerico.
	 * @return Lista di oggetti di tipo Tweet filtrati.
	 * @throws EmptyCollectionListException Eccezione personalizzata per gestire l'output di eventuali liste vuote.
	 * @throws InvalidFilterException Eccezione personalizzata per gestire eventuali campi field invalidi.
	 *
	 */
	List<Tweet> filterField(String fieldName, String operator, String str) throws InvalidFilterException, EmptyCollectionListException;
	/**
	 * Metodo che restituisce una tabella con una lista di tweet analizzati e le relative statistiche.
	 * 
	 * @param from_hour Accetta solo valori del tipo HH, inserire per visualizzare i tweet creati dopo l'ora inserita.
	 * @param to_hour Accetta solo valori del tipo HH, inserire per visualizzare i tweet creati prima dell'ora inserita.
	 * @param from_day Accetta solo valori del tipo dd mm yy,inserire per visualizzare i tweet creati dopo la data inserita.
	 * @param to_day Accetta solo valori del tipo dd mm yy,inserire per visualizzare i tweet creati prima della data inserita.
	 * @return Restituisce una tabella contenente i tweet filtrati e le relative statistiche.
	 * @throws ParseException Eccezione relativa ad eventuali problemi con valori di tipo Data.
	 * @throws EmptyCollectionListException Eccezione personalizzata per gestire l'output di eventuali liste vuote.
	 * @throws InvalidHourException Eccezione lanciata in caso di formati invalidi dei parametri *_hour in input.
	 * @throws InvalidDateException Eccezione lanciata in caso di formati invalidi dei parametri *_day in input.
	 *
	 */
	Map<String, Object> analyzeTweets (String from_day,String to_day, String from_hour, String to_hour) 
				throws ParseException, EmptyCollectionListException, InvalidHourException, InvalidDateException;
}

