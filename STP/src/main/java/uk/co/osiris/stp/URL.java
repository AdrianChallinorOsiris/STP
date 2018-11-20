/**
 * (c) 2018 Adrian Challinor, Osiris Consultsants Ltd
 *     All Rights reserved 
 *  Created: 15 Nov 2018
 */
package uk.co.osiris.stp;

import java.net.MalformedURLException;

/**
 * @author adrian
 *
 */
public class URL extends Transition {
	public URL() {
		super();
		name = "<URL>"; 
	}

	/* (non-Javadoc)
	 * @see uk.co.osiris.stp.Transition#match(uk.co.osiris.stp.Scanner)
	 */
	@Override
	public Token match(Scanner sc) throws NoMoreTokenException {
		Token t = null;
		
		try {
			java.net.URL url = new java.net.URL(sc.peek());
			t = new Token(url, sc.next());
		} catch (MalformedURLException e) {
		} 
		
		return t;
	}

}
