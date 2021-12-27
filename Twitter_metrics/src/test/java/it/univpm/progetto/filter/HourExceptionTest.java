package it.univpm.progetto.filter;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.progetto.exceptions.InvalidHourException;
import it.univpm.progetto.model.Tweet;


/**
 * Classe che testa il lancio dell'eccezione in caso di ora scritta in modo sbagliato
 * 
 * @author Ivan Pacenti
 *
 */
class HourExceptionTest {
	FilterUtils prova=new FilterUtils();
	List<Tweet> tweets=new ArrayList<>();
	InvalidHourException thrown;
	
	/**
	 * Viene utilizzato il metodo  {@link it.univpm.progetto.filter.FilterUtils#selectHour} 
	 * con il valore 31, che non dovrebbe essere ammesso
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		thrown = assertThrows(
		           InvalidHourException.class,
		           () -> prova.selectHour(tweets, "31", "18"));
	}

	/**
	 * Verifica che venga lanciata l'eccezione in caso le ore siano minori di 0 o maggiori di 24
	 * Test method for {@link it.univpm.progetto.filter.FilterUtils#selectHour(java.util.List, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testSelectHour() {
		    assertTrue(thrown.getMessage().contains("Please insert a valid hour of the format: hh"));	
	}

}
