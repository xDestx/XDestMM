package com.xdest.mm.impl;

import com.xdest.mm.Account;
import com.xdest.mm.exception.InsufficientFundsException;

public class SelfAccountAutomation extends SelfInitiatedAutomation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7019144681843122443L;

	public SelfAccountAutomation(Account source, Account destination, double amount) {
		super(source, destination, amount);
	}

	
	@Override
	public Account getSource() {
		return (Account)source;
	}
	
	@Override
	public Account getDestination() {
		return (Account)destination;
	}
	
	@Override
	public boolean doTask() {
		try {
			double drawn = getSource().withdraw(getAutomationAmount());
			double overflow = getDestination().deposit(drawn);
			getSource().deposit(overflow);
			return true;
		} catch (InsufficientFundsException e) {
			System.out.println("Insufficient funds to follow through with transfer!");
			e.printStackTrace();
		}
		return false;
	}

}
