/**
 * 
 */
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
import java.util.TimeZone;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.univpm.progetto.exceptions.EmptyCollectionListException;
import it.univpm.progetto.filter.DataFilter;
import it.univpm.progetto.model.Account;
import it.univpm.progetto.model.Metadata;
import it.univpm.progetto.model.Timeline;
import it.univpm.progetto.model.Tweet;

/**
 * @author ivan
 *
 */
@Component

public final class DataService  {
	private static final String ACCOUNTS_API_URL="https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/users/search.json?q=";
	private static final String TWEET_API_URL="https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/user/1.1/statuses/user_timeline.json?id=<id>&count=<count>&include_rts=<rtws>&exclude_replies=<rpls>";
	private static final String COLLECTIONS_API_URL="https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/user/1.1/collections/list.json?user_id=";
	private static final String TIMELINE_API_URL="https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/user/1.1/collections/entries.json?id=";
	private static final String USER_API_URL="https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/user/1.1/users/show.json?user_id=<id>&include_entities=false&tweet_mode=false";
	
	private static List<Account> accounts=new ArrayList<>();
	private static List<Tweet> tweets=new ArrayList<>();
	private static Map<String,Timeline> collections;

	
	private static Timeline tmp;
	private static Tweet tweettmp=new Tweet();
	private static ObjectMapper mapper=new ObjectMapper();
	private static DataFilter filter;
	private static Account user=new Account();
	
	private static APIImpl call=new APIImpl();
	
	public DataService() {}
	
	
	
	public static List<Account> getAccounts(String query) throws IOException
	{
		
		String url=ACCOUNTS_API_URL+query.replaceAll(" ", "%20");
		try {
			accounts = Arrays.asList(mapper.readValue(call.getData(url), Account[].class));
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return accounts;
	}
	
	public static List<Tweet> getTweets(String id,String count,Boolean show_retweets,Boolean show_replies) throws IOException
	{
		String url=TWEET_API_URL.replaceAll("<id>", id).replaceAll("<count>", count)
				.replaceAll("<rtws>", show_retweets.toString()).replaceAll("<rpls>",show_replies.toString());
		//call=new APIImpl(url);
		//mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		
		try {
			tweets = Arrays.asList(mapper.readValue(call.getData(url), Tweet[].class));
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		filter=new DataFilter(tweets);
		return tweets;
	}
	
	public static Map<String,Timeline> getCollections(String id) throws IOException
	{
		collections=new HashMap<>();
		String url=COLLECTIONS_API_URL+id;
		JsonNode node=mapper.readTree(call.getData(url));
		JsonNode tls=node.path("objects");
		if(!tls.isEmpty()) {
		Iterator<Entry<String, JsonNode>> nodes = tls.get("timelines").fields();
		while (nodes.hasNext()) 
		{
			Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodes.next();
			JsonNode tl_id=tls.path("timelines");
			
			tmp=mapper.readValue(tl_id.get(entry.getKey()).toString(), Timeline.class);
			tmp.setId(entry.getKey());
			collections.put(tl_id.get(entry.getKey()).get("name").asText(),tmp);
		}
		}
		return collections;
	}
	
	public static List<Tweet> getTimelines(String timeline,String count) throws IOException
	{
		tweets=new ArrayList<>();
		String url=TIMELINE_API_URL+timeline+"&count="+count;
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
		
		filter=new DataFilter(tweets);
		return tweets;
	}
	
	
	
	public static Account getUser(String id) throws IOException
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
	
	public static List<Object> getMetadata(String type)
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
			case "accounts":{return accounts_data;}
			case"collections":{ return collections_data;}
			case"tweets":{return tweets_data;}
			case"analytics":{ return analytics_data;}
		}
		return null;
	}
	
	public static List<Tweet> getTweets() {
		return tweets;
	}


	public void setTweets(List<Tweet> tweets) {
		DataService.tweets = tweets;
	}
	
	public static List<Tweet> filterField(String field,String op, String val)
	
	{
		
		return filter.filterField(field, op, val);
		
	}
	
	public static Map<String, Object> searchbyDate(String from_hour,String to_hour, String from_day, String to_day) throws ParseException, EmptyCollectionListException
	{
		
		return filter.searchbyDate(from_day,to_day,from_hour,to_hour);
	}
	
	/*public static Map<String, Object> searchbyHour(String from,String to) throws ParseException, EmptyCollectionListException
	{
		
		return filter.searchbyHour(from,to);
	}*/
	
	/*public static Map<String, Object> analyze() throws ParseException, EmptyCollectionListException
	{
		
		return filter.analyze();
	}*/
}
