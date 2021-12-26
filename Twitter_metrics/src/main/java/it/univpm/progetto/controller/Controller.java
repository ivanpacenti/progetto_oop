package it.univpm.progetto.controller;

import java.io.IOException;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
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

/**Classe che gestisce le chiamate all'applicazione
 * @author Ivan Pacenti
 *
 */
@RestController
public class Controller {
	
	/**Restituisce un elenco di accounts relativi alla query inserita
	 * @param query parola chiave relativa all'account che cerchiamo
	 * @return una lista di oggetti di tipo Account
	 * @throws IOException in caso di problemi nella lettura dei dati dalla API di twitter
	 * @throws InputStreamException eccezione personalizzata per gestire eventuali problemi di lettura dei dati
	 * @throws EmptyCollectionListException eccezione personalizzata per gestire l'output di eventuali liste vuote 
	 */
	
	@GetMapping("/search/accounts")
	public ResponseEntity<List<Account>> 
	searchAccounts(@RequestParam(value="query",required=true) String query) 
			throws IOException, InputStreamException, EmptyCollectionListException 
	{
		return new ResponseEntity<List<Account>>(DataService.getAccounts(query),HttpStatus.OK);
	}
	
	/**Restituisce una lista di collezioni di tweet, relativa all'id utente inserito
	 * @param id identificativo dell'account utente di cui vogliamo cercare eventuali collezioni 
	 * @return una mappa (tabella) che contiene il nome della collezione, seguito da altri valori 
	 * @throws IOException in caso di problemi nella lettura dei dati dalla API di twitter
	 * @throws EmptyCollectionListException eccezione personalizzata per gestire l'output di eventuali liste vuote 
	 * @throws InputStreamException eccezione personalizzata per gestire eventuali problemi di lettura dei dati
	 */
	@GetMapping("/search/collections")
	public ResponseEntity<Map<String,Timeline>> searchCollections
	(@RequestParam(value="id", required=true) String id) 
			throws IOException, EmptyCollectionListException, InputStreamException
	{
		return new ResponseEntity<Map<String,Timeline>>(DataService.getCollections(id),HttpStatus.OK);
	}
	
	/**Restituisce una lista di tweets, in base all'id utente inserito
	 * E' possibile specificare ulteriori parametri
	 * @param id identificativo dell'account utente di cui vogliamo cercare eventuali tweet 
	 * @param count numero di tweet che vogliamo scaricare, il massimo è 200
	 * @param show_replies valore booleano, inserire true se si vogliono visualizzare anche le risposte effettuate dall'utente
	 * @param show_retweets valore booleano, inserire true se si vogliono visualizzare anche i retweet fatti dall'utente
	 * @return una lista di oggetti di tipo Tweet
	 * @throws IOException in caso di problemi nella lettura dei dati dalla API di twitter
	 * @throws EmptyCollectionListException eccezione personalizzata per gestire l'output di eventuali liste vuote 
	 * @throws InputStreamException eccezione personalizzata per gestire eventuali problemi di lettura dei dati
	 */
	@GetMapping("/tweets")
	public ResponseEntity<List<Tweet>> getTweets
	(@RequestParam(value="id",required=true) String id,
			@RequestParam(value="count", defaultValue = "50") int count,
			@RequestParam(value="replies",defaultValue="false") Boolean show_replies,
			@RequestParam(value="retweets",defaultValue="false") Boolean show_retweets)  throws  IOException, EmptyCollectionListException, InputStreamException
			
	{
		return new ResponseEntity<List<Tweet>>(DataService.getTweets(id,count,show_retweets,!show_replies),HttpStatus.OK);
	}
	
