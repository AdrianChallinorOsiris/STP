/**
 * (c) 2018 Adrian Challinor, Osiris Consultsants Ltd
 *     All Rights reserved 
 *  Created: 15 Nov 2018
 */
package uk.co.osiris.stp;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author adrian
 *
 */
public class IP extends Transition {
	public IP() {
		super();
		name = "<IP>"; 
	}

	/* (non-Javadoc)
	 * @see uk.co.osiris.stp.Transition#match(uk.co.osiris.stp.Scanner)
	 */
	@Override
	public Token match(Scanner sc) throws NoMoreTokenException {
		Token t = null;
		String s = sc.peek();
		InetAddress ip = null; 
		
		// Try for a named node
		try {
			ip = InetAddress.getByName(s);
		} catch (UnknownHostException e) {
		}
		
		// Else try for an address 
		if (ip == null) {
			try {
				ip = InetAddress.getByAddress(s.getBytes());
			} catch (UnknownHostException e) {
			}
		}
		
		// If we have an address ...
		if (ip != null) {
			t = new Token(ip, sc.next());
		}
		
		return t;
	}

}
