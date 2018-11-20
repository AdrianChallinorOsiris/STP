package uk.co.osiris.stp;

/**
 * Match a symbol 
 * 
 * @author adrian
 *
 */
public class Symbol extends Transition {
	private char symbol;

	public Symbol() {
		super();
		name = "<SYMBOL>";
	}
	
	public Symbol(char s) {
		super();
		symbol = s;
	}
	
	public Token match(Scanner sc) throws NoMoreTokenException {
		Token t = null;
		String s = sc.peek();
		if (s.length() == 1) {
			if (s.getBytes()[0] == symbol) {
				t = new Token(symbol, sc.next());
				t.setType(TokenType.SYMBOL);
			}
		}
		return t;
	}
}