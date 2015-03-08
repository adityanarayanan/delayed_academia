package com.collectiveintelligence.recommend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.collectiveintelligence.extract.MoviePreferenceExtractor;
import com.collectiveintelligence.scoring.PearsonCoefficient;
import com.collectiveintelligence.scoring.ScorePair;
import com.collectiveintelligence.scoring.Scorer;

public class Recommender {
	
	// Gets recommendations for a person by using a weighted average of every other user's rankings
	public static List<ScorePair> getRecommendations(Map<String, Map<String, Double>> preferences,
			String person, Scorer simScorer) {
		Map<String, Double> totals = new HashMap<>();
		Map<String, Double> similaritySums = new HashMap<>();
		
		for (String other : preferences.keySet()) {
			// Don't compare me to myself
			if (other.equals(person)) {
				continue;
			}
			
			double simScore = simScorer.similarityDistance(person, other);
			// Ignore scores of zero or lower
			if (simScore <= 0) {
				continue;
			}
			
			for (String item : preferences.get(other).keySet()) {
				// Only score movies I haven't seen yet
				if (preferences.get(person).containsKey(item) && preferences.get(person).get(item) != 0) {
					continue;
				}
				// Similarity Score * Rating
				if (!totals.containsKey(item)) {
					totals.put(item, 0.0);
				}
				double currentTotal = totals.get(item);
				totals.put(item, currentTotal + preferences.get(other).get(item) * simScore);
				
				// Sum of similarities
				if (!similaritySums.containsKey(item)) {
					similaritySums.put(item, 0.0);
				}
				double currentSum = similaritySums.get(item);
				similaritySums.put(item, currentSum + simScore);
			}
		}
		
		List<ScorePair> rankings = new ArrayList<>();
		// Create the normalized list
		for (String item : totals.keySet()) {
			double total = totals.get(item);
			rankings.add(new ScorePair(total / similaritySums.get(item), item));
		}
		
		// Return the sorted list
		Collections.sort(rankings);
		Collections.reverse(rankings);
		return rankings;
	}
	
	public static void main(String[] args) {
		Map<String, Map<String, Double>> prefs = MoviePreferenceExtractor.getPreferencesFromResource();
		System.out.println("Preference map:\n" + prefs);
		System.out.println();
		System.out.println("Calculating recommedations for Toby...");
		List<ScorePair> recommendations = getRecommendations(prefs, "Toby", new PearsonCoefficient(prefs));
		System.out.println(recommendations);
	}
}
