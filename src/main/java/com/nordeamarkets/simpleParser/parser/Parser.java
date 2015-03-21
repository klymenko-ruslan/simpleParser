package com.nordeamarkets.simpleParser.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nordeamarkets.simpleParser.entities.Sentence;
import com.nordeamarkets.simpleParser.entities.Text;

/**
 * Abstract parses class.
 * Defines concrete and abstract methods for parsing.
 * @author klymenko.ruslan
 *
 */
public abstract class Parser {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Parser.class);
	
	/**
	 * ParseFile method with java.io.File argument
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public String parseFile(java.io.File file) throws FileNotFoundException {
		try {
			return parse(getTextFromFile(file));
		} catch (FileNotFoundException e) {
			LOGGER.error("Parser.parseFile(File file): \n" + e);
			throw new FileNotFoundException();
		}
	}
	
	/**
	 * parseFileToConsole method with java.io.File argument
	 * @param file
	 * @throws FileNotFoundException
	 */
	public void parseFileToConsole(java.io.File file) throws FileNotFoundException {
		try {
			parseToConsole(getTextFromFile(file));
		} catch (FileNotFoundException e) {
			LOGGER.error("Parser.parseFileToConsole(File file): \n" + e);
			throw new FileNotFoundException();
		}
	}
	
	/**
	 * parseFileToFile with two java.io.File arguments 
	 * @param src
	 * @param dest
	 * @throws IOException
	 */
	public void parseFileToFile(java.io.File src, java.io.File dest) throws IOException {		
		try {
			parseToFile(getTextFromFile(src), dest);
		} catch (IOException e) {
			LOGGER.error("Parser.parseFileToFile(File src, File dest): \n" + e);
			throw new IOException();
		}
	}
	
	/**
	 * ParseFile method with String argument.
	 * String should point to file location.
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	public String parseFile(String fileName) throws FileNotFoundException {
		return parseFile(new java.io.File(fileName));
	}
	
	/**
	 * parseFileToConsole with with String argument.
	 * String should point to file location.
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public void parseFileToConsole(String fileName) throws FileNotFoundException {
		parseFileToConsole(new java.io.File(fileName));
	}
	
	/**
	 * parseFileToFile with two String arguments.
	 * Strings should point to files locations.
	 * @param src
	 * @param dest
	 * @throws IOException
	 */
	public void parseFileToFile(String src, String dest) throws IOException {
		parseToFile(getTextFromFile(new java.io.File(src)), new java.io.File(dest));
	}
	
	/**
	 * parseString method which parses from string argument.
	 * @param sourceName
	 * @return
	 */
	public String parseString(String text) {
		return parse(getTextFromString(text));
	}
	
	/**
	 * parseStringToConsole with String argument.
	 * @param text
	 */
	public void parseStringToConsole(String text) {
		parseToConsole(getTextFromString(text));
	}
	
	/**
	 * parseFromInput method get user input which ends when user types
	 * "delimiter" value and parses it.
	 * @param delimiter
	 * @return
	 */
	public String parseFromInput(String delimiter) {
		return parse(getTextFromString(getInput(delimiter)));
	}
	
	/**
	 * parseFromInputToConsole with String argument.
	 * String represents delimiter which stops user input.
	 * @param delimiter
	 */
	public void parseFromInputToConsole(String delimiter) {
		parseToConsole(getTextFromString(getInput(delimiter)));
	}
	
	/**
	 * getInput method with String argument.
	 * Returns user input which ends by delimiter.
	 * @param delimiter
	 * @return
	 */
	private String getInput(String delimiter) {
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		scanner.useDelimiter(delimiter);
		String input = scanner.next();
		scanner.close();
		return input;
	}
	
	/**
	 * Abstract parse method.
	 * Concrete realizations may vary.
	 * @param sentences
	 * @return
	 */
	public abstract String parse(Text text);
	
	/**
	 * Abstract parseToConsole method.
	 * Concrete realizations may vary.
	 * @param text
	 */
	public abstract void parseToConsole(Text text);
	
	/**
	 * Abstract parseToFile method.
	 * Concrete realizations may vary.
	 * @param text
	 * @param file
	 * @throws IOException
	 */
	public abstract void parseToFile(Text text, File file) throws IOException;
	
	/**
	 * getSentencesFromString method
	 * Returns java.util.Collection of Sentence.
	 * @param text
	 * @return
	 */
	public Text getTextFromString(String input) {
		Text text = new Text();
		String [] stringSentences = input.split(Sentence.SENTENCE_DELIMITER);
		for(String currentSentence : stringSentences) {
			if(!Sentence.isEmptySentence(currentSentence))
				text.addSentence(new Sentence(currentSentence));
		}
		return text;
	}
	/**
	 * getTextFromFile method.
	 * Returns Text object which contains all sentences.
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public Text getTextFromFile(java.io.File file) throws FileNotFoundException {
		java.util.Scanner scanner = null;
		try {
			scanner = new java.util.Scanner(file);
			scanner.useDelimiter(Sentence.SENTENCE_DELIMITER);		
			Text text = new Text();
			while(scanner.hasNext()) {
				text.addSentence((new Sentence(scanner.next())));
			}			
			return text;
		} catch (FileNotFoundException e) {
			LOGGER.error("Parser.getSentencesFromFile(File file): \n" + e);
			throw new FileNotFoundException();
		} finally {
            if(scanner != null)
			    scanner.close();
		}
	}
	
}
