/**
 * (c) 2018 Adrian Challinor, Osiris Consultsants Ltd
 *     All Rights reserved 
 *  Created: 16 Nov 2018
 */
package uk.co.osiris.stp.test;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.co.osiris.stp.*;

/**
 * 
 * Test case is a complex grammar, used by the CLI version of SESHAT. This sets
 * out a command languuge in the form of a grammar builder. The grammar is then
 * checked and pruned.
 * 
 * @author adrian
 *
 */

public class TestGrammar {

	@Test
	public void testseshat() {
		Grammar STP = Seshat.seshat();
		
		STP.state("NotUsed");
		
		STP.state("Notcalled").add(new Lambda());


		int pruned = STP.prune();
		boolean ok = STP.check();
		
		assertEquals(2, pruned);
		assertTrue(ok);
	}
}
