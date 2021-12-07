/**
 * 
 */
package it.univpm.progetto.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import org.json.simple.JSONValue;

import org.json.JSONObject;

/**
 * @author ivan
 *
 */
public class APICall {

	public void searchapi(String query) {
		try {
			String url = "https://wd4hfxnxxa.execute-api.us-east-2.amazonaws.com/dev/api/1.1/users/search.json";
			HttpURLConnection openConnection = (HttpURLConnection) new URL(url).openConnection();
			openConnection.setRequestMethod("GET");
			//openConnection.setRequestProperty("Content-Type", "application/json");
			//openConnection.setRequestProperty("Accept", "application/json");
			openConnection.addRequestProperty("q", query);

			openConnection.setDoOutput(true);
			String jsonBody = "{\n" + "\"id\",\n" + "\"id_str\",\n" + "\"name\",\n" + "\"screen_name\",\n"
					+ "\"location\",\n" + "\"description\",\n" + "\"url\",\n" + "\"entities\",\n" + "\"protected\",\n"
					+ "\"followers_count\",\n" + "\"friends_count\",\n" + "\"listed_count\",\n" + "\"created_at\",\n"
					+ "\"favourites_count\",\n" + "\"utc_offset\",\n" + "\"time_zone\",\n" + "\"geo_enabled\",\n"
					+ "\"verified\",\n" + "\"statuses_count\",\n" + "\"lang\",\n" + "\"status\",\n"
					+ "\"contributors_enabled\",\n" + "\"is_translator\",\n" + "\"is_translation_enabled\",\n"
					+ "\"profile_background_color\",\n" + "\"profile_background_image_url\",\n"
					+ "\"profile_background_image_url_https\",\n" + "\"profile_background_tile\",\n"
					+ "\"profile_image_url\",\n" + "\"profile_image_url_https\",\n" + "\"profile_link_color\",\n"
					+ "\"profile_sidebar_border_color\",\n" + "\"profile_sidebar_fill_color\",\n"
					+ "\"profile_text_color\",\n" + "\"profile_use_background_image\",\n"
					+ "\"has_extended_profile\",\n" + "\"default_profile\",\n" + "\"default_profile_image\",\n"
					+ "\"following\",\n" + "\"follow_request_sent\",\n" + "\"notifications\",\n"
					+ "\"translator_type\",\n" + "\"withheld_in_countries\"\n" + "}";
			try (OutputStream os = openConnection.getOutputStream()) {
				byte[] input = jsonBody.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			InputStream in = openConnection.getInputStream();

			String data = "";
			String line = "";
			try {
				InputStreamReader inR = new InputStreamReader(in);
				BufferedReader buf = new BufferedReader(inR);

				while ((line = buf.readLine()) != null) {
					data += line;
					System.out.println(line);
				}
			} finally {
				in.close();
			}
			JSONObject obj = (JSONObject) JSONValue.parseWithException(data);
			System.out.println("OK");
		} catch (IOException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
