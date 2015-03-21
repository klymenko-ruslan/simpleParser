package com.nordeamarkets.simpleParser;

import java.io.IOException;
import com.nordeamarkets.simpleParser.parser.CSVParser;
import com.nordeamarkets.simpleParser.parser.Parser;
import com.nordeamarkets.simpleParser.parser.XMLParser;

/*
 * Simple client class
 * Takes file's path from command line and parses content into XML and CSV
 */
public class App {
	
	// Enter path to the file with text 
	public static final String TEST_FILE_NAME = "test.txt";
	
	public static void main(String[] args) throws IOException {
		Parser [] parsers = {XMLParser.getInstance(), CSVParser.getInstance()};
		for(Parser parser : parsers)
			System.out.print(parser.parseFile(TEST_FILE_NAME));
	}
}
