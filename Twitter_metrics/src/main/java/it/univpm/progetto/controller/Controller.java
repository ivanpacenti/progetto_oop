package it.univpm.progetto.controller;

import java.io.IOException;


import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import it.univpm.progetto.exceptions.EmptyCollectionListException;
import it.univpm.progetto.exceptions.InvalidDateException;
import it.univpm.progetto.exceptions.InvalidFilterException;
import it.univpm.progetto.exceptions.InputStreamException;
import it.univpm.progetto.exceptions.InvalidHourException;
import it.univpm.progetto.model.Account;
import it.univpm.progetto.model.Timeline;
import it.univpm.progetto.model.Tweet;
import it.univpm.progetto.service.DataService;

/**
 * Classe che gestisce le chiamate all'applicazione.
 * 
 * @author Ivan Pacenti
 */
@RestController
public class Controller {
	/**
	 * Oggetto della classe che gestisce i dati.<p>
	 * <b>@Autowired</b> è un'annotazione necessaria affinchè Springboot 
	 * utilizzi la classe {@link it.univpm.progetto.service.DataService},
	 * annotata con <b>@Service</b>.
	 */
	@Autowired 
	private DataService service;
	
	/**
	 * Restituisce un elenco di account relativi alla query inserita.
	 * 
	 * @param query Parola chiave relativa all'account che cerchiamo.
	 * @return Una lista di oggetti di tipo Account.
	 * @throws IOException In caso di problemi nella lettura dei dati dalla API di Twitter.
	 * @throws InputStreamException Eccezione personalizzata per gestire eventuali problemi di lettura dei dati.
	 * @throws EmptyCollectionListException Eccezione personalizzata per gestire l'output di eventuali liste vuote. 
	 */
	@GetMapping("/search/accounts")
	public ResponseEntity<List<Account>> 
	searchAccounts(@RequestParam(value="query",required=true) String query) 
			throws IOException, InputStreamException, EmptyCollectionListException 
	{
		if(query=="") throw new EmptyCollectionListException("Request parameter query cannot be empty");
		return new ResponseEntity<List<Account>>(service.getAccounts(query),HttpStatus.OK);
	}
	
	/**
	 * Restituisce una lista di collezioni di tweet, relativa all'id utente inserito.
	 * 
	 * @param id Identificativo dell'account utente di cui vogliamo cercare eventuali collezioni. 
	 * @return Una mappa (tabella) che contiene il nome della collezione, seguito da altri valori. 
	 * @throws IOException In caso di problemi nella lettura dei dati dalla API di Twitter.
	 * @throws EmptyCollectionListException Eccezione personalizzata per gestire l'output di eventuali liste vuote. 
	 * @throws InputStreamException Eccezione personalizzata per gestire eventuali problemi di lettura dei dati.
	 */
	@GetMapping("/search/collections")
	public ResponseEntity<Map<String,Timeline>> searchCollections
	(@RequestParam(value="id", required=true) String id) 
			throws IOException, EmptyCollectionListException, InputStreamException
	{
		return new ResponseEntity<Map<String,Timeline>>(service.getCollections(id),HttpStatus.OK);
	}
	
	/**
	 * Restituisce una lista di tweets, in base all'id utente inserito.
	 * 
	 * @param id Identificativo dell'account utente di cui vogliamo cercare eventuali tweet, per default impostato sull'id dell'UNIVPM.
	 * @param count Numero di tweet che vogliamo scaricare, il massimo è 200 e nel conteggio sono inclusi sia i retweets che i tweet normali.
	 * @param show_replies Valore booleano, inserire true se si vogliono visualizzare anche le risposte effettuate dall'utente.<p>
	 * 					   Twitter utilizza una logica contraria (exclude_replies), quindi si utilizza il <b>!</b> per correggere la chiamata all'API
	 * @param show_retweets Valore booleano, inserire true se si vogliono visualizzare anche i retweet fatti dall'utente.
	 * @return Una lista di oggetti di tipo tweet.
	 * @throws IOException In caso di problemi nella lettura dei dati dalla API di Twitter.
	 * @throws EmptyCollectionListException Eccezione personalizzata per gestire l'output di eventuali liste vuote. 
	 * @throws InputStreamException Eccezione personalizzata per gestire eventuali problemi di lettura dei dati.
	 */
	@GetMapping("/tweets")
	public ResponseEntity<List<Tweet>> getTweets
	(@RequestParam(value="id",defaultValue="1304170778") String id,
			@RequestParam(value="count", defaultValue = "50") int count,
			@RequestParam(value="replies",defaultValue="false") Boolean show_replies,
			@RequestParam(value="retweets",defaultValue="false") Boolean show_retweets)  
					throws  IOException, EmptyCollectionListException, InputStreamException
			
