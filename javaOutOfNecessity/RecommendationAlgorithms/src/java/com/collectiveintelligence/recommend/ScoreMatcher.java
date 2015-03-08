package com.collectiveintelligence.recommend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.collectiveintelligence.extract.MoviePreferenceExtractor;
import com.collectiveintelligence.scoring.PearsonCoefficient;
import com.collectiveintelligence.scoring.ScorePair;
import com.collectiveintelligence.scoring.Scorer;

public class ScoreMatcher {
	
	// Returns the best matches for the person from the 'preferences' map
	public static List<ScorePair> topMatches(Map<String, Map<String, Double>> preferences, String person,
			int numResults, Scorer simScorer) {
		List<ScorePair> scores = new ArrayList<ScorePair>();
		for (String other : preferences.keySet()) {
			if (!other.equals(person)) {
				scores.add(new ScorePair(simScorer.similarityDistance(person, other), other));
			}
		}
		// Sort the list so the highest scores appear on top
		Collections.sort(scores);
		Collections.reverse(scores);
		if (numResults >= scores.size()) {
			return scores;
		}
		return scores.subList(0, numResults);
	}
	
	public static void main(String[] args) {
		Map<String, Map<String, Double>> prefs = MoviePreferenceExtractor.getPreferencesFromResource();
		System.out.println("Preference map:\n" + prefs);
		System.out.println();
		System.out.println("Calculating top matches for Toby...");
		List<ScorePair> matches = topMatches(prefs, "Toby", 3, new PearsonCoefficient(prefs));
		System.out.println(matches);
	}
}
