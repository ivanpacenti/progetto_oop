package it.univpm.progetto.service;
import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.progetto.exceptions.InputStreamException;

/**
 * 
 */

/**
 * Test per verificare l'effettivo lancio dell'eccezione
 * {@link it.univpm.progetto.exceptions.InputStreamException.java}
 * in caso l'utente scelto abbia un profilo privato
 * 
 * @author Ivan Pacenti
 *
 */
class PrivateUserTest {
	DataService service=new DataService();
	InputStreamException thrown;
	
	/**
	 * Viene utilizzato un id utente privato per 
	 * verificare il funzionamento dell'eccezione apposita
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		 thrown = assertThrows(
				InputStreamException.class,
		           () -> service.getTweets("806926892129456129",200,false,false));		
	}

	/**
	 * 
	 * Test method for {@link it.univpm.progetto.service.APIImpl#getData(java.lang.String)}.
	 */
	@Test
	void testGetData() {
		assertTrue(thrown.getMessage().contains("The user has a private profile or is non-existent"));	
	}

}
