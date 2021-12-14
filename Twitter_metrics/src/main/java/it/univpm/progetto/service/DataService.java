/**
 * 
 */
package it.univpm.progetto.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.univpm.progetto.model.Account;
import it.univpm.progetto.model.Timeline;
import it.univpm.progetto.model.Tweet;

/**
 * @author ivan
 *
 */
@Component
public class DataService {
	private static final String ACCOUNTS_API_URL="https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/users/search.json?q=";
	private static final String TWEET_API_URL="https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/user/1.1/statuses/user_timeline.json?id=";
	private static final String COLLECTIONS_API_URL="https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/user/1.1/collections/list.json?user_id=";
	private static final String TIMELINE_API_URL="https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/user/1.1/collections/entries.json?id=";
	
	private List<Account> accounts=new ArrayList<>();
	private List<Tweet> tweets=new ArrayList<>();
	private Map<String,Timeline> collections=new HashMap<>();
	
	private Timeline tmp;
	private ObjectMapper mapper=new ObjectMapper();
	private APIImpl call;
	
	public DataService() {}
	
	
	public List<Account> getAccounts(String query)
	{
		String url=ACCOUNTS_API_URL+query.replaceAll(" ", "%20");
		call=new APIImpl(url);
		
		try {
			accounts = Arrays.asList(mapper.readValue(call.getData(), Account[].class));
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return accounts;
	}
	
	public List<Tweet> getTweets(String id)
	{
		String url=TWEET_API_URL+id.replaceAll(" ", "%20");
		call=new APIImpl(url);
		
		try {
			tweets = Arrays.asList(mapper.readValue(call.getData(), Tweet[].class));
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return tweets;
	}
	
	public Map<String,Timeline> getCollections(String id) throws JsonMappingException, JsonProcessingException
	{
		String url=COLLECTIONS_API_URL+id;
		call=new APIImpl(url);
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		JsonNode node=mapper.readTree(call.getData());
		JsonNode tls=node.path("objects");
		if(!tls.isEmpty()) {
		Iterator<Entry<String, JsonNode>> nodes = tls.get("timelines").fields();
		while (nodes.hasNext()) 
		{
			Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodes.next();
			JsonNode tl_id=tls.path("timelines");
			
			tmp=mapper.readValue(tl_id.get(entry.getKey()).toString(), Timeline.class);
			this.tmp.setId(entry.getKey());
			collections.put(tl_id.get(entry.getKey()).get("name").asText(),tmp);
		}
		}
		return collections;
	}
	
	public List<Tweet> getTimelines(String timeline)
	{
		String url=TIMELINE_API_URL+timeline+"&count=200";
		call=new APIImpl(url);
		Tweet tmp=new Tweet();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		try {
			JsonNode node=mapper.readTree(call.getData());
			JsonNode objects_node=node.path("objects");
			Iterator<Entry<String, JsonNode>> nodes = objects_node.get("tweets").fields();
			while (nodes.hasNext()) 
			{
				Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodes.next();
				JsonNode tweets_node=objects_node.path("tweets");
				tweets.add(mapper.readValue(tweets_node.get(entry.getKey()).toString(), Tweet.class));
				//tweets.add(tmp);
			}
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return tweets;
	}
	
}
