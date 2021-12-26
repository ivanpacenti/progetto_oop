package it.univpm.progetto.filter;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.progetto.exceptions.InvalidDateException;
import it.univpm.progetto.exceptions.InvalidHourException;
import it.univpm.progetto.filter.FilterUtils;
import it.univpm.progetto.model.Tweet;


/**classe che testa il lancio dell'eccezione in caso di ora scritta in modo sbagliato
 * @author Ivan Pacenti
 *
 */
class HourExceptionTest {
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
		/*verifica che venga lanciata l'eccezione in caso le ore siano minori di 0 o maggiori di 24
		 */
		InvalidHourException thrown = assertThrows(
		           InvalidHourException.class,
		           () -> prova.selectHour(tweets, "31", "18"),
		           "asd"
		    );

		    assertTrue(thrown.getMessage().contains("Please insert a valid hour of the format: hh"));
		
		
	}

}
