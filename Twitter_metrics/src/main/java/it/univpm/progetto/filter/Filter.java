/**
 * 
 */
package it.univpm.progetto.filter;

import java.text.ParseException;
import java.util.Collection;
import java.util.Map;

import it.univpm.progetto.exceptions.EmptyCollectionListException;
import it.univpm.progetto.exceptions.InvalidDateException;
import it.univpm.progetto.exceptions.InvalidFilterException;
import it.univpm.progetto.exceptions.InvalidHourException;

/**
 * @author ivan
 *
 */

	public interface Filter<E> {
		
		Collection<E> filterField(String fieldName, String operator, String value) throws InvalidFilterException;
		Map<String, Object> searchbyDate (String from_day,String to_day, String from_hour, String to_hour) throws ParseException, EmptyCollectionListException, InvalidHourException, InvalidDateException;
		
	}

