package com.nordeamarkets.simpleParser.parser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import com.google.common.io.Files;
import com.nordeamarkets.simpleParser.entities.Sentence;
import com.nordeamarkets.simpleParser.entities.Text;

/**
 * XMLParser class.
 * Parses resource into XML with "<text>", "<sentence>" and "<word>" nodes.
 * @author klymenko.ruslan
 *
 */
public class XMLParser extends Parser{
	
	public static final String DELIMITER = System.getProperty("line.separator");
	public static final String TAB = "\t";
	public static final String XML_DECLARATION = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
	public static final String TEXT_OPEN = "<text>";
	public static final String TEXT_CLOSE = "</text>";
	public static final String SENTENCE_OPEN = "<sentence>";
	public static final String SENTENCE_CLOSE = "</sentence>";
	public static final String WORD_OPEN = "<word>";
	public static final String WORD_CLOSE = "</word>";
	
	private XMLParser(){}
	
	public static XMLParser getInstance() {
		return new XMLParser();
	}
	
	/**
	 * parse method parses Text into XML.
	 * On large inputs may cause OutOfMemoryError(StringBuilder couldn't contain
	 * such big string).
	 * For such large inputs recommended to use parseToConsole or parseToFile
	 */
	@Override
	public String parse(Text text) {
		StringBuilder result = new StringBuilder();
		result.append(XML_DECLARATION).append(DELIMITER);
		result.append(TEXT_OPEN).append(DELIMITER);
		
		for(Sentence sentence : text.getSentences()) {
			String [] words = sentence.getSortedWords();
			result.append(TAB).append(SENTENCE_OPEN).append(DELIMITER);			
			for(String word : words) {
				result.append(TAB).append(TAB).append(WORD_OPEN).append(word).append(WORD_CLOSE).append(DELIMITER);
			}			
			result.append(TAB).append(SENTENCE_CLOSE).append(DELIMITER);
		}	
		
		result.append(TEXT_CLOSE).append(DELIMITER);

		return result.toString();
	}
	
	/**
	 * parseToConsole method parses Text into XML and print result to console.
	 */
	@Override
	public void parseToConsole(Text text) {
		System.out.print(XML_DECLARATION + "" + DELIMITER);
		System.out.print(TEXT_OPEN + "" + DELIMITER);
		for(Sentence sentence : text.getSentences()) {
			String [] words = sentence.getSortedWords();
			System.out.print(TAB + "" + SENTENCE_OPEN + "" + DELIMITER);
			for(String word : words) {
				System.out.print(new StringBuilder(TAB).append(TAB).append(WORD_OPEN)
						.append(word).append(WORD_CLOSE).append(DELIMITER));
			}			
			System.out.print(TAB + "" + SENTENCE_CLOSE + "" + DELIMITER);
		}	
		System.out.print(TEXT_CLOSE + "" + DELIMITER);
	}

	/**
	 * parseToConsole method parses Text into XML and saves result into file.
	 */
	@Override
	public void parseToFile(Text text, File file) throws IOException {
		Files.append(XML_DECLARATION + "" + DELIMITER, file, Charset.defaultCharset());
		Files.append(TEXT_OPEN + "" + DELIMITER, file, Charset.defaultCharset());		
		for(Sentence sentence : text.getSentences()) {
			String [] words = sentence.getSortedWords();
			Files.append(TAB + "" + SENTENCE_OPEN + "" + DELIMITER, file, Charset.defaultCharset());
			for(String word : words) {
				Files.append(TAB + "" + TAB + "" + WORD_OPEN + "" + word + "" + WORD_CLOSE + "" + DELIMITER, file, Charset.defaultCharset());
			}			
			Files.append(TAB + "" + SENTENCE_CLOSE + "" + DELIMITER, file, Charset.defaultCharset());
		}	
		Files.append(TEXT_CLOSE + "" + DELIMITER, file, Charset.defaultCharset());
	}
}
