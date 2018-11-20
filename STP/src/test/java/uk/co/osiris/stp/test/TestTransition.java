/**
 * (c) 2018 Adrian Challinor, Osiris Consultsants Ltd
 *     All Rights reserved 
 *  Created: 15 Nov 2018
 */
package uk.co.osiris.stp.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Test;

import uk.co.osiris.stp.*;
import uk.co.osiris.stp.Character;

/**
 * 
 * @author adrian
 *
 */
public class TestTransition {

	@Test
	public void testAny() throws NoMoreTokenException {
		Scanner sc = new Scanner("Any colour you like");
		assertEquals(4, sc.getTokens().size());

		Any any = new Any();

		Token t = any.match(sc);
		assertNotNull(t);
		assertEquals("Any", t.getSval());
	}

	@Test
	public void testYesNow() throws NoMoreTokenException {
		Scanner sc = new Scanner("True no yes");
		assertEquals(3, sc.getTokens().size());

		YesNo yn = new YesNo();

		Token t = yn.match(sc);
		assertNotNull(t);
		assertTrue(t.isYes());
		
		t = yn.match(sc);
		assertNotNull(t);
		assertFalse(t.isYes());
		
		t = yn.match(sc);
		assertNotNull(t);
		assertTrue(t.isYes());
		
	}

	@Test
	public void testChar() throws NoMoreTokenException {
		Scanner sc = new Scanner("Any $colour you like");
		assertEquals(5, sc.getTokens().size());
		Character c = new Character();

		Token t = c.match(sc);
		assertNull(t);

		sc.next();
		t = c.match(sc);
		assertNotNull(t);
	}

	@Test
	public void testDate() throws NoMoreTokenException {
		Scanner sc = new Scanner("Date 13-Sep-1958 13/9/1958 13-9-1958 ");
		assertEquals(16, sc.getTokens().size());
		LocalDate ld = LocalDate.of(1958, 9, 13);

		Date d = new Date();

		Token t = d.match(sc);
		assertNull(t);
		sc.next();

		t = d.match(sc);
		assertEquals(ld, t.getLdval());

		t = d.match(sc);
		assertEquals(ld, t.getLdval());

		t = d.match(sc);
		assertEquals(ld, t.getLdval());
	}

	@Test
	public void testTime() throws NoMoreTokenException {
		Scanner sc = new Scanner("Date 10:58 ");
		assertEquals(4, sc.getTokens().size());
		LocalTime ld = LocalTime.of(10, 58);

		Time d = new Time();

		Token t = d.match(sc);
		assertNull(t);
		sc.next();

		t = d.match(sc);
		assertEquals(ld, t.getLtval());
	}

	@Test
	public void testDateTime() throws NoMoreTokenException {
		Scanner sc = new Scanner("Date 13-Sep-1958 10:58 ");
		assertEquals(9, sc.getTokens().size());
		LocalDateTime ld = LocalDateTime.of(1958, 9, 13, 10, 58);

		DateTime d = new DateTime();

		Token t = d.match(sc);
		assertNull(t);
		sc.next();

		t = d.match(sc);
		assertEquals(ld, t.getLdtval());
	}

	@SuppressWarnings("unused")
	private void dump(Scanner sc) {
		int i = 1;
		for (String s : sc.getTokens()) {
			System.out.println(String.format("%d: %s", i++, s));
		}
	}

	@Test
	public void testDouble() throws NoMoreTokenException {
		Scanner sc = new Scanner("Salary 146000.09 146,000.09  -146.00009E3  14600009E-2 ");
		double n = 146000.09;

		DoubleNumber d = new DoubleNumber();

		Token t = d.match(sc);
		assertNull(t);
		sc.next();

		t = d.match(sc);
		assertEquals(n, t.getDval(), 001);

		t = d.match(sc);
		assertEquals(n, t.getDval(), 001);

		t = d.match(sc);
		assertEquals(-n, t.getDval(), 001);

		t = d.match(sc);
		assertEquals(n, t.getDval(), 001);
	}

