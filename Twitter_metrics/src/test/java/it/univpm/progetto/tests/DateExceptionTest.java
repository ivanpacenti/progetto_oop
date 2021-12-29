/**
 * 
 */
package it.univpm.progetto.tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.progetto.exceptions.InvalidDateException;
import it.univpm.progetto.filter.FilterUtils;
import it.univpm.progetto.model.Tweet;


/**
 * classe per testare il parsing delle date 
 * 
 * @author Ivan Pacenti
 *
 */
class DateExceptionTest {
	FilterUtils prova=new FilterUtils();
	List<Tweet> tweets=new ArrayList<>();
	InvalidDateException thrown;
	
	/**
	 * Si immette una data in modo volutamente scorretto
	 * per verificare il lancio dell'eccezione nel metodo {@link it.univpm.progetto.filter.FilterUtils#selectDate} 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		thrown = assertThrows(
		           InvalidDateException.class,
		           () -> prova.selectDate(tweets, "01 as 20", "01 02 21"));		
	}

	/**
	 * verifica che venga lanciata l'eccezione in caso le date da convertire siano scritte
	 * in modo scorretto
	 * Test method for {@link it.univpm.progetto.filter.FilterUtils#selectHour(java.util.List, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testSelectHour() {
		    assertTrue(thrown.getMessage().contains("Please insert a valid date of the format: dd mm yy"));
	}

}
