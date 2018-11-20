/**
 * (c) 2018 Adrian Challinor, Osiris Consultsants Ltd
 *     All Rights reserved 
 *  Created: 13 Nov 2018
 */
package uk.co.osiris.stp.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import uk.co.osiris.stp.Tokenizer;

/**
 * @author adrian
 *
 */
public class TestTokenizer {

	@Test
	public void testSimple() {
		ArrayList<String> t = Tokenizer.tokenizer("The cat sat on the mat");
		assertEquals(6, t.size());
	}

	@Test
	public void testSimpleQuoted() {
		ArrayList<String> t = Tokenizer.tokenizer("The cat sat on the \"mat by the door\"");
		assertEquals(6, t.size());
	}

	@Test
	public void testArray() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("EXIT :exit ~ ;");  																// 5
		input.add("COMPUTER %STRING:cname {"); 														// 6
		input.add("TYPE [ winxp:typeXP | win7:typeWin7 | win8:typeWin8 | linux:typeLinux ] ,");		// 19. Total = 30
		
		ArrayList<String> t = Tokenizer.tokenizer(input);
		assertEquals(30, t.size());
	}

	@Test
	public void testFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("seshat.grammar").getFile());
		ArrayList<String> t = Tokenizer.tokenizer(file);
		assertEquals(307, t.size());
	}

}
