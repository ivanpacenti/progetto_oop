/**
 * 
 */
package it.univpm.progetto.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

import it.univpm.progetto.service.APIImpl;

/**
 * @author ivan
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tweets {
	@JsonIgnore
	private List<Tweets> tweets=new ArrayList<>();
	@JsonIgnore
	private static final String tweets_url_api="https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/user/1.1/statuses/user_timeline.json?id=";
	
	private String created_at;
	@JsonAlias("id_str")
	private String id;
	private String text;
	//@JsonSerialize
	private List<Entities> entities=new ArrayList<>();	
	@JsonAlias("retweet_count")
	private int retweets;//Number of times this Tweet has been retweeted
	@JsonAlias("favorite_count")
	private int likes;//Nullable. Indicates approximately how many times this Tweet has been liked by Twitter users
	private List<Tweets> retweeted_status=new ArrayList<>();
	
	public Tweets() {}
	public Tweets(String id) throws IllegalArgumentException, IOException 
	{
		
		String url=tweets_url_api+id;
		APIImpl call=new APIImpl(url);
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		try {
			
			/*JsonNode node=mapper.valueToTree(call.getData());
			JsonNode ent=node.at("entities");*/
			
			//entities=Arrays.asList(mapper.readValue(call.getData(), Entities[].class));
			
			tweets = Arrays.asList(mapper.readValue(call.getData(), Tweets[].class));
			
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @return the tweets
	 */
	public List<Tweets> getTweets() {
		return tweets;
	}

	/**
	 * @return the created_at
	 */
	public String getCreated_at() {
		return created_at;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return the entities
	 */
	public List<Entities> getEntities() {
		return entities;
	}

	/**
	 * @return the retweets
	 */
	public int getRetweets() {
		return retweets;
	}

	/**
	 * @return the likes
	 */
	public int getLikes() {
		return likes;
	}

	/**
	 * @param tweets the tweets to set
	 */
	public void setTweets(List<Tweets> tweets) {
		this.tweets = tweets;
	}

	/**
	 * @param created_at the created_at to set
	 */
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @param entities the entities to set
	 */
	public void setEntities(List<Entities> entities) {
		this.entities = entities;
	}

	/**
	 * @param retweets the retweets to set
	 */
	public void setRetweets(int retweets) {
		this.retweets = retweets;
	}

	/**
	 * @param likes the likes to set
	 */
	public void setLikes(int likes) {
		this.likes = likes;
	}
	/**
	 * @return the retweeted_status
	 */
	public List<Tweets> getRetweeted_status() {
		return retweeted_status;
	}
	/**
	 * @param retweeted_status the retweeted_status to set
	 */
	public void setRetweeted_status(List<Tweets> retweeted_status) {
		this.retweeted_status = retweeted_status;
	}
	@Override
	public String toString() {
		return "Tweets [tweets=" + tweets + ", created_at=" + created_at + ", id=" + id + ", text=" + text
				+ ", entities=" + entities + ", retweets=" + retweets + ", likes=" + likes + ", retweeted_status="
				+ retweeted_status + "]";
	}
	
	
	/**
	 * @param created_at
	 * @param id
	 * @param text
	 * @param entities
	 */
	/*public Tweets(String created_at, String id, String text, JSONObject entitiesobj,int retweets,int favorite_count) {
		this.created_at = created_at;
		this.id = id;
		this.text = text;
		JSONArray hashtag_json=entitiesobj.getJSONArray("hashtags");
		for (int i = 0; i < hashtag_json.length(); i++) {
			JSONObject obj = hashtag_json.getJSONObject(i);
			hashtags.add(new Hashtags(obj.getString("text")));
		}
		this.entities=new Entities(hashtags);
		this.retweets=retweets;
		this.favorite_count=favorite_count;
		
	}*/
	
	

	
	
	
	
	
	
}