	@Test
	public void testIntger() throws NoMoreTokenException {
		Scanner sc = new Scanner("Salary 10.2 10 +10 -10 ");

		IntNumber i = new IntNumber();

		Token t = i.match(sc);
		assertNull(t);
		sc.next();

		t = i.match(sc);
		assertNull(t);
		sc.next();

		t = i.match(sc);
		assertEquals(10, t.getIval());

		t = i.match(sc);
		assertEquals(10, t.getIval());

		t = i.match(sc);
		assertEquals(-10, t.getIval());
	}

	@Test
	public void testFile() throws NoMoreTokenException {
		Scanner sc = new Scanner("file '${JAVA_HOME}/LICENSE' '~/.bashrc' ");

		FilePath f = new FilePath();
		Token t = f.match(sc);
		assertNull(t);
		sc.next();

		t = f.match(sc);
		assertNotNull(t);

		t = f.match(sc);
		assertNotNull(t);
	}

	@Test
	public void testIP() throws NoMoreTokenException {
		Scanner sc = new Scanner("IP 192.168.1.74 bbc.co.uk ");

		IP ip = new IP();
		Token t = ip.match(sc);
		assertNull(t);
		sc.next();

		t = ip.match(sc);
		assertNotNull(t);

		t = ip.match(sc);
		assertNotNull(t);
	}

	@Test
	public void testKeyword() throws NoMoreTokenException {
		Scanner sc = new Scanner("The qui BROWN fox");
		Keyword k1 = new Keyword("THE");
		Keyword k2 = new Keyword("quick");
		Keyword k3 = new Keyword("Brown");
		Keyword k4 = new Keyword("bat");
		Keyword k5 = new Keyword("fox");

		Token t1 = k1.match(sc);
		assertNotNull(t1);

		Token t2 = k2.match(sc);
		assertNotNull(t2);
		assertEquals("QUICK", t2.getKeyword());

		Token t3 = k3.match(sc);
		assertNotNull(t3);

		Token t4 = k4.match(sc);
		assertNull(t4);

		Token t5 = k5.match(sc);
		assertNotNull(t5);
	}

	@Test
	public void testLambda() throws NoMoreTokenException {
		Scanner sc = new Scanner("The qui BROWN fox");
		Lambda l = new Lambda();
		Keyword k = new Keyword("THE");

		Token t1 = l.match(sc);
		assertNotNull(t1);

		Token t2 = k.match(sc);
		assertNotNull(t2);
		assertEquals("THE", t2.getKeyword());
	}

	@Test
	public void testString() throws NoMoreTokenException {
		// String are matched with ANY
		Scanner sc = new Scanner("Name \"Adrian Challinor\" ");
		Any a = new Any();

		Token t1 = a.match(sc);
		assertEquals(4, t1.getSval().length());

		Token t2 = a.match(sc);
		assertEquals(16, t2.getSval().length());
		assertEquals("Adrian Challinor", t2.getSval());
	}

	@Test
	public void testSymbol() throws NoMoreTokenException {
		Scanner sc = new Scanner("any $%*");
		assertEquals(4, sc.getTokens().size());
		Symbol s1 = new Symbol('$');
		Symbol s2 = new Symbol('%');
		Symbol s3 = new Symbol('*');

		Token t = s1.match(sc);
		assertNull(t);
		sc.next();

		t = s1.match(sc);
		assertNotNull(t);

		t = s2.match(sc);
		assertNotNull(t);

		t = s3.match(sc);
		assertNotNull(t);
	}

	@Test
	public void testURL() throws NoMoreTokenException {
		Scanner sc = new Scanner("URL 'https://www.osiris.co.uk' ");
		URL u = new URL();

		Token t = u.match(sc);
		assertNull(t);
		sc.next();

		t = u.match(sc);
		assertNotNull(t);
	}

}
