/**
 * 
 */
package it.univpm.progetto.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.univpm.progetto.service.APIImpl;

/**
 * @author ivan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Collections {

	private List<Timelines> timeline=new ArrayList<>();
	private Map<String,Timelines > map=new HashMap<>();
	private String name;
	private Timelines time=new Timelines();
	private String description;
	@JsonIgnore
	private List<Collections> collection =new ArrayList<>();
	private String collections_url_api="https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/user/1.1/collections/list.json?user_id=";

	/**
	 * 
	 */
	public Collections() {
	}
	
	public Collections(String id) throws IllegalArgumentException, IOException 
	{
		
		String url=collections_url_api+id;
		APIImpl call=new APIImpl(url);
		ObjectMapper mapper=new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		JsonNode node=mapper.readTree(call.getData());
		
		JsonNode tls=node.path("objects");
		Iterator<Entry<String, JsonNode>> nodes = tls.get("timelines").fields();
		while (nodes.hasNext()) 
		{
			  Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodes.next();
			  JsonNode tl_id=tls.path("timelines");
				/* for(JsonNode temptimeline: tl_id.get(entry.getKey())) 
				//{*/
					
					/*this.timeline.add(new Timelines(temptimeline.get("name").textValue(),
							temptimeline.get("description").textValue(),
							entry.getKey()));*/
					
				//}
			  String a=tl_id.get(entry.getKey()).toString().replace("}", ",\"id\":\""+entry.getKey()+"\"}");
			  time=mapper.readValue(a, Timelines.class);
			  map.put(tl_id.get(entry.getKey()).get("name").asText(),time);
			  	//System.out.println( tl_id.get(entry.getKey()));
			}
		/*try {
			
			/*JsonNode node=mapper.valueToTree(call.getData());
			JsonNode ent=node.at("entities");*/
			
			//entities=Arrays.asList(mapper.readValue(call.getData(), Entities[].class));
			//collection = Arrays.asList(mapper.readValue(call.getData(), Collections[].class));
			
		
		
		
	}
	
	

	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the collection
	 */
	public List<Collections> getCollection() {
		return collection;
	}

	/**
	 * @param collection the collection to set
	 */
	public void setCollection(List<Collections> collection) {
		this.collection = collection;
	}

	/**
	 * @return the timeline
	 */
	public List<Timelines> getTimeline() {
		return timeline;
	}

	/**
	 * @param timeline the timeline to set
	 */
	public void setTimeline(List<Timelines> timeline) {
		this.timeline = timeline;
	}

	/**
	 * @return the map
	 */
	public Map<String, Timelines> getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String, Timelines> map) {
		this.map = map;
	}
	
	
	
}
