package com.xdest.mm.impl;

import java.util.Calendar;
import java.util.Date;

import com.xdest.mm.Account;
import com.xdest.mm.Automation;
import com.xdest.mm.exception.InsufficientFundsException;

/**
 * Default implementation for an Automation using Accounts as sources and destinations
 * @author xDest
 *
 */
public class DefaultAutomation implements Automation {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7087021731230233898L;
	
	private final Account source, destination;
	private long intervalInMinutes;
	private double amount;
	private Date nextTransferDate;
	
	/**
	 * Create an automation with the specified parameters
	 * @param source The source account
	 * @param destination The destination account
	 * @param interval The interval in minutes between transfers
	 * @param amount The amount to transfer
	 */
	public DefaultAutomation(Account source, Account destination, long interval, double amount) {
		this.source = source;
		this.destination = destination;
		this.amount = amount;
		nextTransferDate = Calendar.getInstance().getTime();
		this.intervalInMinutes = interval;
		this.doScheduleNext();
	}
	
	
	/**
	 * @return An account used as the destination for this automation
	 */
	@Override
	public Account getDestination() {
		return destination;
	}

	/**
	 * @return An account used as the source for this automation
	 */
	@Override
	public Account getSource() {
		return source;
	}

	@Override
	public boolean doTask() {
		double withdrawn = 0;
		try {
			withdrawn = source.withdraw(amount);
		} catch (InsufficientFundsException e) {
			e.printStackTrace();
			System.out.println("Unable to complete automation task");
			return false;
		}
		double leftover = destination.deposit(withdrawn);
		source.deposit(leftover);
		doScheduleNext();
		return true;
	}

	private void doScheduleNext() {
		Date newDate = this.nextTransferDate;
		newDate.setTime(newDate.getTime() + (this.intervalInMinutes * 60 * 1000));
		this.nextTransferDate = newDate;
	}
	
	@Override
	public double getAutomationAmount() {
		return this.amount;
	}

	@Override
	public void setAutomationAmount(double d) {
		this.amount = d;
	}

	@Override
	public Date getNextScheduledAutomation() {
		return nextTransferDate;
	}

	@Override
	public boolean setNextAutomationDate(Date d) {
		this.nextTransferDate = d;
		return true;
	}

	@Override
	public boolean setInterval(long l) {
		this.intervalInMinutes = l;
		return true;
	}

	@Override
	public boolean ready() {
		return Calendar.getInstance().after(this.getNextScheduledAutomation());
	}
	
	@Override
	public String toString() {
		return "[Automation: (" + this.getSource() + ") ($" + this.getAutomationAmount() + ") --> (" + this.getDestination() + ") every " + this.getInterval() + " minutes]";
	}


	@Override
	public long getInterval() {
		return this.intervalInMinutes;
	}

}
