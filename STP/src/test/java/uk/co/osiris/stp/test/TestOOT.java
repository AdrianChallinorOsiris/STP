/**
 * (c) 2018 Adrian Challinor, Osiris Consultsants Ltd
 *     All Rights reserved 
 *  Created: 17 Nov 2018
 */
package uk.co.osiris.stp.test;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.co.osiris.stp.NoMoreTokenException;
import uk.co.osiris.stp.Scanner;
import uk.co.osiris.stp.Token;
import uk.co.osiris.stp.Word;

/**
 * @author adrian
 *
 */
public class TestOOT {

	/** 
	 * Test out of token 
	 * @throws NoMoreTokenException 
	 */
	@Test(expected=NoMoreTokenException.class)
	public void testOOT() throws NoMoreTokenException {
		Scanner sc = new Scanner("One Two");
		assertEquals(2, sc.getTokens().size());
		
		Word w = new Word();
		Token t = w.match(sc);
		assertEquals("One", t.getSval());
		
		t = w.match(sc);
		assertEquals("Two", t.getSval());
		
		t = w.match(sc);
		fail("Exception not thrown");
	}

}
