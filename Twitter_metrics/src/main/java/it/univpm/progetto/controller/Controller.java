/**
 * 
 */
package it.univpm.progetto.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progetto.apicall.APICall;
import it.univpm.progetto.apicall.GetData;
import it.univpm.progetto.model.Accounts;
import it.univpm.progetto.model.Tweets;

/**
 * @author ivan
 *
 */
@RestController
public class Controller {
	
	GetData data=new GetData();
	
	
	
	@GetMapping("/search")
	public List<Accounts> search(@RequestParam(value="query") String query)
	{
		
		return data.getAccounts(query);
		
	}
	
	@GetMapping("/tweets")
	public ArrayList<Tweets> list(@RequestParam(value="id", defaultValue = "1304170778")String id)
	{
		return data.getTweets(id);
	}
}
