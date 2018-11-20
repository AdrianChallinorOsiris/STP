/**
 * (c) 2018 Adrian Challinor, Osiris Consultsants Ltd
 *     All Rights reserved 
 *  Created: 17 Nov 2018
 */
package uk.co.osiris.stp.test;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.co.osiris.stp.*;

/**
 * @author adrian
 *
 */
public class TestParser {

	@Test
	public void testStandardParser() {
		Grammar STP = Seshat.seshat();
		CB cb = new CB(); 
		Parser p = new Parser(STP, cb);
		boolean b = p.check();
		assertTrue(b);
		
		Scanner sc = new Scanner("BACKUP '/home' auto exit");
		try {
			Result rc = p.parse(sc);
			assertEquals(Result.COMPLETE, rc);
		} catch (NoMoreTokenException e) {
			fail("Failed - out of tokens");
		}
	}

}
