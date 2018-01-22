package com.xdest.mm;

import java.util.Date;

public interface ScheduledAutomation extends Automation {
	
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
