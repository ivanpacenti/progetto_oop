/**
 * 
 */
package it.univpm.progetto.filter;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import it.univpm.progetto.exceptions.EmptyCollectionListException;
import it.univpm.progetto.exceptions.InvalidDateException;
import it.univpm.progetto.exceptions.InvalidHourException;
import it.univpm.progetto.model.Tweet;

/**
 * @author ivan
 *
 */
public interface Utils {
	  List<Tweet> selectDate(List<Tweet> tweets, String from, String to) 
			  	throws ParseException, EmptyCollectionListException, InvalidDateException;
	  List<Tweet> selectHour(List<Tweet> tweets, String from, String to)  
				throws ParseException, EmptyCollectionListException, InvalidHourException;
	  Map<String,Object> analyze(List<Tweet> tmp);
}
