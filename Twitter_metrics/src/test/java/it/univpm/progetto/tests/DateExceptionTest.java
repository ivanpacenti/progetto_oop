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
 * Classe per testare il parsing delle date.<p>
 * Metodo di test per {@link it.univpm.progetto.filter.FilterUtils#selectHour(java.util.List, java.lang.String, java.lang.String)}.
 * 
 * @author Ivan Pacenti
 *
 */
class DateExceptionTest {
	/**
	 * Oggetto creato per effettuare il test.
	 */
	FilterUtils prova=new FilterUtils();
	/**
	 * Lista vuota di tweet, creata giusto per far iniziare l'algoritmo di filtraggio per data.
	 */
	List<Tweet> tweets=new ArrayList<>();
	/**
	 * Oggetto che contiene l'eccezione lanciata nel <b>setUp</b>.
	 */
	InvalidDateException thrown;
	
	/**
	 * Si immette una data in modo volutamente scorretto
	 * per verificare il lancio dell'eccezione nel metodo {@link it.univpm.progetto.filter.FilterUtils#selectDate}.
	 */
	@BeforeEach
	void setUp() throws Exception {
		thrown = assertThrows(
		           InvalidDateException.class,
		           () -> prova.selectDate(tweets, "01 as 20", "01 02 21"));		
	}

	/**
	 * Verifica che venga lanciata l'eccezione in caso le date da convertire siano scritte
	 * in modo scorretto.
	 */
	@Test
	void testSelectHour() {
		    assertTrue(thrown.getMessage().contains("Please insert a valid date of the format: dd mm yy"));
	}

}
