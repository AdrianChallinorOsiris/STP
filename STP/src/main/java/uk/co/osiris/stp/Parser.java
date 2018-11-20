/**
 * 
 */
package uk.co.osiris.stp;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author adrian
 *
 */

@Data
@Log4j2
public class Parser {
	/**
	 * log for this class
	 */
	private Grammar grammar;
	private Callback callback = null;
	private State currentState;

	/**
	 * Constructors
	 * 
	 * @param t
	 */

	public Parser(Grammar t, Callback cb) {
		grammar = t;
		callback = cb;
	}

	public void print() {
		try {
			grammar.print(new PrintWriter(System.out));
		} catch (IOException e) {
			log.error("Error during grammar print", e);
		}
	}

	/*
	 * Parse input from a scanner. 
	 * 
	 */
	public Result parse(Scanner sc) throws NoMoreTokenException {
		currentState = grammar.getInitialState();
		log.debug("parse - Parser Starting State=" + currentState);

		while (true) {
			log.debug("In State: " + currentState.getName());
			sc.setStateName(currentState.getName());

			// Consume help symbols 
			while (sc.peek().equals("?")) {
				help();
				sc.next();
			}
			
			// Match a transition
			Match match = currentState.match(sc);
			if (match != null) {
				Token token = match.getToken();
				Transition t = match.getTrans();

				log.debug("Matched {}", t.toString());
				callback(t.getCbMethod(), token);
				if (t.isComplete()) {
					log.debug("Complete: " + t.toString());
					return Result.COMPLETE;
				} 
				// Get the next state to go to
				currentState = t.getSuccessState();

				// If we don't have a next state, always reset to the main command look
				// This means the grammar does not be to littered with setting the success
				// to the main look at the end of a command
				if (currentState == null) {
					currentState = grammar.getInitialState();
				}
			} else {
				// What to do if we don't match anything.
				log.error("Unable to parse input <{}> - ", sc.next());
				return Result.FAILURE;
			}
		}
	}

	/**
	 * This is a redundant call, unless the grammar has just been initialized
	 * 
	 */
	public void help() {
		currentState.help();
	}

	/**
	 * Internal method to do the call back. 
	 * 
	 * @param cbmethod
	 * @param token
	 * @return
	 */
	private void callback(String cbmethod, Token token) {
		if (cbmethod != null) {
			log.debug("Callback by method: {} ", cbmethod);
			try {
				Method cbi = callback.getClass().getMethod(cbmethod, Token.class);
				cbi.invoke(callback, token);
			} catch (SecurityException e1) {
				log.error("Security exception calling: " + callback.getClass().getName() + "." + cbmethod);
			} catch (NoSuchMethodException e1) {
				log.warn("Method not found: " + callback.getClass().getName() + "." + cbmethod);
			} catch (IllegalArgumentException e) {
				log.error("Incorrect arguments calling: " + callback.getClass().getName() + "." + cbmethod);
			} catch (IllegalAccessException e) {
				log.error("Illegal Access calling: " + callback.getClass().getName() + "." + cbmethod);
			} catch (InvocationTargetException e) {
				log.error("Invocation Exception calling: " + callback.getClass().getName() + "." + cbmethod, e);
			}
		}
	}

	/**
	 * Check the grammar and the call back mapping
	 * 
	 */
	public boolean check() {
		boolean OK = true;

		OK = grammar.check();

		Method[] methods = callback.getClass().getDeclaredMethods();

		ArrayList<String> mlist = new ArrayList<String>();
		for (Method m : methods) {
			int i = m.getParameterCount();
			Class<?>[] p = m.getParameterTypes();

			boolean add = true;

			if (i != 1)
				add = false;
			else if (p[0] != Token.class)
				add = false;

			if (add)
				mlist.add(m.getName());
		}

		for (State s : grammar.getStates().values()) {
			for (Transition t : s.getTrans()) {
				String cbmethod = t.getCbMethod();
				if (cbmethod != null) {
					try {
						@SuppressWarnings("unused")
						Method cbi = callback.getClass().getMethod(cbmethod, Token.class);
						mlist.remove(cbmethod);
					} catch (NoSuchMethodException | SecurityException e) {
						log.warn("Callback Mathod Missing: " + callback.getClass().getName() + "." + cbmethod);
						OK = false;
					}
				}
			}
		}

		for (String s : mlist) {
			log.warn("Callback Method redundant: " + callback.getClass().getName() + "." + s);
		}
		return OK;
	}
}
