/**
 * 
 */
package it.univpm.progetto.model;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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

public class Tweet {
	@JsonIgnore
	private List<Tweet> tweets=new ArrayList<>();
	@JsonIgnore
	private static final String TWEET_URL_API="https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/user/1.1/statuses/user_timeline.json?id=";
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE MMM dd HH:mm:ss Z yyyy",locale = "en")
	private Date created_at;
	@JsonAlias("id_str")
	private String id;
	private String text;
	
	private List<Entity> entities=new ArrayList<>();	
	@JsonAlias("retweet_count")
	private int retweets;//Number of times this Tweet has been retweeted
	@JsonAlias("favorite_count")
	private int likes;//Nullable. Indicates approximately how many times this Tweet has been liked by Twitter users
	@JsonAlias("retweeted_status")
	private List<Tweet> retweets_status=new ArrayList<>();
	
	public Tweet() {}
	public Tweet(String id) throws IllegalArgumentException, IOException 
	{
		
		String url=TWEET_URL_API+id;
		APIImpl call=new APIImpl(url);
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		try {
			
			/*JsonNode node=mapper.valueToTree(call.getData());
			JsonNode ent=node.at("entities");*/
			
			//entities=Arrays.asList(mapper.readValue(call.getData(), Entities[].class));
			
			tweets = Arrays.asList(mapper.readValue(call.getData(), Tweet[].class));
			
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @return the tweets
	 */
	public List<Tweet> getTweets() {
		return tweets;
	}

	/**
	 * @return the created_at
	 */
	public Date getCreated_at() {
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
	public List<Entity> getEntities() {
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
	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	/**
	 * @param created_at the created_at to set
	 * @throws ParseException 
	 */
	public void setCreated_at(Date created_at)  {
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
	public void setEntities(List<Entity> entities) {
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
	public List<Tweet> getRetweeted_status() {
		return retweets_status;
	}
	/**
	 * @param retweeted_status the retweeted_status to set
	 */
	public void setRetweeted_status(List<Tweet> retweeted_status) {
		this.retweets_status = retweeted_status;
	}
	@Override
	public String toString() {
		return "Tweets [tweets=" + tweets + ", created_at=" + created_at + ", id=" + id + ", text=" + text
				+ ", entities=" + entities + ", retweets=" + retweets + ", likes=" + likes + ", retweeted_status="
				+ retweets_status + "]";
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
