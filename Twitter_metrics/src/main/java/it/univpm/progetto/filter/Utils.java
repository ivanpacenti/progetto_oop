package it.univpm.progetto.filter;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import it.univpm.progetto.exceptions.EmptyCollectionListException;
import it.univpm.progetto.exceptions.InvalidDateException;
import it.univpm.progetto.exceptions.InvalidHourException;
import it.univpm.progetto.model.Tweet;

/**
 * Interfaccia con metodi di filtraggio e analisi
 * @author Ivan Pacenti
 *
 */
public interface Utils {

	/**
	 * Metodo che filtra i tweet per data 
	 * 
	 * @param tweets lista di tweet da filtrare
	 * @param from stringa contenente una data per filtrare
	 * @param to stringa contenete un'altra data per filtrare
	 * @return una lista di tweet creati nell'intervallo di tempo definito dalle due date, 
	 * in assenza di date da filtrare la lista resta immutata,
	 * in caso sia presente solo la data "from" verranno restituiti i tweet creati dopo,
	 * in caso sia presente solo la data "to" verranno restituiti solo i tweet creati prima,
	 * in caso siano presenti sia "from" che "to" verranno restituiti i tweet creati tra le due date
	 * @throws ParseException Eccezione relativa ad eventuali problemi con valori di tipo Data
	 * @throws EmptyCollectionListException eccezione personalizzata per gestire l'output di eventuali liste vuote 
	 * @throws InvalidDateException eccezione lanciata in caso di formati invalidi dei parametri "from" e "to" in input
	 */
	List<Tweet> selectDate(List<Tweet> tweets, String from, String to)
			throws ParseException, EmptyCollectionListException, InvalidDateException;

	/**
	 * Metodo che filtra i tweet in base all'ora
	 * 
	 * @param tweets lista di tweet da filtrare
	 * @param from stringa contenente un'ora per filtrare
	 * @param to stringa contenete un'altra ora per filtrare
	 * @return una lista di tweet creati nell'intervallo di tempo definito dalle due ore, 
	 * in assenza di ore da filtrare la lista resta immutata,
	 * in caso sia presente solo il valore "from", verranno restituiti i tweet creati dopo, fino alla mezzanotte dello stesso giorno,
	 * in caso sia presente solo l'ora "to", verranno restituiti solo i tweet creati prima, dalla mezzanotte dello stesso giorno fino all'ora in questione,
	 * in caso siano presenti sia "from" che "to" verranno restituiti i tweet creati tra le due ore
	 * @throws ParseException Eccezione relativa ad eventuali problemi con valori di tipo Data
	 * @throws EmptyCollectionListException eccezione personalizzata per gestire l'output di eventuali liste vuote 
	 * @throws InvalidHourException eccezione lanciata in caso di formati invalidi dei parametri "from" e "to" in input
	 */
	List<Tweet> selectHour(List<Tweet> tweets, String from, String to) 
			throws ParseException, EmptyCollectionListException, InvalidHourException;

	/**
	 * Metodo che calcola media, varianza, engagement più alto e più basso di una lista di tweet
	 * 
	 * @param tmp lista di tweet da analizzare
	 * @param tot totale utilizzato per calcolare la media
	 * @param average media
	 * @param variance varianza
	 * @param maxengagement engagement massimo
	 * @param minengagement engagement minimo
	 * @param map tabella di valori da restituire in output
	 * @return tabella con statistiche, contenente anche il numero di tweet analizzati
	 */
	Map<String, Object> analyze(List<Tweet> tmp);
	
}
