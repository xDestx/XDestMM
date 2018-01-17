package com.xdest.mm.impl;

import com.xdest.mm.Account;
import com.xdest.mm.exception.InsufficientFundsException;

/**
 * An account tied to nothing but a file on this machine
 * @author xDest
 *
 */
public class LocalAccount implements Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9155192774205968483L;
	
	private String accountName;
	
	
	
	//TODO: This entire implementation
	
	
	/**
	 * Create an account with the given name	
	 * @param name
	 */
	public LocalAccount(String name) {
		this.accountName = name;
	}

	@Override
	public double deposit(double d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double withdraw(double amt) throws InsufficientFundsException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getAccountOwner() {
		return accountName;
	}

	@Override
	public long getRoutingNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getAccountNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getRoutingNumberAsString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAccountNumberAsString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getBalance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getFormattedBalance() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return null;
	}

}
