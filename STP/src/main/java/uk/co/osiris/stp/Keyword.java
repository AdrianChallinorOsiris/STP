package uk.co.osiris.stp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Keyword extends Transition {
	private String keyword;
	private Pattern pattern;
	private static final int MINCHARS = 3;

	public Keyword() {
		super();
		name = "<KEYWORD>";
	}

	public Keyword(String k) {
		super();
		name = k;
		keyword = k.toUpperCase();
		StringBuffer p1 = new StringBuffer();
		StringBuffer p2 = new StringBuffer();
		char c[] = k.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (i >= MINCHARS) {
				p1.append('(');
				p2.append(")?");
			}
			p1.append(k.charAt(i));
		}
		String p = p1.toString() + p2.toString();
		pattern = Pattern.compile(p, Pattern.CASE_INSENSITIVE);
	}

	public Token match(Scanner sc) throws NoMoreTokenException {
		Token t = null;
		Matcher m = pattern.matcher(sc.peek());
		if (m.matches()) {
			t = new Token(keyword, sc.next());
		}
		return t;
	}

	public Transition auto() {
		setSuccess(new State(keyword));
		return this;
	}

}