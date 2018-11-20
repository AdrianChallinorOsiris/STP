/**
 * (c) 2018 Adrian Challinor, Osiris Consultsants Ltd
 *     All Rights reserved 
 *  Created: 14 Nov 2018
 */
package uk.co.osiris.stp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author adrian
 *
 */
public class Tokenizer {
	// Specify delimiters 
	public static final String delims = " :'~;[]|{}<>+-!£$%^&*/@\\//\"\t"; 
	/**
	 * Given a line of text, break it in to tokens. Discard any comments, 
	 * ie anyting that follows a #. Discard any spaces that come back as 
	 * delimiters. 
	 * 
	 * Do special processing on quoted strings ("). Make multiple tokens between 
	 * them to form one quoted string. 
	 * 
	 * @param string
	 * @return
	 */
	public static ArrayList<String> tokenizer(String line) {
		ArrayList<String> tokens = new ArrayList<String>();
		
		// Remove comments 
		int hash = line.indexOf('#');
		if (hash > 0) {
			line = line.substring(0,  hash);
		}

		StringTokenizer st = new StringTokenizer(line, delims, true);
	
		while (st.hasMoreElements()) {
	        String s = st.nextToken();
	        
	        // Look for quoted strings. Double quoted strings have space between them.
	        if (s.equals("\"")) {
	        	StringBuilder sb = new StringBuilder();
	        	s = st.nextToken();
	        	while (!s.equals("\"")) {
	        		sb.append(s);
	        		s = st.nextToken();
	        	}
	        	s = sb.toString();
	        }
	
	        // Look for quoted strings. Single quoted strings don't have space between them.
	        if (s.equals("'")) {
	        	StringBuilder sb = new StringBuilder();
	        	s = st.nextToken();
	        	while (!s.equals("'")) {
	        		if (!s.equals(" "))
	        			sb.append(s);
	        		s = st.nextToken();
	        	}
	        	s = sb.toString();
	        }
	        
	        // Ignore spaces - they are delimiters between tokens that we ignore
	        if (!s.equals(" ") && !s.equals("\t"))
	        	tokens.add(s);
	    }
		return tokens;
	}

	/**
	 * Given an array of lines, ask the line tokenizer to 
	 * do its stuff on each line. Append all the tokens in to 
	 * one list to return. 
	 * 
	 * @param input
	 * @return
	 */
	public static ArrayList<String> tokenizer(List<String> input) {
		ArrayList<String> tokens = new ArrayList<String>();
		for (String line : input) {
			tokens.addAll(tokenizer(line));
		}
		return tokens;
	}

	/**
	 * Read a file from a path name. Take out any lines that begin with # 
	 * Put this in to an aray and ask the array processor to handle the tokenisation 
	 * 
	 * @param path
	 * @return
	 */
	public static ArrayList<String> tokenizer(File file) {
		List<String> list = new ArrayList<String>();
		// Read the file, omitting any lines that start with #
		try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
			list = stream
					.filter(line -> !line.startsWith("#"))
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return tokenizer(list);
	}

}
