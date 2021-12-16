/**
 * 
 */
package it.univpm.progetto.filter;

import java.util.Collection;

/**
 * @author ivan
 *
 */

	public interface Filter<E,T> {
		
		abstract Collection<E> filterField(String fieldName, String operator, String value);
		
	}

