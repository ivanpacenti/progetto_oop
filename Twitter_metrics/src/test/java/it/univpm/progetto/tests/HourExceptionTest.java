package it.univpm.progetto.tests;
import static org.junit.jupiter.api.Assertions.*;



import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.progetto.exceptions.InvalidHourException;
import it.univpm.progetto.filter.FilterUtils;
import it.univpm.progetto.model.Tweet;


/**
 * Classe che testa il lancio dell'eccezione in caso di ora scritta in modo sbagliato.<p>
 * Metodo di test per {@link it.univpm.progetto.filter.FilterUtils#selectHour(java.util.List, java.lang.String, java.lang.String)}.
 * 
 * @author Ivan Pacenti
 *
 */
class HourExceptionTest {
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
	InvalidHourException thrown;
	
	/**
	 * Viene utilizzato il metodo {@link it.univpm.progetto.filter.FilterUtils#selectHour} 
	 * con il valore 31, che non dovrebbe essere ammesso.
	 * 
	 */
	@BeforeEach
	void setUp() throws Exception {
		thrown = assertThrows(
		           InvalidHourException.class,
		           () -> prova.selectHour(tweets, "31", "18"));
	}

	/**
	 * Verifica che venga lanciata l'eccezione in caso le ore siano minori di 0 o maggiori di 24.
	 */
	@Test
	void testSelectHour() {
		    assertTrue(thrown.getMessage().contains("Please insert a valid hour of the format: hh"));	
	}

}
