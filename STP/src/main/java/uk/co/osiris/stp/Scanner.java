/**
 * (c) 2018 Adrian Challinor, Osiris Consultsants Ltd
 *     All Rights reserved 
 *  Created: 14 Nov 2018
 */
package uk.co.osiris.stp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

/**
 * @author adrian
 *
 */
@Data
@Log4j2
public class Scanner {
	private int pos;
	private ArrayList<String> tokens;
	private String stateName;
	private boolean prompt;
	
	public Scanner() { 
		tokens = new ArrayList<String>();
		pos = 0; 
			prompt = true;
	}
	
	/**
	 * Construct a scanner from a prebuilt list of tokens. 
	 * @param tokens
	 */
	public Scanner(ArrayList<String> tokens) { 
		this.tokens = tokens;
		this.pos = 0;
	}
	
	/**
	 * Build a scanner from a string to be parsed
	 * 
	 * @param line
	 */
	public  Scanner(String line) {
		this.tokens = Tokenizer.tokenizer(line);
		this.pos = 0;
	}
	
	/** 
	 * Build a scanner from a file input 
	 * 
	 * @param file
	 */
	public  Scanner(File file) {
		this.tokens = Tokenizer.tokenizer(file);
		this.pos = 0;
	}
		
	// TODO - what happens when scanner is exhausted? 
	
	/**
	 * @param pattern
	 * @return
	 */
	public boolean hasNext() {
		return pos < tokens.size();
	}
	
	public String peek() throws NoMoreTokenException { 
		return peek(1);
	}

	public String peek(int n) throws NoMoreTokenException { 
		if (pos + n > tokens.size()) {
			if (prompt) 
				getMore();
			else
				throw new NoMoreTokenException();
		}
		
		StringBuffer sb = new StringBuffer(); 
		for (int i =0 ; i<n; i++) {
			sb.append(tokens.get(pos+i));
		}
		return sb.toString();
	}
	
	public String next() throws NoMoreTokenException {
		if (pos  > tokens.size()) {
			if (prompt) 
				getMore();
			else
				throw new NoMoreTokenException();
		}

		return tokens.get(pos++);
	}
	
	public String next(int n) throws NoMoreTokenException { 
		if (pos + n > tokens.size()) {
			if (prompt) 
				getMore();
			else
				throw new NoMoreTokenException();
		}

		StringBuffer sb = new StringBuffer(); 
		for (int i =0 ; i<n; i++) {
			sb.append(tokens.get(pos+i));
		}
		pos += n;
		return sb.toString();
	}
	
	private void getMore()  { 
	       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		stateName = StringUtils.lowerCase(stateName);
		stateName = StringUtils.capitalize(stateName);
		try {
			System.out.print(stateName + "> ");
			String line = null;
			while (line == null) {
				line = br.readLine();
				log.debug("Read: " + line);
			}
			ArrayList<String>newTokens = Tokenizer.tokenizer(line);
			tokens.addAll(newTokens);
			log.debug("Added tokens: " + newTokens.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void dump() { 
		if (log.isDebugEnabled()) {
			int l = pos + 10; 
			if (l > tokens.size() ) l = tokens.size();
			StringBuffer sb = new StringBuffer(); 
			for (int i=pos; i<l; i++)
				sb.append("<" + tokens.get(i) + "> ");
			log.debug("Tokens: " + sb.toString());
		}
	}

}
