package com.nordeamarkets.simpleParser.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.nordeamarkets.simpleParser.parser.XMLParser;

public class XMLParserTest extends ParserTestUtil{
	
	public static final String TEST_STRING = "dfk; 93- c,z l kd[. dasflk . e 1 $%^&*(rtyvb.,.bfk";
	public static final String EXPECTED_RESULT = XMLParser.XML_DECLARATION + XMLParser.DELIMITER +
												 XMLParser.TEXT_OPEN + XMLParser.DELIMITER +
												 XMLParser.TAB + XMLParser.SENTENCE_OPEN + XMLParser.DELIMITER +
												 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "c" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
												 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "dfk" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
												 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "kd" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
												 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "l" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
												 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "z" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
												 XMLParser.TAB + XMLParser.SENTENCE_CLOSE + XMLParser.DELIMITER +
												 XMLParser.TAB + XMLParser.SENTENCE_OPEN + XMLParser.DELIMITER +
												 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "dasflk" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
												 XMLParser.TAB + XMLParser.SENTENCE_CLOSE + XMLParser.DELIMITER +
												 XMLParser.TAB + XMLParser.SENTENCE_OPEN + XMLParser.DELIMITER +
												 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "e" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
												 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "rtyvb" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
												 XMLParser.TAB + XMLParser.SENTENCE_CLOSE + XMLParser.DELIMITER +
												 XMLParser.TAB + XMLParser.SENTENCE_OPEN + XMLParser.DELIMITER +
												 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "bfk" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
												 XMLParser.TAB + XMLParser.SENTENCE_CLOSE + XMLParser.DELIMITER +
												 XMLParser.TEXT_CLOSE + XMLParser.DELIMITER;
	
	public static final String EXPECTED_RESULT_FROM_FILE = XMLParser.XML_DECLARATION + XMLParser.DELIMITER +
			 XMLParser.TEXT_OPEN + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.SENTENCE_OPEN + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "a" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "had" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "lamb" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "little" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "Mary" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.SENTENCE_CLOSE + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.SENTENCE_OPEN + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "Aesop" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "and" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "called" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "came" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "for" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "Peter" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "the" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "wolf" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.SENTENCE_CLOSE + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.SENTENCE_OPEN + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "Cinderella" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "likes" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.TAB + XMLParser.WORD_OPEN + "shoes" + XMLParser.WORD_CLOSE + XMLParser.DELIMITER +
			 XMLParser.TAB + XMLParser.SENTENCE_CLOSE + XMLParser.DELIMITER +
			 XMLParser.TEXT_CLOSE + XMLParser.DELIMITER;
	
	private XMLParser parser;
	
	@Before
	public void setUp() {
		parser = XMLParser.getInstance();
	}
	
	@Test
	public void testParseFile() throws URISyntaxException, ParserConfigurationException, SAXException, IOException {
		URL inputUrl = getClass().getResource(INPUT_URL);
		Path inputPath = Paths.get(inputUrl.toURI());
		try {			
			String result = parser.parseFile(new File(inputPath.toString()));
			assertEquals(result, EXPECTED_RESULT_FROM_FILE);
		} catch (FileNotFoundException e) {
			fail("File not found exception.");
		}
	}
	
	@Test
	public void testParseString() throws URISyntaxException {
		String result = parser.parseString(TEST_STRING);
		String errorMessage = "Expected: " + EXPECTED_RESULT + "\n Actual: " + result;
		assertEquals(errorMessage, result, EXPECTED_RESULT);
	}
	
	@Test
	public void testParseWithLargeData() throws URISyntaxException {
		URL inputUrl = getClass().getResource(LARGE_INPUT_URL);
		Path inputPath = Paths.get(inputUrl.toURI());
		try {			
			parser.parseFile(new File(inputPath.toString()));
		} catch (FileNotFoundException e) {
			fail("File not found exception.");
		} catch (OutOfMemoryError e) {
			fail("Out of memory error.");
		}
	}
}
