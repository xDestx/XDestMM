package com.xdest.mm.impl;

import com.xdest.mm.Depositable;
import com.xdest.mm.Withdrawlable;
import com.xdest.mm.exception.InsufficientFundsException;

public class SelfInitiatedAutomation extends BasicAutomation {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3221421574562527618L;

	/**
	 * Create a self initiated automation. Similar to normal automations except this requires an a call to go off.
	 * @param source The source for this automation
	 * @param destination The destination for this automation
	 * @param amount The amount used per execution
	 */
	public SelfInitiatedAutomation(Withdrawlable source, Depositable destination, double amount) {
		super(source, destination, amount);
	}

	@Override
	public boolean doTask() {
		try {
			double amt = source.withdraw(getAutomationAmount());
			destination.deposit(amt);
			return true;
		} catch (InsufficientFundsException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	@Override
	public String toString() {
		return "[Automation (Non-Scheduled): (" + this.getSource() + ") ($" + this.getAutomationAmount() + ") --> (" + this.getDestination() + ")]";
	}

}
