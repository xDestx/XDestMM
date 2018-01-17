package com.xdest.mm.impl;

import com.xdest.mm.Account;
import com.xdest.mm.Automation;
import com.xdest.mm.Depositable;
import com.xdest.mm.Expense;
import com.xdest.mm.MMUser;
import com.xdest.mm.Manager;
import com.xdest.mm.Withdrawlable;

/**
 * An implementation of the Manager class using System.out
 * @author xDest
 *
 */
public class TextManager implements Manager {

	private final MMUser user;
	
	public TextManager(MMUser u) {
		user = u;
	}

	@Override
	public void listAccounts() {
		System.out.println("==== Accounts ====");
		for(Account a : user.getAccounts()) {
			System.out.println(a);
		}
		System.out.println("==== Accounts ====");
	}

	@Override
	public void listExpenses() {
		System.out.println("==== Expenses ====");
		for(Expense e : user.getExpenses()) {
			System.out.println(e);
		}
		System.out.println("==== Expenses ====");
	}

	@Override
	public void listAutomations() {
		System.out.println("==== Automations ====");
		for(Automation a : user.getAutomations()) {
			System.out.println(a);
		}
		System.out.println("==== Automations ====");
	}

	/**
	 * @see #createAutomation(Account, Account, long, double)
	 */
	@Override @Deprecated
	public Automation createAutomation(Withdrawlable source, Depositable destination, long interval, double amount) {
		return null;
	}

	/**
	 * Simplified version of {@link #createAutomation(Withdrawlable, Depositable, long, double)}
	 * @param source The source account
	 * @param destination The destination account
	 * @param interval The interval in minutes between auto transfers
	 * @param amount The amount per automation
	 * @return The automation
	 */
	public Automation createAutomation(Account source, Account destination, long interval, double amount) {
		DefaultAutomation da = new DefaultAutomation(source,destination,interval,amount);
		this.user.addAutomation(da);
		return da;
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
