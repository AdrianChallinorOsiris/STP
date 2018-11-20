/**
 * (c) 2018 Adrian Challinor, Osiris Consultsants Ltd
 *     All Rights reserved 
 *  Created: 19 Nov 2018
 */
package uk.co.osiris.stp.test;

import uk.co.osiris.stp.Grammar;
import uk.co.osiris.stp.NoMoreTokenException;
import uk.co.osiris.stp.Parser;
import uk.co.osiris.stp.Scanner;

/**
 * @author adrian
 *
 */
public class InteractiveTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start");
		Grammar STP = Seshat.seshat();
		CB cb = new CB(); 
		Parser p = new Parser(STP, cb);
		Scanner sc = new Scanner();

		try {
			 p.parse(sc);
		} catch (NoMoreTokenException e) {
		}
	}

}


