/**
 * (c) 2018 Adrian Challinor, Osiris Consultsants Ltd
 *     All Rights reserved 
 *  Created: 15 Nov 2018
 */
package uk.co.osiris.stp;

import java.io.File;

import org.apache.commons.text.StringSubstitutor;

/**
 * @author adrian
 *
 */
public class FilePath extends Transition {
	public FilePath() {
		super();
		name = "<FILEPATH>"; 
	}
	
	/**
	 * Match a file path. This will expand a leading ~ and 
	 * any shell variables. 
	 * @throws NoMoreTokenException 
	 * 
	 * @see uk.co.osiris.stp.Transition#match(uk.co.osiris.stp.Scanner)
	 */
	
	@Override
	public Token match(Scanner sc) throws NoMoreTokenException {
		Token t = null;
		String path = sc.peek();
		
		// Replace tilde with users home
		if (path.startsWith("~" + File.separator)) {
		    path = System.getProperty("user.home") + path.substring(1);
		}
		StringSubstitutor sub = new StringSubstitutor(System.getenv());
		path = sub.replace(path);

		File file = new File(path);
		if (file.exists())
			t = new Token(file, sc.next());	
		return t;
	}
}
