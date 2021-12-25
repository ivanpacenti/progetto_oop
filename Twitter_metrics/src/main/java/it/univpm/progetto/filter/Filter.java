/**
 * 
 */
package it.univpm.progetto.filter;

import java.util.Collection;

import it.univpm.progetto.exceptions.EmptyCollectionListException;
import it.univpm.progetto.exceptions.InvalidFilterException;

/**
 * @author ivan
 *
 */

	public interface Filter<E,T> {
		
		abstract Collection<E> filterField(String fieldName, String operator, String value) throws InvalidFilterException;
		
	}

