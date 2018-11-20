package uk.co.osiris.stp;

public class IntNumber extends Transition {
	public IntNumber() { 
		super();
		name = "<Integer>";
	}

	@Override
	public Token match(Scanner sc) throws NoMoreTokenException {
		Token t = null;
		int l = 1;
		String s = sc.peek();
		
		// If the first character is - or + extend the scan to cover this
		if (s.startsWith("-") || s.startsWith("+")) {
			l++;
			s = sc.peek(l);		
		}
		
		// If the last char is an 'E' then we have an exponent where the matissa  is 
		// missing the signed exponent. Add the next two tokens
		String e = s.substring(s.length()-1, s.length());
		if (e.equalsIgnoreCase("E")) {
			l += 2;
			s = sc.peek(l);
		}

		try {
			int i = Integer.parseInt(s);
			t = new Token(i, s);
			sc.next(l);
		}
		catch(NumberFormatException ex)
		{}
		return t;
	}
	
}
