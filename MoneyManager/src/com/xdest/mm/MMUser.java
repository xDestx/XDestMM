package com.xdest.mm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.xdest.mm.exception.UserAlreadyExistsException;

/**
 * MM User class. Used to check funds / set goals / projects / use the entire management system.
 * @author xDest
 *
 */
public class MMUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7856785047936908648L;
	private static final String userPath = System.getProperty("user.home") + "/" + "XDMM" + "/" + "local" + "/" + "users";
	
	private Set<Account> userAccounts;
	private String email;
	private String name;
	private List<Expense> expenses;
	private List<Automation> automations;
	
	/**
	 * Default Constructor
	 * @param name The users name
	 */
	public MMUser(String name) throws UserAlreadyExistsException {
		this.name = name;
		this.email = null;
		userAccounts = new HashSet<Account>();
		expenses = new ArrayList<Expense>();
		automations = new ArrayList<Automation>();
		checkExists();
	}
	
	/**
	 * Re?constructor
	 * @param name The name of the user
	 * @param a The accounts for this user
	 */
	public MMUser(String name, Account...a) throws UserAlreadyExistsException {
		this.userAccounts = new HashSet<Account>();
		for(Account acc : a) {
			this.userAccounts.add(acc);
		}
		expenses = new ArrayList<Expense>();
		automations = new ArrayList<Automation>();
		this.name = name;
		checkExists();
	}
	
	/**
	 * Re?constructor
	 * @param name The name of the user
	 * @param a The account for this user
	 */
	public MMUser(String name, Account a) throws UserAlreadyExistsException {
		this.userAccounts = new HashSet<Account>();
		this.userAccounts.add(a);
		expenses = new ArrayList<Expense>();
		automations = new ArrayList<Automation>();
		this.name = name;
		checkExists();
	}
	
	public void checkExists() throws UserAlreadyExistsException {
		File userDirectory = new File(MMUser.userPath);
		if(!userDirectory.exists()) {
			userDirectory.mkdirs();
		}
		File userFilePath = new File(MMUser.userPath + '/' + getUserName() + ".mmu");
		if(userFilePath.exists()) throw new UserAlreadyExistsException(getUserName());
		try {
			userFilePath.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the name of this user
	 * @return The users name
	 */
	public String getUserName() {
		return this.name;
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
	public void checkScheduledAutomations() {
		for(Automation a : this.automations) {
			if(a instanceof ScheduledAutomation) {
				if(((ScheduledAutomation)a).ready()) {
					a.doTask();
				}
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
	
	/**
	 * Saves this user
	 */
	public void save() {
		File savePath = new File(MMUser.userPath + "/" + getUserName() + ".mmu");
		try {
			if(!savePath.exists()) savePath.createNewFile();
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savePath));
			oos.writeObject(this);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Load and return a user if it exists
	 * @param name The name of the user
	 * @return The user, if it exists
	 */
	public static MMUser loadUser(String name) {
		File savePath = new File(MMUser.userPath + "/" + name + ".mmu");
		try {
			ObjectInputStream oos = new ObjectInputStream(new FileInputStream(savePath));
			MMUser u = (MMUser)oos.readObject();
			oos.close();
			return u;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
