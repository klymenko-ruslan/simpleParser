package com.nordeamarkets.simpleParser.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

public abstract class ParserTestUtil {
	
	public static final String INPUT_URL = "/testInput.txt";
	public static final String LARGE_INPUT_URL = "/testInputLarge.txt";
	
	public String getTextFromPath(Path path) throws FileNotFoundException {
		StringBuilder result = new StringBuilder();
		Scanner scanner = new Scanner(new File(path.toString()));
		while(scanner.hasNextLine()) {
			result.append(scanner.nextLine()).append("\n");
			
		}
		scanner.close();
		
		return result.toString();
	}	
}
