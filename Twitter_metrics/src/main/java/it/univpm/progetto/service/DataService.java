package it.univpm.progetto.service;

import java.io.IOException;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.univpm.progetto.exceptions.EmptyCollectionListException;
import it.univpm.progetto.exceptions.InvalidDateException;
import it.univpm.progetto.exceptions.InvalidFilterException;
import it.univpm.progetto.exceptions.InputStreamException;
import it.univpm.progetto.exceptions.InvalidHourException;
import it.univpm.progetto.filter.DataFilter;
import it.univpm.progetto.filter.Filter;
import it.univpm.progetto.model.Account;
import it.univpm.progetto.model.Metadata;
import it.univpm.progetto.model.Timeline;
import it.univpm.progetto.model.Tweet;


/**
 * Classe che gestisce la struttura dati.
 * <p>
 * L'annotazione <b>@Service</b> è necessaria affinchè Springboot 
 * utilizzi questa classe per l'iniezione delle dipendenze richieste
 * nel {@link it.univpm.progetto.controller.Controller} con l'annotazione <b>@Autowired</b>.</p> 
 * 
 * <p>Gli URL delle API di Twitter non sono diretti ma passano attraverso un proxy. </p>
 * 
 * @author Ivan Pacenti
 * 
 */
@Service
public class DataService implements Filter{
	
	/**
	 * Indirizzo dell'API di twitter che restituisce una lista di account.
	 */
	private static final String ACCOUNTS_API_URL="https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/users/search.json?q=";
	/**
	 * Indirizzo dell'API di Twitter che restituisce una lista di tweets.
	 */
	private static final String TWEET_API_URL="https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/user/1.1/statuses/user_timeline.json?id=<id>&count=<count>&include_rts=<rtws>&exclude_replies=<rpls>";
	/**
	 * Indirizzo dell'API di Twitter che restituisce una lista di collezioni.
	 */
	private static final String COLLECTIONS_API_URL="https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/user/1.1/collections/list.json?user_id=";
	/**
	 * Indirizzo dell'API di Twitter che restituisce una lista di tweet appartenenti ad una collezione.
	 */
	private static final String TIMELINE_API_URL="https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/user/1.1/collections/entries.json?id=";
	/**
	 * Indirizzo dell'API di Twitter che restituisce i dati di un solo utente.
	 */
	private static final String USER_API_URL="https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/user/1.1/users/show.json?user_id=<id>&include_entities=false&tweet_mode=false";
	
	/**
	 * Lista di account.
	 */
	private List<Account> accounts=new ArrayList<>();
	/**
	 * Lista di tweet.
	 */
	private List<Tweet> tweets=new ArrayList<>();
	/**
	 * Mappa contenente titolo e proprietà di una collezione.
	 */
	private Map<String,Timeline> collections;
	/**
	 * Variabile temporanea.
	 */
	private Timeline tmp;
	/**
	 * Variabile temporanea di tipo tweet.
	 */
	private Tweet tweettmp=new Tweet();
	
	/**
	 * Oggetto della classe ObjectMapper utilizzato per gestire i JSON in entrata e uscita.<p>
	 * 
	 * Si sfrutterà la possibilità dell'oggetto di poter ricevere in input un flusso di dati <b>InputStream</b>,
	 * in modo da rendere molto più veloce il download e il conseguente parsing dei dati.
	 */
	private ObjectMapper mapper=new ObjectMapper();
	/**
	 * Oggetto di tipo DataFilter, utilizzato per 
	 * filtrare i dati.
	 */
	private DataFilter filter;	
	/**
	 * Oggetto di tipo Account,
	 * utilizzato nelle collezioni.<p>
	 * Visto che ogni tweet ha un oggetto utente diverso
	 * ne viene utilizzato uno per ogni tweet di una collezione.
	 */
	private Account user=new Account();
	
	/**
	 * Oggetto della classe  {@link it.univpm.progetto.service.APIImpl} utilizzato per scaricare i dati dalle API di Twitter.
	 */
	private APIImpl call=new APIImpl();
	
	
	
	/**
	 * Costruttore vuoto necessario per la libreria Jackson.
	 */
	public DataService() {}

