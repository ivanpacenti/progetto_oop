package it.univpm.progetto.tests;
import static org.junit.jupiter.api.Assertions.*;




import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.progetto.exceptions.InputStreamException;
import it.univpm.progetto.service.DataService;

/**
 * 
 */

/**
 * Test per verificare l'effettivo lancio dell'eccezione
 * {@link it.univpm.progetto.exceptions.InputStreamException}
 * in caso l'utente scelto abbia un profilo privato.
 * 
 * @author Ivan Pacenti
 *
 */
class PrivateUserTest {
	/**
	 * Oggetto creato per testare la classe {@link it.univpm.progetto.service.DataService} 
	 */
	DataService service=new DataService();
	/**
	 * Oggetto utile per contenere l'eventuale eccezione lanciata.
	 */
	InputStreamException thrown;
	
	/**
	 * Viene utilizzato un id utente privato per 
	 * verificare il funzionamento dell'eccezione apposita.
	 */
	@BeforeEach
	void setUp()  {
		 thrown = assertThrows(
				InputStreamException.class,
		           () -> service.getTweets("806926892129456129",200,false,false));		
	}

	/**
	 * Verifica che venga lanciata l'eccezione in caso non si riesca a scaricare i dati dalle API di Twitter.
	 */
	@Test
	void testGetData() {
		assertTrue(thrown.getMessage().contains("The user has a private profile or is non-existent"));	
	}

}
