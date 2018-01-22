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
	ScheduledAutomation createScheduledAutomation(Withdrawlable source, Depositable destination, long interval, double amount);
	
	/**
	 * Attempt to create an automation from the source to the destination
	 * @param source The source 
	 * @param destination The destination
	 * @param amount The amount to transfer per transfer
	 * @return The automation instance
	 */
	Automation createAutomation(Withdrawlable source, Depositable destination, double amount);
	
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
	
	/**
	 * Delete an account
	 * @param a The account to delete
	 * @return The account deleted
	 */
	Account deleteAccount(Account a);
	
	/**
	 * Delete an account by index. Implementation may vary
	 * @param index The index of the account.
	 * @return The account deleted
	 */
	Account deleteAccount(int index);
	
	
	/**
	 * Rename an account
	 * @param a The account to rename
	 * @param newName The new account name
	 */
	void renameAccount(Account a, String newName);
	
	
	/**
	 * Delete an expense
	 * @param e The expense to delete
	 * @return The expense deleted
	 */
	Expense deleteExpense(Expense e);
	
	/**
	 * Delete an expense by index. Implementation can vary
	 * @param index The index
	 * @return The expense deleted
	 */
	Expense deleteExpense(int index);
	
	/**
	 * Change the name of an expense
	 * @param e The expense to rename
	 * @param newExpenseName The new expense name
	 */
	void renameExpense(Expense e, String newExpenseName);
	
	/**
	 * Delete an automation
	 * @param e The automation to delete
	 * @return The automation deleted
	 */
	Automation deleteAutomation(Automation e);
	
	/**
	 * Delete an automation by index. Implementation can vary
	 * @param index The index
	 * @return The automation deleted
	 */
	Automation deleteAutomation(int index);
	
	/**
	 * Change the name of an automation
	 * @param e The automation to rename
	 * @param newAutomationName The new automation name
	 */
	void renameAutomation(Automation e, String newAutomationName);
	
	
}
