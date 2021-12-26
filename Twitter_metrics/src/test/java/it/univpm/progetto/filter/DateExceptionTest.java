/**
 * 
 */
package it.univpm.progetto.filter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.progetto.exceptions.InvalidDateException;
import it.univpm.progetto.model.Tweet;


/**classe per testare il parsing delle date 
 * @author Ivan Pacenti
 *
 */
class DateExceptionTest {
	FilterUtils<Tweet> prova=new FilterUtils<>();
	List<Tweet> tweets=new ArrayList<>();
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link it.univpm.progetto.filter.FilterUtils#selectHour(java.util.List, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testSelectHour() {
		/*verifica che venga lanciata l'eccezione in caso le date da convertire siano scritte
		 * in modo scorretto
		 */
		InvalidDateException thrown = assertThrows(
		           InvalidDateException.class,
		           () -> prova.selectDate(tweets, "01 as 20", "01 02 21"),
		           "asd"
		    );

		    assertTrue(thrown.getMessage().contains("Please insert a valid date of the format: dd mm yy"));
	}

}
