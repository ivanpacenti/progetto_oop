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
 * Interfaccia contenente metodi per filtrare
 * 
 * @author Ivan Pacenti
 *
 */
public interface Filter{
	
	/**
	 * Metodo che passa i valori alla classe FilterUtils, controllando che il valore insierito come parametro sia numerico
	 * 
	 * @param fieldName nome del campo da usare come filtro
	 * @param operator operatore per filtrare (< > ==)
	 * @param str valore da usare per filtrare
	 * @return lista di oggetti di tipo Tweet filtrati dalla classe FilterUtils
	 * @throws InvalidFilterException eccezione personalizzata per gestire eventuali campi field invalidi
	 *
	 */
	List<Tweet> filterField(String fieldName, String operator, String str) throws InvalidFilterException;
	/**
	 * Metodo che restituisce una tabella con una lista di tweet analizzati e le relative statistiche
	 * 
	 * @param from_hour accetta solo valori del tipo HH, inserire per visualizzare i tweet creati dopo l'ora inserita
	 * @param to_hour accetta solo valori del tipo HH, inserire per visualizzare i tweet creati prima dell'ora inserita
	 * @param from_day acccetta solo valori del tipo dd mm yy,inserire per visualizzare i tweet creati dopo la data inserita
	 * @param to_day acccetta solo valori del tipo dd mm yy,inserire per visualizzare i tweet creati prima della data inserita
	 * @param tmp lista di tweet temporanea, risultato del filtraggio secondo i parametri inseriti
	 * @param map tabella contenente le statistiche dei tweet filtrati
	 * @param total tabella riassuntiva contenente la lista dei tweet e la tabella delle statistiche
	 * @return restituisce una tabella contenente i tweet filtrati e le relative statistiche
	 * @throws ParseException Eccezione relativa ad eventuali problemi con valori di tipo Data
	 * @throws EmptyCollectionListException eccezione personalizzata per gestire l'output di eventuali liste vuote 
	 * @throws InvalidHourException eccezione lanciata in caso di formati invalidi dei parametri *_hour in input
	 * @throws InvalidDateException eccezione lanciata in caso di formati invalidi dei parametri *_day in input
	 *
	 */
	Map<String, Object> analyzeTweets (String from_day,String to_day, String from_hour, String to_hour) 
				throws ParseException, EmptyCollectionListException, InvalidHourException, InvalidDateException;
}

