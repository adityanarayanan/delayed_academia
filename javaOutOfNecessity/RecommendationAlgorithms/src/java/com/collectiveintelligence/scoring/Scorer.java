package com.collectiveintelligence.scoring;

import java.util.HashMap;
import java.util.Map;

public abstract class Scorer {
	
	protected Map<String, Map<String, Double>> preferences;
	
	public Scorer(Map<String, Map<String, Double>> preferences) {
		this.preferences = preferences;
	}
	
	protected Map<String, Integer> getSharedItems(String criticA, String criticB) {
		// Get a list of shared items
		Map<String, Integer> shared = new HashMap<String, Integer>();
		for (String item : preferences.get(criticA).keySet()) { // for each movie-rating pairing for critic A, with 'item' meaning movie
			if (preferences.get(criticB).containsKey(item)) { // if the same movie is found in the preferences of critic B
				shared.put(item, 1);
			}
		}
		return shared;
	}
	
	public abstract double similarityDistance(String criticA, String criticB);

}
