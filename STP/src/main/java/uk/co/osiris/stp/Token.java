package uk.co.osiris.stp;

import java.io.File;
import java.net.InetAddress;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;


@Data
public class Token {
	private TokenType type;
	private String sval;
	private double dval;
	private int ival;
	private char cval;
	private LocalDate ldval;
	private LocalTime ltval;
	private LocalDateTime ldtval;
	private String keyword;
	private InetAddress ip;
	private URL url;
	private File file;
	private boolean yes;
	
	public Token() { 
		type = TokenType.LAMBDA;
	}
	
	public Token(LocalDateTime ldt, String s) { 
		type = TokenType.DATETIME;
		ldtval = ldt;
		ldval = ldt.toLocalDate();
		ltval = ldt.toLocalTime();
		sval = s;
	}

	public Token(LocalDate ld,  String s) { 
		type = TokenType.DATE;
		ldval = ld;
		ldtval = LocalDateTime.of(ld, LocalTime.MIDNIGHT);
		sval = s ;
	}

	public Token(LocalTime ldt, String s) { 
		type = TokenType.TIME;
		ltval = ldt;
		sval = s;
	}

	public Token(char c, String s) {
		type = TokenType.CHARACTER;
		cval = c;
		sval = s;
	}
	
	public Token(int i, String s) {
		type = TokenType.INTEGER;
		ival = i;
		dval = i;
		sval = s;
	}
	
	public Token(String s) { 
		type = TokenType.STRING;
		sval = s;
	}
	
	public Token(double d, String s) {
		type = TokenType.DOUBLE;
		dval = d ;
		ival = (int)d;
		sval = s;	
	}
		
	public Token(String keyword, String sval) {
		type = TokenType.KEYWORD;
		this.keyword = keyword;
		this.sval = sval; 
	}

	/**
	 * @param ip
	 * @param s
	 */
	public Token(InetAddress ip, String s) {
		type = TokenType.IP;
		this.ip = ip;
		sval = s;
	}

	/**
	 * @param url
	 * @param s
	 */
	public Token(URL url, String s) {
		type = TokenType.URL;
		this.url = url;
		sval = s;
	}

	/**
	 * @param file
	 * @param next
	 */
	public Token(File file, String s) {
		type = TokenType.FILE;
		this.file = file; 
		sval = s;
	}

	/**
	 * @param b
	 * @param next
	 */
	public Token(boolean b, String s) {
		type = TokenType.YESNO;
		yes = b;
		sval = s;
	}

	public String toString() { 
		String v ="";
		switch (type) { 
		case STRING:  
			v = "SVAL="+sval;
			break;
		case CHARACTER: 
			v = "CVAL="+cval;
			break;
		case INTEGER:
			v = "IVAL="+ival;
			break;
		case DOUBLE: 
			v = "DVAL="+dval;
			break;
		case DATE: 
			v = "DATE="+ldval;
		case TIME: 
			v = "DATE="+ltval;
		default: ;
		}
		String s = "Type=" + type + " " + v;

		return s ;
	}

}
