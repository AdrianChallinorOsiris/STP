package uk.co.osiris.stp;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Date extends Transition {
	private final static String[] format = {
			"d-MMM-yyyy", 
			"d-M-yyyy", 
			"d-MM-yyyy", 
			"d/MM/yyyy", 
			"d/M/yyyy"};
	
	public Date() {
		super();
		name = "<Date DD-MMM-YYYY>";
	}

	@Override
	public Token match(Scanner sc) throws NoMoreTokenException  {
		Token t = null;
		String s = sc.peek(5);
		for (int i=0; i<format.length; i++) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format[i]);
			try {
				LocalDate ld = LocalDate.parse(s, formatter);
				t = new Token(ld, s);
				sc.next(5);
				break;
			}
			catch(DateTimeParseException e) {}
		}
		return t;
	}
}
