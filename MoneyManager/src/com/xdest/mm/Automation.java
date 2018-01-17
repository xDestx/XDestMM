package com.xdest.mm;

import java.io.Serializable;
import java.util.Date;

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
	
	/**
	 * Get the next scheduled transfer date.
	 * @return The Date of the next transfer
	 */
	Date getNextScheduledAutomation();
	
	/**
	 * Sets the date for the next transfer.
	 * @param d The date
	 * @return true, if the change was successful
	 */
	boolean setNextAutomationDate(Date d);
	
	/**
	 * Set the interval of minutes between each transfer
	 * @param l The time between each transfer, in minutes
	 * @return true, if the change was a success. Changing this will not update the value returned by {@link #getNextScheduledAutomation()}.
	 */
	boolean setInterval(long l);
	
	/**
	 * Get the interval in minutes
	 * @return The interval in minutes
	 */
	long getInterval();
	
	/**
	 * Check if this automation is at or past it's enact date.
	 * @return true, if the automation should occur.
	 */
	boolean ready();
}
