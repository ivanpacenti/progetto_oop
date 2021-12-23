/**
 * 
 */
package it.univpm.progetto.model;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
import it.univpm.progetto.stats.DataStats;

/**
 * @author ivan
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tweet {
	@JsonIgnore
	private List<Tweet> tweets=new ArrayList<>();
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "E MMM dd HH:mm:ss Z yyyy",locale = "en")
	private Date created_at;
	@JsonAlias("id_str")
	private String id;
	private String text;
	@JsonIgnore
	private Double engagement;
	private Entity entities=new Entity();
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@JsonAlias("retweet_count")
	private int retweets;//Number of times this Tweet has been retweeted
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@JsonAlias("favorite_count")
	private int likes;//Nullable. Indicates approximately how many times this Tweet has been liked by Twitter users
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	private Account user=new Account();
	@JsonProperty(access=JsonProperty.Access.READ_ONLY)
	private DataStats stats;
	@JsonAlias("retweeted_status")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Tweet original_tweet;
	@JsonAlias("quoted_status")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Tweet quoted_tweet;
	
	
	public Tweet() {}
	

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
	public Entity getEntities() {
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
		this.text=text.replaceAll("@.*? ", "").replaceAll("#[^# ]*","").replaceAll("\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]","").replaceAll("\"","").replaceAll("\\n", "");
		//this.text=text;
	}

	/**
	 * @param entities the entities to set
	 */
	public void setEntities(Entity entities) {
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
	
	
	

	/**
	 * @return the user
	 */
	public Account getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(Account user) {
		this.user = user;
	}


	/**
	 * @return the stats
	 */
	public DataStats getStats() {
		this.stats = new DataStats(this.getLikes(), this.getRetweets(),entities.getHashtags().size(),
				entities.getMentions().size(),user.getFollowers(),user.getFriends(),
				user.getListed(),user.getUsername());
		
		return stats;
	}


	/**
	 * @return the engagement
	 */
	public Double getEngagement() {
		return stats.getEngagement();
	}


	/**
	 * @return the original_tweet
	 */
	public Tweet getOriginal_tweet() {
		return original_tweet;
	}


	/**
	 * @param original_tweet the original_tweet to set
	 */
	public void setOriginal_tweet(Tweet original_tweet) {
		this.original_tweet = original_tweet;
	}


	/**
	 * @return the quoted_tweet
	 */
	public Tweet getQuoted_tweet() {
		return quoted_tweet;
	}


	/**
	 * @param quoted_tweet the quoted_tweet to set
	 */
	public void setQuoted_tweet(Tweet quoted_tweet) {
		this.quoted_tweet = quoted_tweet;
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
