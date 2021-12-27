package it.univpm.progetto.filter;

import java.text.ParseException;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import it.univpm.progetto.exceptions.EmptyCollectionListException;
import it.univpm.progetto.exceptions.InvalidDateException;
import it.univpm.progetto.exceptions.InvalidFilterException;
import it.univpm.progetto.exceptions.InvalidHourException;
import it.univpm.progetto.model.Tweet;


/**
 * Classe che gestisce il filtraggio dei tweet
 * 
 * @author Ivan Pacenti
 *
 */
public class DataFilter implements Filter {
	
	private  List<Tweet> tweets=new ArrayList<>();
	private  List<Tweet> tmp=new ArrayList<>();
	private  FilterUtils utils;
		
	
	
	
	/**
	 * Costruttore della classe
	 * 
	 * @param tweets lista di tweets presi in input
	 * @param utils oggetto della classe FilterUtils per filtrare
	 */
	public DataFilter(List<Tweet> tweets) {
		this.tweets = tweets;
		this.utils=new FilterUtils();
		
	}

	/**
	 * @return the utils
	 */
	public FilterUtils getUtils() {
		return utils;
	}

	/**
	 * @param result the result to set
	 */
	public  void setResult(List<Tweet> result) {
	}
	

	/**
	 * @return the tweets
	 */
	public  List<Tweet> getTweets() {
		return tweets;
	}


	/**
	 * @param tweets the tweets to set
	 */
	public  void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}


	/**
	 * @param utils the utils to set
	 */
	public void setUtils(FilterUtils utils) {
		this.utils = utils;
	}
	
	/**
	 * Metodo per controllare se il valore è numerico
	 * 
	 * @param str valore in input
	 * @return true se il valore è numerico, al contrario ritorna false
	 */
	public static boolean isNumeric(String str) { 
		try {  
			Double.parseDouble(str);  
			return true;
		} catch(NumberFormatException e){  
			return false;  
		}  
	}

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
	@Override
	public  List<Tweet> filterField(String fieldName, String operator, String str) throws InvalidFilterException {
		Object value;
		if(isNumeric(str)&&!(fieldName.equals("id"))) 
		{
			value=Double.parseDouble(str);
		}
		else value=str;
		return (List<Tweet>) utils.select(getTweets(), fieldName, operator, value);
	}
	
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
	@Override
	public Map<String, Object> analyzeTweets (String from_day,String to_day, String from_hour, String to_hour) 
			throws ParseException, InvalidHourException, InvalidDateException, EmptyCollectionListException 
	{
		/**
		 * Qui ci sono dei filtri innestati: prima si esegue il filtraggio dei tweet a seconda della data,
		 *successivamente i tweet filtrati vengono ri-filtrati a seconda dell'ora immessa
		 */
		this.tmp=utils.selectHour
				(utils.selectDate(getTweets(), from_day, to_day), from_hour, to_hour);
		Map<String,Object> map=utils.analyze(this.tmp);
		Map<String,Object> total=new LinkedHashMap<>();
		total.put("analytics", map);
		total.put("tweets", tmp);
		return total;
	}
}