	/**
	 * Metodo usato per ricevere una lista di account, ordinati per rilevanza,
	 * rispetto la stringa <b>query</b> inserita.
	 * 
	 * @param query Parola chiave utilizzata per cercare account correlati.
	 * @return Una lista di oggetti di tipo Account.
	 * @throws IOException Eccezione di tipo input/output.
	 * @throws InputStreamException Eccezione personalizzaata, lanciata in caso di errori nello stream in input.
	 * @throws EmptyCollectionListException Eccezione personalizzata lanciata in caso 
	 * 		   debba essere restituita in uscita una collezione vuota.
	 */
	public List<Account> getAccounts(String query) throws IOException, InputStreamException, EmptyCollectionListException
	{

		String url=ACCOUNTS_API_URL+query.replaceAll(" ", "%20");
		try {
			accounts = Arrays.asList(mapper.readValue(call.getData(url), Account[].class));
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		if(accounts.isEmpty()) throw new EmptyCollectionListException("No users found");
		return accounts;
	}
	
	/**
	 * @return lista di tweet
	 */
	public List<Tweet> getTweets() {
		return tweets;
	}
	
	/**
	 * Restituisce una lista di tweet pubblici, relativi al parametro <b>id</b> inserito.
	 * 
	 * @param id Parametro obbligatorio, è l'identificativo dell'account di cui vogliamo i tweet.
	 * @param count Intero che indica il numero di tweet che si vogliono scaricare, il valore massimo è 200.
	 * @param show_retweets Valore booleano, va inserito con true se si vogliono scaricare anche i retweets effettuati dall'utente.
	 * @param show_replies Valore booleano, inserire true in caso si vogliano scaricare anche le risposte dell'utente.
	 * @return Una lista di oggetti di tipo Tweet.
	 * @throws IOException Eccezione di tipo input/output.
	 * @throws EmptyCollectionListException Eccezione personalizzata lanciata in caso 
	 * 		   debba essere restituita in uscita una collezione vuota.
	 * @throws InputStreamException Eccezione personalizzaata, lanciata in caso di errori nello stream in input.
	 */
	public List<Tweet> getTweets(String id,int count,Boolean show_retweets,Boolean show_replies) 
			throws IOException, EmptyCollectionListException, InputStreamException
	{
		String url=TWEET_API_URL.replaceAll("<id>", id).replace("<count>", Integer.toString(count))
				.replaceAll("<rtws>", show_retweets.toString()).replaceAll("<rpls>",show_replies.toString());
		try {
			tweets = Arrays.asList(mapper.readValue(call.getData(url), Tweet[].class));
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		/*inizializza l'oggetto filter con la lista di tweet
		 */
		filter=new DataFilter(tweets);
		if(tweets.isEmpty()) throw new EmptyCollectionListException("The user has no tweets yet");
		return tweets;
	}
	
	/**
	 * Restituisce una tabella contenente eventuali collezioni di tweet create dall'utente.
	 * 
	 * @param id Identificativo dell'utente di cui vogliamo scaricare le collezioni.
	 * @return Una tabella di collezioni dell'utente, se la lista è vuota viene lanciata una eccezione.
	 * @throws IOException Eccezione di tipo input/output.
	 * @throws EmptyCollectionListException Eccezione personalizzata lanciata in caso.
	 *         debba essere restituita in uscita una collezione vuota.
	 * @throws InputStreamException Eccezione personalizzata, lanciata in caso di errori nello stream in input.
	 */
	public Map<String,Timeline> getCollections(String id) throws IOException, EmptyCollectionListException, InputStreamException
	{
		collections=new HashMap<>();
		String url=COLLECTIONS_API_URL+id;
		/**
		 * la variabile node di tipo JsonNode, prende i valori dello stream in ingresso grazie all'Object Mapper
		 * successivamente viene creato un altro nodo json solo dei valori contenuti nella proprietà "objects"
		 */
		JsonNode node=mapper.readTree(call.getData(url));
		JsonNode tls=node.path("objects");
		if(!tls.isEmpty()) {
			/**
			 * Viene creato un iteratore riferito ai campi dell'array JSON <b>Timeline</b>
			 * che è una lista di collezioni aventi come titolo il loro id.
			 * Non si può usare il solito metodo di parsing per ottenere i dati, in quanto
			 * object mapper non riconoscerebbere l'id univoco di ogni collezione.
			 */
			Iterator<Entry<String, JsonNode>> nodes = tls.get("timelines").fields();
			while (nodes.hasNext()) 
			{
				/**
				 * Qui i dati ricevuti sono diversi dalle liste di tweet ricevute sopra.
				 * In questo caso si ha ogni collezione presentata come oggetto JSON distinto
				 * con il suo <b>id</b> come titolo.
				 * Con l'iteratore si scorre tra le collezioni e viene creato un jsonnode contenente i dati della singola collezione.
				 * Si crea un oggetto di tipo {@link it.univpm.progetto.model.Timeline} temporaneo <b>tmp</b> grazie all'oggetto 
				 * <b>mapper</b> che legge i valori dell'iteratore,
				 * si setta l'<b>id</b> che è stato letto tramite l'iteratore e infine si aggiunge l'oggetto <b>tmp</b> 
				 * alla collezione <b>collections</b>, che sarà data in uscita.
				 */
				Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodes.next();
				JsonNode tl_id=tls.path("timelines");
				tmp=mapper.readValue(tl_id.get(entry.getKey()).toString(), Timeline.class);
				tmp.setId(entry.getKey());
				collections.put(tl_id.get(entry.getKey()).get("name").asText(),tmp);
			}
		}
		if(collections.isEmpty()) throw new EmptyCollectionListException("The user has no collections yet");
		return collections;
	}
	
	/**
	 * Fornisce una lista di tweet presa dalla collezione di cui viene inserito l'<b>id</b>.
	 * 
	 * @param id Rappresenta l'identificativo della collezione.
	 * @param count Stabilisce il numero di oggetti restituiti, per un massimo di 200 tweet.
	 * @return Una lista di tweet.
	 * @throws IOException Eccezione di tipo input/output.
	 * @throws EmptyCollectionListException Eccezione personalizzata lanciata in caso 
	 * 		   debba essere restituita in uscita una collezione vuota.
	 * @throws InputStreamException Eccezione personalizzaata, lanciata in caso di errori nello stream in input.
	 */
	public List<Tweet> getTimelines(String id,String count) throws IOException, EmptyCollectionListException, InputStreamException
	{
		tweets=new ArrayList<>();
		String url=TIMELINE_API_URL+id+"&count="+count;
		try {
			JsonNode node=mapper.readTree(call.getData(url));
			JsonNode objects_node=node.path("objects");
			Iterator<Entry<String, JsonNode>> nodes = objects_node.get("tweets").fields();
			while (nodes.hasNext()) 
			{
				Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodes.next();
				JsonNode tweets_node=objects_node.path("tweets");
				tweettmp=mapper.readValue(tweets_node.get(entry.getKey()).toString(), Tweet.class);
				tweettmp.setUser(getUser(tweettmp.getUser().getId()));
				tweets.add(tweettmp);

			}
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		/**
		 * Inizializza l'oggetto filter con la lista di tweet.
		 */
		filter=new DataFilter(tweets);
		if(tweets.isEmpty()) throw new EmptyCollectionListException("The user has not added anything to this collection yet");
		return tweets;
	}
	
	
	
	/**
	 * Metodo utilizzato per scaricare i dati dell'utente di ogni tweet di una collezione.
	 * 
	 * @param id Identificativo dell'utente.
	 * @return Un oggetto di tipo Account.
	 * @throws IOException Eccezione di tipo input/output.
	 * @throws InputStreamException Eccezione personalizzaata, lanciata in caso di errori nello stream in input.
	 */
	public Account getUser(String id) throws IOException, InputStreamException
	{
		String url=USER_API_URL.replaceAll("<id>", id);
		try {
			user=mapper.readValue(call.getData(url), Account.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return user;
	}
	
	/**
	 * Restituisce i metadati utilizzati nell'applicazione.
	 * 
	 * @param type Stringa contenete la categoria di metadati desiderati.
	 * @return Una tabella di metadati.
	 * @throws EmptyCollectionListException In caso di input non valido, lancia un'eccezione personalizzata.
	 */
	public List<Object> getMetadata(String type) throws EmptyCollectionListException
	{
		List<Object> collections_data=new ArrayList<>();
		collections_data.add(new Metadata("name","nome della collezione","String"));
		collections_data.add(new Metadata("description","descrizione della collezione","String"));
		collections_data.add(new Metadata("id","identificativo della collezione","String"));

		List<Metadata> mentions_data=new ArrayList<>();
		mentions_data.add(new Metadata("name","nome dell'account a cui è riferita la menzione","String"));
		mentions_data.add(new Metadata("username","nome che identifica l'utente, preceduto da @","String"));
		mentions_data.add(new Metadata("id","identificativo dell'account menzionato","String"));
		Map<String,Object> mentions_map=new LinkedHashMap<>();
		mentions_map.put("mentions", mentions_data);

		List<Metadata> hashtags_data=new ArrayList<>();
		hashtags_data.add(new Metadata("text","testo dell'hashtag, preceduto da #","String"));
		Map<String,Object> hashtags_map=new LinkedHashMap<>();
		hashtags_map.put("hashtag", hashtags_data);

		List<Object> accounts_data=new ArrayList<>();
		accounts_data.add(new Metadata("id","identificativo dell'account","String"));
		accounts_data.add(new Metadata("name","nome dell'account","String"));
		accounts_data.add(new Metadata("username","nome che identifica l'utente, preceduto da @","String"));
		accounts_data.add(new Metadata("followers","numero di utenti che seguono l'account","Integer"));
		accounts_data.add(new Metadata("following","numero di utenti che l'account segue","Integer"));
		accounts_data.add(new Metadata("listed","numero di liste in cui compare l'account","Integer"));
		accounts_data.add(new Metadata("statuses","totale dei tweet dell'account","Integer"));

		List<Object> entities_data=new ArrayList<>();
		entities_data.add(hashtags_map);
		entities_data.add(mentions_map);
		Map<String,Object> entities_map=new LinkedHashMap<>();
		entities_map.put("entities", entities_data);

		List<Metadata> stats_data=new ArrayList<>();
		stats_data.add(new Metadata("username","nome che identifica l'utente, preceduto da @","String"));
		stats_data.add(new Metadata("followers","numero di utenti che seguono l'account","Integer"));
		stats_data.add(new Metadata("following","numero di utenti che l'account segue","Integer"));
		stats_data.add(new Metadata("listed","numero di liste in cui compare l'account","Integer"));
		stats_data.add(new Metadata("likes","totale dei likes del tweet","Integer"));
		stats_data.add(new Metadata("retweets","numero di volte che il tweet è stato retwittato","Integer"));
		stats_data.add(new Metadata("hashtags","hashtag totali","Integer"));
		stats_data.add(new Metadata("mentions","menzioni totali","Integer"));
		stats_data.add(new Metadata("engagement","engagement del tweet","Double"));
		Map<String,Object> stats_map=new LinkedHashMap<>();
		stats_map.put("stats", stats_data);


		List<Object> tweets_data=new ArrayList<>();
		tweets_data.add(new Metadata("created_at","data di creazione del tweet","Date"));
		tweets_data.add(new Metadata("id","identificativo del tweet","String"));
		tweets_data.add(new Metadata("text","testo del tweet","String"));
		tweets_data.add(entities_map);
		tweets_data.add(stats_map);
		tweets_data.add(new Metadata("original_tweet","presente in caso di retweet, indica il tweet originale","Tweet"));
		tweets_data.add(new Metadata("quoted_tweet","presente in caso di quote, indica il tweet quotato","Tweet"));
		Map<String,Object> tweets_map=new LinkedHashMap<>();
		tweets_map.put("tweets", tweets_data);

		List<Metadata> filter_data=new ArrayList<>();
		filter_data.add(new Metadata("Tweets analyzed","numero di tweet analizzati","Integer"));
		filter_data.add(new Metadata("Average engagement","media di engagement","Double"));
		filter_data.add(new Metadata("Variance of engagement","varianza di engagement","Double"));
		filter_data.add(new Metadata("Higher engagement","valore dell'engagement più alto","Double"));
		filter_data.add(new Metadata("Lower engagement","valore dell'egagement più basso","Double"));
		Map<String,Object> filter_map=new LinkedHashMap<>();
		filter_map.put("analytics", filter_data);

		List<Object> analytics_data=new ArrayList<>();
		analytics_data.add(filter_map);
		analytics_data.add(tweets_map);
		switch(type)
		{
		case"accounts":{return accounts_data;}
		case"collections":{ return collections_data;}
		case"tweets":{return tweets_data;}
		case"analytics":{ return analytics_data;}
		default:{throw new EmptyCollectionListException("There is no param such as "+type+". Please use /accounts /collections /tweets /analytics");}
		}
	}

	/**
	 * Setter dei tweets.
	 * 
	 * @param tweets Tweets da settare.
	 */
	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}
	
	/**
	 * Metodo per il filtro dei tweet.
	 * 
	 * @param field Campo del tweet che vogliamo utilizzare come filtro.
	 * @param op Operatore per filtrare, accettati solo > < ==.
	 * @param val Valore che si vuole usare per filtrare i tweet.
	 * @return Lista di oggetti di tipo Tweet filtrati.
	 * @throws InvalidFilterException Eccezione personalizzata per gestire eventuali campi field invalidi.
	 * @throws EmptyCollectionListException Lanciata se non sono stati scaricati tweets, il controllo avviene verificando che l'oggetto filter non sia inizializzato.
	 * @throws EmptyCollectionListException lanciata anche in caso vengano scelti come campi getters non numerici, che possono portare ad un filtraggio sbagliato.
	 */
	@Override
	public List<Tweet> filterField(String field,String op, String val) throws InvalidFilterException, EmptyCollectionListException
	{
		/*
		 * se @param filter non è inizializzato (quindi nullo) vuol dire che non sono stati scaricati tweets, quindi lancia l'eccezione
		 */
		if(filter==null)  throw new EmptyCollectionListException("Please download some tweet before filtering");
		/*
		 * se @param op non corisponde a < > == lancia un'eccezione
		 */
		if(!op.matches("<|>|==")) throw new InvalidFilterException("Please insert a valid operator : < > ==");
		/*
		 * il filtro funziona tramite la libreria {@link java.lang.reflect} che cerca se esiste un getter del campo immesso,
		 * visto che esistono getter che ritornano valori non filtrabili in questo modo (tipo getTweets()) lancia un'eccezione
		 * in caso venga immesso un field con valore non filtrabile
		 */
		if(field.matches("stats|tweets|entities|created_at|user|original_tweet|quoted_tweet")) 
			throw new InvalidFilterException("The field "+field+" is not avaiable for filtering");
		return filter.filterField(field, op, val);
	}
	
	/**
	 * Restituisce dei tweet filtrati a seconda dei parametri inseriti, insieme a delle statistiche.
	 * 
	 * @param from_hour Accetta solo valori del tipo HH, inserire per visualizzare i tweet creati dopo l'ora inserita.
	 * @param to_hour Accetta solo valori del tipo HH, inserire per visualizzare i tweet creati prima dell'ora inserita.
	 * @param from_day Accetta solo valori del tipo dd mm yy,inserire per visualizzare i tweet creati dopo la data inserita.
	 * @param to_day Accetta solo valori del tipo dd mm yy,inserire per visualizzare i tweet creati prima della data inserita.
	 * @return Una lista di oggetti filtrati di tipo Tweet.
	 * @throws ParseException Eccezione relativa ad eventuali problemi con valori di tipo Data.
	 * @throws EmptyCollectionListException Eccezione personalizzata per gestire l'output di eventuali liste vuote. 
	 * @throws InvalidHourException Eccezione lanciata in caso di formati invalidi dei parametri *_hour in input.
	 * @throws InvalidDateException Eccezione lanciata in caso di formati invalidi dei parametri *_day in input.
	 */
	public Map<String, Object> analyzeTweets(String from_hour,String to_hour, String from_day, String to_day) 
			throws ParseException, EmptyCollectionListException, InvalidHourException, InvalidDateException
	{
		/**
		 * Lancia un'eccezione in caso di oggetto filter non inizializzato.
		 */
		if(filter==null)  throw new EmptyCollectionListException("Please download some tweet before filtering");
		return filter.analyzeTweets(from_day,to_day,from_hour,to_hour);
	}	
}
