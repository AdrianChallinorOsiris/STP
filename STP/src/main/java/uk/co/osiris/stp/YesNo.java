package uk.co.osiris.stp;

/**
 * Match any single character 
 * 
 * @author adrian
 *
 */
public class YesNo extends Transition {
	private final static String[] YES = {"Yes", "Y", "True"};
	private final static String[] NO = {"No", "N", "False"};
	
	public YesNo() {
		super();
		name = "<YES/NO>";
	}
	
	public Token match(Scanner sc) throws NoMoreTokenException {
		Token t = null;
		
		String s = sc.peek();
		for (int i = 0; i < YES.length; i++) {
			if (s.equalsIgnoreCase(YES[i])) {
				t = new Token(true, sc.next());
			}
		}
		if (t==null) {
			for (int i = 0; i < NO.length; i++) {
				if (s.equalsIgnoreCase(NO[i])) {
					t = new Token(false, sc.next());
				}
			}
		}
		
		return t;
	}
}