	/**Restituisce i tweet relativi all'id collezione inserito
	 * @param timeline identificativo della collezione che vogliamo visualizzare	
	 * @param count numero di tweet che vogliamo scaricare, il massimo è 200
	 * @return una lista di oggetti di tipo Tweet
	 * @throws IOException in caso di problemi nella lettura dei dati dalla API di twitter
	 * @throws EmptyCollectionListException eccezione personalizzata per gestire l'output di eventuali liste vuote 
	 * @throws InputStreamException eccezione personalizzata per gestire eventuali problemi di lettura dei dati
	 */
	@GetMapping("/collections")
	public ResponseEntity<List<Tweet>> getCollections
	(@RequestParam (value="timeline",required=true) String timeline,
			@RequestParam(value="count",defaultValue="50")String count) 
			throws IOException, EmptyCollectionListException, InputStreamException 
	{
		return new ResponseEntity<List<Tweet>>(DataService.getTimelines(timeline,count),HttpStatus.OK);
	}
	
	/**Filtra l'ultima lista di tweet scaricata
	 * @param field campo del tweet che vogliamo utilizzare come filtro
	 * @param op operatore per filtrare, accettati solo > < ==
	 * @param val valore che si vuole usare per filtrare i tweet
	 * @return lista di oggetti di tipo Tweet filtrati
	 * @throws EmptyCollectionListException eccezione personalizzata per gestire l'output di eventuali liste vuote 
	 * @throws InvalidFilterException eccezione personalizzata per gestire eventuali campi field invalidi
	 */
	@GetMapping("/filter")
	public ResponseEntity<List<Tweet>> getFilter
	(@RequestParam (value="field",required=true) String field,
	 @RequestParam (value="op",required=true) String op,
	 @RequestParam(value="val",required=true )String val)
			 throws EmptyCollectionListException, InvalidFilterException 
			
	{	
		return new ResponseEntity<List<Tweet>>(DataService.filterField(field,op,val),HttpStatus.OK);
	}
	
	/**Restituisce dei tweet filtrati a seconda dei parametri inseriti, insieme a delle statistiche 
	 * @param from_hour accetta solo valori del tipo HH, inserire per visualizzare i tweet creati dopo l'ora inserita
	 * @param to_hour accetta solo valori del tipo HH, inserire per visualizzare i tweet creati prima dell'ora inserita
	 * @param from_day acccetta solo valori del tipo dd mm yy,inserire per visualizzare i tweet creati dopo la data inserita
	 * @param to_day acccetta solo valori del tipo dd mm yy,inserire per visualizzare i tweet creati prima della data inserita
	 * @return una lista di oggetti filtrati di tipo Tweet
	 * @throws ParseException Eccezione relativa ad eventuali problemi con valori di tipo Data
	 * @throws EmptyCollectionListException eccezione personalizzata per gestire l'output di eventuali liste vuote 
	 * @throws InvalidHourException eccezione lanciata in caso di formati invalidi dei parametri *_hour in input
	 * @throws InvalidDateException eccezione lanciata in caso di formati invalidi dei parametri *_day in input
	 */
	@GetMapping("/filter/date")
	public ResponseEntity<Map<String, Object>> getSearchbyDate
	(@RequestParam (value="from_hour",required=false) String from_hour,
	 @RequestParam (value="to_hour",required=false) String to_hour,
	 @RequestParam (value="from_day",required=false) String from_day,
	 @RequestParam (value="to_day",required=false) String to_day)
			 throws ParseException, EmptyCollectionListException, InvalidHourException, InvalidDateException 
			
	{	
		return new ResponseEntity<Map<String, Object>>(DataService.searchbyDate(from_hour,to_hour,from_day,to_day),HttpStatus.OK);
	}
	
	/**Restitiusce i metadati relativi alla path variable inserita
	 * @param type stringa relativa al tipo di metadati che si vogliono ottenere,
	 * accetta solo i valori tweets, collections, accounts e analytics
	 * @return tabelle di metadati relativi al parametro inserito
	 * @throws EmptyCollectionListException eccezione personalizzata per gestire l'output di eventuali liste vuote 
	 */
	@GetMapping("/metadata/{type}")
	public ResponseEntity<List<Object>> getMetadata
	(@PathVariable String type) throws EmptyCollectionListException
	{	
		return new ResponseEntity<List<Object>> (DataService.getMetadata(type),HttpStatus.OK);
	}
}

