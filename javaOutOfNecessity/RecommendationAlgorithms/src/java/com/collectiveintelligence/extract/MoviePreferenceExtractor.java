package com.collectiveintelligence.extract;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MoviePreferenceExtractor {
	
	private static JSONParser parser = new JSONParser();
	
	public static Map<String, Map<String, Double>> getPreferencesFromResource() {
		try (InputStream fin = MoviePreferenceExtractor.class.getClassLoader()
				.getResourceAsStream("movie_preferences.json")) {
			return getPreferences(fin);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Map<String, Map<String, Double>> getPreferences(InputStream fin) throws IOException, ParseException {
		Object obj = parser.parse(new InputStreamReader(fin));
		@SuppressWarnings("unchecked")
		Map<String, Map<String, Double>> jsonObj = (JSONObject) obj;
		return jsonObj;
	}
	
	public static void main(String[] args) {
		Map prefMap = getPreferencesFromResource();
		System.out.println(prefMap);
	}

}
