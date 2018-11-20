package uk.co.osiris.stp;

public class Any extends Transition {
	
	public Any() { 
		super();
		name = "<ANY>"; 
	}

	@Override
	public Token match(Scanner sc) throws NoMoreTokenException  {
		Token t =  new Token(sc.next());
		t.setType(TokenType.ANY);
		return t;
	}


}
