package com.nordeamarkets.simpleParser.parser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;

import com.google.common.io.Files;
import com.nordeamarkets.simpleParser.entities.Sentence;
import com.nordeamarkets.simpleParser.entities.Text;

/**
 * CSVParser class.
 * Parses content of source into CSV format. 
 * @author klymenko.ruslan
 *
 */
public class CSVParser extends Parser{

	public static final String WORD = "Word";
	public static final String SENTENCE = "Sentence";
	public static final String DELIMITER = ", ";
	public static final String NEW_LINE = System.getProperty("line.separator");
	public static final String WHITESPACE = " ";
		
	private CSVParser(){}
	
	public static CSVParser getInstance() {
		return new CSVParser();
	}
	
	/**
	 * parse method parses Text into CSV and returns String.
	 * On large inputs may cause OutOfMemoryError(StringBuilder couldn't contain
	 * such big string).
	 * For such large inputs recommended to use parseToConsole or parseToFile
	 */
	@Override
	public String parse(Text text) {
		int maxSentenceLength = Collections.max(text.getSentences()).getLength();
		int wordCount = 0;
		StringBuilder result = new StringBuilder();		
		while(wordCount++ < maxSentenceLength) {
			result.append(DELIMITER).append(WORD).append(WHITESPACE).append(wordCount);			
		}
		result.append(NEW_LINE);
		long sentenceCount = 1;
		for(Sentence sentence : text.getSentences()) {
			result.append(SENTENCE).append(WHITESPACE).append(sentenceCount);
			String []words = sentence.getSortedWords();
			for(String word : words) {
				result.append(DELIMITER).append(word);
			}
			result.append(NEW_LINE);
			sentenceCount++;
		}

		return result.toString();
	}
	
	/**
	 * parseToConsole method parses Text into CSV and prints result to the console.
	 */
	@Override
	public void parseToConsole(Text text) {
		int maxSentenceLength = Collections.max(text.getSentences()).getLength();
		int wordCount = 0;
		while(wordCount++ < maxSentenceLength) {
			System.out.print(DELIMITER + "" + WORD + "" + WHITESPACE + "" + wordCount);
		}
		System.out.println();
		long sentenceCount = 1;
		for(Sentence sentence : text.getSentences()) {
			System.out.print(SENTENCE + "" + WHITESPACE + "" + sentenceCount);
			String []words = sentence.getSortedWords();
			for(String word : words) {
				System.out.print(DELIMITER + "" + word);
			}
			System.out.print(NEW_LINE);
			sentenceCount++;
		}
	}
	
	/**
	 * parseToConsole method parses Text into CSV and saves result into file.
	 */
	@Override
	public void parseToFile(Text text, File file) throws IOException {
		int maxSentenceLength = Collections.max(text.getSentences()).getLength();
		int wordCount = 0;
		while(wordCount++ < maxSentenceLength) {
			String currentString = DELIMITER + "" + WORD + "" + WHITESPACE + "" + wordCount;	
			Files.append(currentString, file, Charset.defaultCharset());
		}		
		Files.append(NEW_LINE, file, Charset.defaultCharset());
		long sentenceCount = 1;
		for(Sentence sentence : text.getSentences()) {			
			Files.append((SENTENCE + "" + WHITESPACE + "" + sentenceCount), file, Charset.defaultCharset());
			String []words = sentence.getSortedWords();
			for(String word : words) {
				Files.append((DELIMITER + "" + word), file, Charset.defaultCharset());
			}
			Files.append(NEW_LINE, file, Charset.defaultCharset());
			sentenceCount++;
		}
	}
}
