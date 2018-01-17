package com.xdest.mm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * MM User class. Constructor is hidden to prevent users without accounts. Used to check funds / set goals / projects / use the entire management system.
 * @author xDest
 *
 */
public class MMUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7856785047936908648L;
	private Set<Account> userAccounts;
	private String email;
	private List<Expense> expenses;
	private List<Automation> automations;
	
	/**
	 * Unused constructor
	 */
	private MMUser() {
		userAccounts = null;
		expenses = null;
		email = null;
	}
	
	/**
	 * Default constructor
	 * @param a The accounts for this user
	 */
	private MMUser(Account...a) {
		this.userAccounts = new HashSet<Account>();
		for(Account acc : a) {
			this.userAccounts.add(acc);
		}
		expenses = new ArrayList<Expense>();
		automations = new ArrayList<Automation>();
	}
	
	/**
	 * Default constructor
	 * @param a The account for this user
	 */
	private MMUser(Account a) {
		this.userAccounts = new HashSet<Account>();
		this.userAccounts.add(a);
		expenses = new ArrayList<Expense>();
		automations = new ArrayList<Automation>();
	}
	
	/**
	 * Add an expense to this account
	 * @param e The expense to add
	 */
	public void addExpense(Expense e) {
		expenses.add(e);
	}
	
	/**
	 * Remove an expense from this account
	 * @param e The expense to remove
	 * @return The Expense removed. Returns null if the expense was never present.
	 */
	public Expense removeExpense(Expense e) {
		if(expenses.remove(e)) {
			return e;
		}
		return null;
	}
	
	/**
	 * Remove an expense by index from this account
	 * @param i The index of the expense
	 * @return The expense removed, or null if the index was out of bounds.
	 */
	public Expense removeExpense(int i) {
		try {
			return expenses.remove(i);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	/**
	 * Set the email used for notifications
	 * @param email The email to send to
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Get the email used for notifications
	 * @return The email as a String
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * Add an automation to this user
	 * @param a The automation
	 */
	public void addAutomation(Automation a) {
		this.automations.add(a);
	}
	
	/**
	 * Remove an automation by reference
	 * @param a The automation to remove
	 * @return The automation removed
	 */
	public Automation removeAutomation(Automation a) {
		this.removeAutomation(a);
		return a;
	}
	
	/**
	 * Remove an automation by index. If the index is out of bounds, null is returned.
	 * @param index The automation index
	 * @return The automation, or null
	 */
	public Automation removeAutomation(int index) {
		try {
			return this.automations.remove(index);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	/**
	 * Run any automations which can be run
	 */
	public void checkAutomations() {
		for(Automation a : this.automations) {
			if(a.ready()) {
				a.doTask();
			}
		}
	}
	
	/**
	 * Get the accounts used in this user
	 * @return An array of accounts
	 */
	public Set<Account> getAccounts() {
		return this.userAccounts;
	}
	
	/**
	 * Add an account to this user. Will not add twice.
	 * @param a The account to add
	 * @return true, if add is successful
	 */
	public boolean addAccount(Account a) {
		return this.userAccounts.add(a);
	}
	
	/**
	 * Get expenses for this user
	 * @return The expense list
	 */
	public List<Expense> getExpenses() {
		return this.expenses;
	}
	
	/**
	 * Get automations for this user
	 * @return The automation list
	 */
	public List<Automation> getAutomations() {
		return this.automations;
	}

}
