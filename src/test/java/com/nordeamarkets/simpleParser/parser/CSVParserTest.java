package com.nordeamarkets.simpleParser.parser;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Before;
import org.junit.Test;
import com.nordeamarkets.simpleParser.parser.CSVParser;

public class CSVParserTest extends ParserTestUtil{	
	
	public static final String TEST_STRING = "dfk; 93- c,z l kd[. dasflk . e 1 $%^&*(rtyvb.,.bfk";
	public static final String EXPECTED_RESULT = ", Word 1, Word 2, Word 3, Word 4, Word 5" + CSVParser.NEW_LINE + 
												 "Sentence 1, c, dfk, kd, l, z" + CSVParser.NEW_LINE +
												 "Sentence 2, dasflk" + CSVParser.NEW_LINE +
												 "Sentence 3, e, rtyvb" + CSVParser.NEW_LINE +
												 "Sentence 4, bfk" + CSVParser.NEW_LINE;
	public static final String EXPECTED_RESULT_FROM_FILE = ", Word 1, Word 2, Word 3, Word 4, Word 5, Word 6, Word 7, Word 8" + CSVParser.NEW_LINE + 
			 "Sentence 1, a, had, lamb, little, Mary" + CSVParser.NEW_LINE +
			 "Sentence 2, Aesop, and, called, came, for, Peter, the, wolf" + CSVParser.NEW_LINE +
			 "Sentence 3, Cinderella, likes, shoes" + CSVParser.NEW_LINE;
	
	private CSVParser parser;
	
	@Before
	public void setUp() {
		parser = CSVParser.getInstance();
	}
	
	@Test
	public void testParseFile() throws URISyntaxException {
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
