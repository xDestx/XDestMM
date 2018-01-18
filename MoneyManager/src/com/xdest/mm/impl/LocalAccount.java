package com.xdest.mm.impl;

import java.text.NumberFormat;
import java.util.Locale;

import com.xdest.mm.Account;
import com.xdest.mm.exception.InsufficientFundsException;

/**
 * An account tied to nothing but the {@link com.xdest.mm.MMUser MMUser}
 * @author xDest
 *
 */
public class LocalAccount implements Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9155192774205968483L;
	
	
	private String accountName, ownerName;

	
	private double balance;
	
	/**
	 * Create an account with the given name	
	 * @param ownerName The name of the owner of this account
	 * @param accountName The name of this account
	 */
	public LocalAccount(String ownerName, String accountName) {
		this.accountName = accountName;
		this.ownerName = ownerName;
		balance = 0;
	}

	@Override
	public double deposit(double d) {
		balance+=d;
		return 0;
	}

	@Override
	public double withdraw(double amt) throws InsufficientFundsException {
		if(balance-amt < 0) {
			throw new InsufficientFundsException(this, balance-amt, balance);
		} else {
			balance-=amt;
			return amt;
		}
	}

	@Override
	public String getAccountOwner() {
		return ownerName;
	}

	/**
	 * The identifier used to load or save this account.
	 * @return The identifier as a String
	 */
	public String getLocalAccountIdentifier() {
		return this.getAccountOwner() + "." + this.getAccountName();
	}
	
	/**
	 * Unused for Local Accounts.
	 * @see #getAccountName()
	 * @see #getLocalAccountIdentifier
	 */
	@Override @Deprecated
	public long getRoutingNumber() {
		// TODO Auto-generated method stub
		return -1;
	}

	/**
	 * Unused for Local Accounts.
	 * @see #getAccountName();
	 * @see #getLocalAccountIdentifier();
	 */
	@Override @Deprecated
	public long getAccountNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Unused for local accounts
	 * @see #getLocalAccountIdentifier()
	 */
	@Override @Deprecated
	public String getRoutingNumberAsString() {
		return "";
	}

	/**
	 * Unused for local accounts
	 * @see #getLocalAccountIdentifier();
	 */
	@Override @Deprecated
	public String getAccountNumberAsString() {
		return "";
	}

	@Override
	public double getBalance() {
		return balance;
	}

	@Override
	public String getFormattedBalance() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
		return (balance < 0 ? "-":"") + nf.format(Math.abs(balance));
	}
	
	@Override
	public String toString() {
		return "[Account: {" + this.getLocalAccountIdentifier() + "} " + getFormattedBalance() + "]";
	}

	@Override
	public String getAccountName() {
		return accountName;
	}

	
}
