package com.collectiveintelligence.scoring;

import java.util.Map;

import com.collectiveintelligence.extract.MoviePreferenceExtractor;

public class PearsonCoefficient extends Scorer {
	
	public PearsonCoefficient(Map<String, Map<String, Double>> preferences) {
		super(preferences);
	}

	@Override
	public double similarityDistance(String criticA, String criticB) {
		if (!(preferences.containsKey(criticA) && preferences.containsKey(criticB))) {
			return -1; // exception value
		}
		
		Map<String, Integer> shared = getSharedItems(criticA, criticB);
		int N = shared.size(); // N is the number of shared items
		if (N == 0) {
			return 0;
		}
		
		// Add up all the preferences for both critics, *AND*
		// Sum up all the squares of the preference values, *AND*
		// Also sum up their products
		double sumA = 0, sumB = 0;
		double sumASq = 0, sumBSq = 0;
		double pSum = 0;
		for (String item : shared.keySet()) {
			double valA = preferences.get(criticA).get(item);
			double valB = preferences.get(criticB).get(item);
			sumA += valA;
			sumB += valB;
			sumASq += Math.pow(valA, 2);
			sumBSq += Math.pow(valB, 2);
			pSum += valA * valB;
		}
		
		// Calculate Pearson score
		
		// Numerator is: Sum of products minus (product of sums divided by N)
		double numer = pSum - (sumA * sumB / N);
		// Denominator is: Square root of product of (sum of squares minus (square of sum divided by N) for each critic
		double denom = Math.sqrt((sumASq - Math.pow(sumA, 2) / N) * (sumBSq - Math.pow(sumB, 2) / N));
		if (denom == 0) {
			return 0;
		}
		return numer / denom;
	}
	
	public static void main(String[] args) {
		Map<String, Map<String, Double>> prefs = MoviePreferenceExtractor.getPreferencesFromResource();
		System.out.println("Preference map:\n" + prefs);
		System.out.println();
		Scorer scorer = new PearsonCoefficient(prefs);
		System.out.println("Similarity distance of Lisa Rose and Gene Seymour: "
				+ scorer.similarityDistance("Lisa Rose", "Gene Seymour"));

	}
}
