package com.nordeamarkets.simpleParser.entities;

import com.google.common.base.Objects;

/**
 * Text class.
 * Represents text with collection of Sentence.
 * @author klymenko.ruslan
 *
 */
public class Text implements Comparable<Text>{
	
	private java.util.Collection<Sentence> sentences;
	
	public Text() {	
		this.sentences = new java.util.ArrayList<>();
	}	

	public Text(java.util.Collection<Sentence> sentences) {
		this.sentences = sentences;
	}
	
	public java.util.Collection<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(java.util.Collection<Sentence> sentences) {
		this.sentences = sentences;
	}
	
	public void addSentence(Sentence sentence) {
		sentences.add(sentence);
	}
	
	public void addSentences(java.util.Collection<Sentence> sentences) {
		this.sentences.addAll(sentences);
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("sentences", sentences).toString();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.sentences);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
	    if (getClass() != obj.getClass()) return false;
	    final Text other = (Text) obj;
		return Objects.equal(this.getSentences(), other.getSentences());
	}

	@Override
	public int compareTo(Text arg0) {
		return this.getSentences().size() < arg0.getSentences().size() ? - 1 : 
			this.getSentences().size() == arg0.getSentences().size() ? 0 : 1;
	}
}
