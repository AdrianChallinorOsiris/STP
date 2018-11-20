package uk.co.osiris.stp;
import java.time.LocalDateTime;

public class DateTime extends Transition {
	
	public DateTime() {
		super();
		name = "<DATETIME dd-MMM-yyyy HH:MM ";
	}
	
	@Override
	public Token match(Scanner sc) throws NoMoreTokenException {
		Date dateTrans = new Date();
		Time timeTrans = new Time();
		Token t = null;
		
		Token dt = dateTrans.match(sc);
		if (dt != null) {
			Token tt = timeTrans.match(sc);
			if (tt != null) {
				LocalDateTime ldt = dt.getLdval().atTime(tt.getLtval());
				String s = dt.getSval() + " " + tt.getSval();
				t = new Token(ldt, s);
			}
			else 
				t = dt;
		}
		return t;
	}
	


}
