package com.xdest.mm.impl;

import com.xdest.mm.Account;
import com.xdest.mm.Automation;
import com.xdest.mm.Depositable;
import com.xdest.mm.Manager;
import com.xdest.mm.ScheduledAutomation;
import com.xdest.mm.Withdrawlable;

public class GUIManager implements Manager {

	@Override
	public void listAccounts() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listExpenses() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listAutomations() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Create an automation using an account as the source
	 * @param source The source account
	 * @param destination The destination depositable
	 * @param amount The amount to transfer
	 * @return The automation instance (Non Scheduled)
	 */
	public Automation createAutomation(Account source, Depositable destination, double amount) {
		
	}
	
	@Override @Deprecated
	public ScheduledAutomation createScheduledAutomation(Withdrawlable source, Depositable destination, long interval,
			double amount) {
		return null;
	}

	@Override @Deprecated
	public Automation createAutomation(Withdrawlable source, Depositable destination, double amount) {
		return null;
	}

	@Override
	public Account createLocalAccount(String accountName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addAccountToUser(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

}
