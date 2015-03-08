package com.collectiveintelligence.scoring;

public class ScorePair implements Comparable<ScorePair> {
	
	public final double score;
	public final String scoredEntity;
	
	public ScorePair(double score, String scoredEntity) {
		this.score = score;
		this.scoredEntity = scoredEntity;
	}
	
	@Override
	public String toString() {
		return "ScorePair [score=" + score + ", scoredEntity=" + scoredEntity + "]";
	}

	@Override
	public int compareTo(ScorePair o) {
		return score < o.score ? -1 : score > o.score ? 1 : 0;
	}	
}
