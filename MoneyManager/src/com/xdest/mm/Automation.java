package com.xdest.mm;

import java.io.Serializable;

/**
 * Automation interface to automate money transfers.
 * @author xDest
 * @see Depositable
 * @see Withdrawlable
 *
 */
public interface Automation extends Serializable  {
	
	/**
	 * Get the destination account for this automation
	 * @return The destination as a {@link Depositable}
	 * @see Depositable
	 * @see Withdrawlable
	 */
	Depositable getDestination();
	
	/**
	 * Get the source of money for this automation
	 * @return The source as a {@link Withdrawlable}
	 * @see Depositable
	 * @see Withdrawlable
	 */
	Withdrawlable getSource();
	
	
	/**
	 * Attempts to perform this automations task.
	 * @return true if successful, false if failed.
	 */
	boolean doTask();

	/**
	 * Get the amount of money used in a single automation task.
	 * @return The amount of money used as a double
	 */
	double getAutomationAmount();
	
	/**
	 * Set the amount of money to use in an automation task.
	 * @param d The amount to use per automation task as a double.
	 */
	void setAutomationAmount(double d);
	
}
