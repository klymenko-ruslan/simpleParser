package com.nordeamarkets.simpleParser.entities;

import java.util.Arrays;
import com.google.common.base.Objects;

/**
 * Sentence class.
 * Represents sentence with sorted words.
 * @author klymenko.ruslan
 *
 */
public class Sentence implements Comparable<Sentence>{
	
	public static final String SENTENCE_DELIMITER = "[\\.]+";
	public static final String NOT_WORD_REGEXP = "[^A-Za-z]+";
	
	private String [] sortedWords;
	
	/**
	 * Constructor with String argument.
	 * Parses words from sentence into String [].
	 * Sorts String [] with words.
	 * @param sentence
	 */
	public Sentence(String sentence) {
		parseWordsFromSentence(sentence);
	}
	
	/**
	 * Method parseWordsFromSentence parses words from sentence using "NOT_WORD_REGEXP".
	 * Words will be sorted in case insensitive way.
	 * @param sentence
	 */
	private void parseWordsFromSentence(String sentence) {
		this.sortedWords = trimSentence(sentence).split(NOT_WORD_REGEXP);
		Arrays.sort(sortedWords, String.CASE_INSENSITIVE_ORDER);
	}
	
	/**
	 * Method trimSentence trims 
	 * @param sentence
	 * @return
	 */
	private String trimSentence(String sentence) {
		int length = sentence.length();
		if(length > 1) {
			int beginning = 0;
			while(beginning < length && ("" + sentence.charAt(beginning)).matches(NOT_WORD_REGEXP)) {
				beginning++;
			}
			sentence = sentence.substring(beginning);
			int ending = sentence.length() - 1;
			while(ending >= 0 && ("" + sentence.charAt(ending)).matches(NOT_WORD_REGEXP)) {
				ending--;
			}
			sentence = sentence.substring(0, ending + 1);
		}
		return sentence;
	}
	
	/**
	 * isEmptySentence method returns true if sentence string doesn't contain any words.
	 * @param sentence
	 * @return
	 */
	public static boolean isEmptySentence(String sentence) {
		return sentence.length() == 0 || sentence.matches(NOT_WORD_REGEXP);
	}
	
	public String [] getSortedWords() {
		return sortedWords;
	}
	
	public int getLength() {
		return sortedWords.length;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("words", Arrays.toString(sortedWords)).toString();
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(this.sortedWords);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
	    if (getClass() != obj.getClass()) return false;
	    final Sentence other = (Sentence) obj;
		return Objects.equal(this.getSortedWords(), other.getSortedWords());
	}

	@Override
	public int compareTo(Sentence other) {
		return this.getLength() < other.getLength() ? -1 : 
			this.getLength() == other.getLength() ? 0 : 1;
	}
}
