package uk.co.osiris.stp;

/**
 * Match any single character 
 * 
 * @author adrian
 *
 */
public class Character extends Transition {
	public Character() {
		super();
		name = "<CHARACTER>"; 
	}
	
	public Token match(Scanner sc) throws NoMoreTokenException {
		Token t = null;
		String s = sc.peek();
		if (s.length() == 1) {
			char c = s.charAt(0);
			t = new Token(c, sc.next());
			t.setType(TokenType.CHARACTER);
		}
		return t;
	}
}