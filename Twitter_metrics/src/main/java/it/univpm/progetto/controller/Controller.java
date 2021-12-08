/**
 * 
 */
package it.univpm.progetto.controller;

import java.util.ArrayList;
import java.util.Vector;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progetto.model.APICall;
import it.univpm.progetto.model.Accounts;

/**
 * @author ivan
 *
 */
@RestController
public class Controller {
	
	private APICall apicall=new APICall();
	
	
	
	@GetMapping("/search")
	public ArrayList<Accounts> search(@RequestParam(value="query") String query)
	{
		
		return apicall.searchapi(query);
		
	}
}
