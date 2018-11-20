package uk.co.osiris.stp;

public class Lambda  extends Transition {
	
	public Lambda() { 
		super();
		name = "<LAMBDA>"; 
	}

	@Override
	public Token match(Scanner sc)  {
		return new Token();
	}

}