	{
		return new ResponseEntity<List<Tweet>>(service.getTweets(id,count,show_retweets,!show_replies),HttpStatus.OK);
	}
	
	/**
	 * Restituisce i tweet contenuti nella collezione di cui si inserisce l'<b>id</b>.
	 * 
	 * @param timeline Identificativo della collezione che vogliamo visualizzare.	
	 * @param count Numero di tweet che vogliamo scaricare, il massimo è 200.
	 * @return Una lista di oggetti di tipo Tweet.
	 * @throws IOException In caso di problemi nella lettura dei dati dalla API di twitter.
	 * @throws EmptyCollectionListException Eccezione personalizzata per gestire l'output di eventuali liste vuote .
	 * @throws InputStreamException Eccezione personalizzata per gestire eventuali problemi di lettura dei dati.
	 */
	@GetMapping("/collections")
	public ResponseEntity<List<Tweet>> getCollections
	(@RequestParam (value="timeline",required=true) String timeline,
			@RequestParam(value="count",defaultValue="50")String count) 
			throws IOException, EmptyCollectionListException, InputStreamException 
	{
		return new ResponseEntity<List<Tweet>>(service.getTimelines(timeline,count),HttpStatus.OK);
	}
	
	/**
	 * Filtra l'ultima lista di tweet scaricata.
	 * 
	 * @param field Campo del tweet che vogliamo utilizzare come filtro.
	 * @param op Operatore per filtrare, accettati solo > < ==.
	 * @param val Valore che si vuole usare per filtrare i tweet.
	 * @return Lista di oggetti di tipo Tweet filtrati.
	 * @throws EmptyCollectionListException Eccezione personalizzata per gestire l'output di eventuali liste vuote.
	 * @throws InvalidFilterException Eccezione personalizzata per gestire eventuali campi field invalidi.
	 */
	@GetMapping("/filter")
	public ResponseEntity<List<Tweet>> getFilter
	(@RequestParam (value="field",required=true) String field,
	 @RequestParam (value="op",required=true) String op,
	 @RequestParam(value="val",required=true )String val)
			 throws EmptyCollectionListException, InvalidFilterException 
			
	{	
		return new ResponseEntity<List<Tweet>>(service.filterField(field,op,val),HttpStatus.OK);
	}
	
	/**
	 * Restituisce dei tweet filtrati a seconda dei parametri inseriti, 
	 * insieme a delle statistiche.
	 * 
	 * @param from_hour Accetta solo valori del tipo HH, inserire per visualizzare i tweet creati dopo l'ora inserita.
	 * @param to_hour Accetta solo valori del tipo HH, inserire per visualizzare i tweet creati prima dell'ora inserita.
	 * @param from_day Accetta solo valori del tipo dd mm yy, inserire per visualizzare i tweet creati dopo la data inserita.
	 * @param to_day Acccetta solo valori del tipo dd mm yy, inserire per visualizzare i tweet creati prima della data inserita.
	 * @return Una lista di oggetti filtrati di tipo Tweet.
	 * @throws ParseException Eccezione relativa ad eventuali problemi con valori di tipo Data.
	 * @throws EmptyCollectionListException Eccezione personalizzata per gestire l'output di eventuali liste vuote.
	 * @throws InvalidHourException Eccezione lanciata in caso di formati invalidi dei parametri *_hour in input.
	 * @throws InvalidDateException Eccezione lanciata in caso di formati invalidi dei parametri *_day in input.
	 */
	@GetMapping("/filter/date")
	public ResponseEntity<Map<String, Object>> getSearchbyDate
	(@RequestParam (value="from_hour",required=false) String from_hour,
	 @RequestParam (value="to_hour",required=false) String to_hour,
	 @RequestParam (value="from_day",required=false) String from_day,
	 @RequestParam (value="to_day",required=false) String to_day)
			 throws ParseException, EmptyCollectionListException, InvalidHourException, InvalidDateException 
			
	{	
		return new ResponseEntity<Map<String, Object>>(service.analyzeTweets(from_hour,to_hour,from_day,to_day),HttpStatus.OK);
	}
	
	/**
	 * Restitiusce i metadati relativi alla path variable inserita.
	 * 
	 * @param type Stringa relativa al tipo di metadati che si vogliono ottenere,
	 *             accetta solo i valori tweets, collections, accounts e analytics.
	 * @return Tabelle di metadati relativi al parametro inserito.
	 * @throws EmptyCollectionListException Eccezione personalizzata per gestire l'output di eventuali liste vuote.
	 */
	@GetMapping("/metadata/{type}")
	public ResponseEntity<List<Object>> getMetadata
	(@PathVariable String type) throws EmptyCollectionListException
	{	
		return new ResponseEntity<List<Object>> (service.getMetadata(type),HttpStatus.OK);
	}
}

