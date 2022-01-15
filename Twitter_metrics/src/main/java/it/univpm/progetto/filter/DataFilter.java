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
 * Classe che gestisce il filtraggio dei tweet.
 * 
 * @author Ivan Pacenti
 *
 */
public class DataFilter implements Filter {
	
	private  List<Tweet> tweets=new ArrayList<>();
	private  List<Tweet> tmp=new ArrayList<>();
	private  FilterUtils utils;
		

	/**
	 * Costruttore della classe.
	 * 
	 * @param tweets Lista di tweets presi in input.
	 */
	public DataFilter(List<Tweet> tweets) {
		this.tweets = tweets;
		this.utils=new FilterUtils();
		
	}

	/**
	 * @return oggetto utils
	 */
	public FilterUtils getUtils() {
		return utils;
	}

	/**
	 * @return i tweets
	 */
	public  List<Tweet> getTweets() {
		return tweets;
	}


	/**
	 * @param tweets setta i tweets
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
	 * Metodo per controllare se il valore è numerico.
	 * 
	 * @param str Valore in input.
	 * @return true Se il valore è numerico, al contrario ritorna false.
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
	 * Metodo che passa i valori alla classe FilterUtils, controllando che il valore insierito come parametro sia numerico.
	 * 
	 * @param fieldName Campo del tweet che vogliamo utilizzare come filtro.
	 * @param operator Operatore per filtrare, accettati solo > < ==.
	 * @param str Stringa contenente valore per filtrare i dati, sarà successivamente convertita eventualmente in formato numerico.
	 * @return Lista di oggetti di tipo Tweet filtrati.
	 * @throws InvalidFilterException Eccezione personalizzata per gestire eventuali campi field invalidi.
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
	 * Metodo che restituisce una tabella con una lista di tweet analizzati e le relative statistiche.
	 * 
	 * @param from_hour Accetta solo valori del tipo HH, inserire per visualizzare i tweet creati dopo l'ora inserita.
	 * @param to_hour Accetta solo valori del tipo HH, inserire per visualizzare i tweet creati prima dell'ora inserita.
	 * @param from_day Accetta solo valori del tipo dd mm yy, inserire per visualizzare i tweet creati dopo la data inserita.
	 * @param to_day Acccetta solo valori del tipo dd mm yy, inserire per visualizzare i tweet creati prima della data inserita.
	 * @return Una lista di oggetti filtrati di tipo Tweet.
	 * @throws ParseException Eccezione relativa ad eventuali problemi con valori di tipo Data.
	 * @throws EmptyCollectionListException Eccezione personalizzata per gestire l'output di eventuali liste vuote.
	 * @throws InvalidHourException Eccezione lanciata in caso di formati invalidi dei parametri *_hour in input.
	 * @throws InvalidDateException Eccezione lanciata in caso di formati invalidi dei parametri *_day in input.
	 *
	 */
	@Override
	public Map<String, Object> analyzeTweets (String from_day,String to_day, String from_hour, String to_hour) 
			throws ParseException, InvalidHourException, InvalidDateException, EmptyCollectionListException 
	{
		/**
		 * Qui ci sono dei filtri innestati: prima si esegue il filtraggio dei tweet a seconda della data,
		 * successivamente i tweet filtrati vengono ri-filtrati a seconda dell'ora immessa.
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
