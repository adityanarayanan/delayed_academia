package com.collectiveintelligence.scoring;

import java.util.Map;

import com.collectiveintelligence.extract.MoviePreferenceExtractor;

public class EuclideanDistance extends Scorer {
	
	public EuclideanDistance(Map<String, Map<String, Double>> preferences) {
		super(preferences);
	}
	
	// Returns a similarity score between 0 and 1 based on the sum of Euclidean
	// distances between the two given critics
	// Basically sum of squares of differences between the ratings of critic 1 and 2
	// is taken, incremented by 1, and inverted
	@Override
	public double similarityDistance(String criticA, String criticB) {
		if (!(preferences.containsKey(criticA) && preferences.containsKey(criticB))) {
			return -1; // exception value
		}
		
		Map<String, Integer> shared = getSharedItems(criticA, criticB);
		if (shared.size() == 0) { // if there aren't any shared items, return 0
			return 0;
		}
		
		// Calculate the sum of squares of the differences between the ratings given to common items by
		// different critics
		double sumOfSquares = 0;
		for (String item : preferences.get(criticA).keySet()) {
			if (preferences.get(criticB).containsKey(item)) {
				sumOfSquares += Math.pow(preferences.get(criticA).get(item) - preferences.get(criticB).get(item), 2);
			}
		}
		
		return 1 / (1 + sumOfSquares);
	}
	
	public static void main(String[] args) {
		Map<String, Map<String, Double>> prefs = MoviePreferenceExtractor.getPreferencesFromResource();
		System.out.println("Preference map:\n" + prefs);
		System.out.println();
		Scorer scorer = new EuclideanDistance(prefs);
		System.out.println("Similarity distance of Lisa Rose and Gene Seymour: "
				+ scorer.similarityDistance("Lisa Rose", "Gene Seymour"));
	}

}
