package org.starmod.net;

/**
 * Implementations of this class represent the data of the command to be sent.
 * All fields should be declared final to ensure thread safety.
 */
public interface Command {

	Header getHeader();

	/**
	 * Whether to handle the command asynchronously or not.
	 * @return true if asynchronous, false if synchronous
	 */
	boolean isAsync();

	/**
	 * All commands must include a toString for debugging
	 * @return a string
	 */
	String toString();

}
