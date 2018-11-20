package uk.co.osiris.stp;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Time extends Transition {
	private final static String format = "HH:mm";
	

	public Time() {
		super();
		name = "<TIME (HH:MM)>";
	}

	@Override
	public Token match(Scanner sc) throws NoMoreTokenException {
		Token t = null;
		String s = sc.peek(3);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		try {
			LocalTime lt = LocalTime.parse(s, formatter);
			t = new Token(lt, s);
			sc.next();
		}
		catch(DateTimeParseException e) {}
		
		return t;
	}
}
