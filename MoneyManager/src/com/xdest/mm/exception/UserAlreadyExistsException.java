package com.xdest.mm.exception;


/**
 * An exception for when a {@link com.xdest.mm.MMUser MMUser} is created but already exists
 * @author xDest
 *
 */
public class UserAlreadyExistsException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2626686181662987635L;
	private String userName;
	
	/**
	 * Create a new exception for the offending username
	 * @param userName The offending name
	 */
	public UserAlreadyExistsException(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Get the offending name
	 * @return The name
	 */
	public String getOffendingName() {
		return this.userName;
	}
}
