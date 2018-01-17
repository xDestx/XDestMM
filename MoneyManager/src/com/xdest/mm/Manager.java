package com.xdest.mm;

public interface Manager {

	/**
	 * List accounts
	 */
	void listAccounts();
	
	/**
	 * List expenses
	 */
	void listExpenses();
	
	/**
	 * List automations
	 */
	void listAutomations();
	
	/**
	 * Attempt to create an automation from the source to the destination
	 * @param source The source account
	 * @param destination The desination account
	 * @param The time in M I N U T E S between transfers
	 * @param The amount to transfer 
	 * @return The automation, or null
	 */
	Automation createAutomation(Withdrawlable source, Depositable destination, long interval, double amount);
	
	/**
	 * Create a local account not linked to anywhere but this machine
	 * @param accountName The acount name
	 * @return The account created
	 */
	Account createLocalAccount(String accountName);
	
	/**
	 * Add an account to the user in this Manager instance
	 * @param a The account to add
	 * @return True, if it was added correctly
	 */
	boolean addAccountToUser(Account a);
}
