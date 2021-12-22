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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.univpm.progetto.exceptions.EmptyCollectionListException;
import it.univpm.progetto.filter.DataFilter;
import it.univpm.progetto.model.Account;
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
	private static Map<String,Timeline> collections=new HashMap<>();

	
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
	
	public static List<Tweet> getTweets(String id,String count,Boolean rtws,Boolean rpls) throws IOException
	{
		String url=TWEET_API_URL.replaceAll("<id>", id).replaceAll("<count>", count)
				.replaceAll("<rtws>", rtws.toString()).replaceAll("<rpls>",rpls.toString());
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
	
	public static List<Tweet> searchbyDate(String from,String to) throws ParseException, EmptyCollectionListException
	{
		
		return filter.searchbyDate(from,to);
	}
	
	public static List<Tweet> searchbyHour(String from,String to) throws ParseException, EmptyCollectionListException
	{
		
		return filter.searchbyHour(from,to);
	}
	
	public static Map<String, Object> analyze() throws ParseException, EmptyCollectionListException
	{
		
		return filter.analyze();
	}
}
