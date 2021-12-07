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
	
	APICall apicall=new APICall();
	@GetMapping("/search")
	public void search(@RequestParam(value="query") String query)
	{
		
		apicall.searchapi(query);
		System.out.println("ciao");
		
	}
}
