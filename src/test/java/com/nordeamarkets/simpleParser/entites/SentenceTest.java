package com.nordeamarkets.simpleParser.entites;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nordeamarkets.simpleParser.entities.Sentence;

public class SentenceTest {
	
	private static final String testSentence = "Obla di obla  ' da \n \b \t hop hip world.\n 75423withnums3! here is text string";
	private static final String [] expectedSortedSentenceWords = {"da","di","here","hip","hop","is","Obla","obla","string","text", "withnums","world"};
	private static final int expectedSentenceLength = 12;
	
	private Sentence sentence;
	
	@Before
	public void setUp() {
		sentence = new Sentence(testSentence);
	}
	
	@Test
	public void testLength() {
		Assert.assertTrue(sentence.getLength() == expectedSentenceLength);
	}
	
	@Test
	public void testSortedWords() {
		Assert.assertTrue(Arrays.equals(sentence.getSortedWords(), expectedSortedSentenceWords));
	}
	
	@Test
	public void testIsEmpty() {
		Assert.assertTrue(Sentence.isEmptySentence(""));
		Assert.assertTrue(Sentence.isEmptySentence(" "));
		Assert.assertTrue(Sentence.isEmptySentence(" 	\\!@#$%^&*(+_"));
		
		Assert.assertFalse(Sentence.isEmptySentence("@#$word+(*"));
		Assert.assertFalse(Sentence.isEmptySentence(".x."));
	}
}
