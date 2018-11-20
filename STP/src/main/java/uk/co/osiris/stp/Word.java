package uk.co.osiris.stp;

public class Word extends Transition{
	public Word() {
		super();
		name = "<Word>";
	}

	@Override
	public Token match(Scanner sc) throws NoMoreTokenException {
		Token t = null;
		String s = sc.peek();
		if (s.matches("[a-zA-Z0-9]+")) {
			t = new Token(sc.next());
		}
		return t;
	}
}
