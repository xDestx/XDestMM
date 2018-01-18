package com.xdest.mm;

/**
 * Interface for using external accounts to check for balances and other things. Not all of the methods are guaranteed to return anything.
 * @author xDest
 *
 */
public interface Account extends Depositable, Withdrawlable {

	
	/**
	 * Get the name of this account.
	 * <br/><strong>Note:</strong> This is not the name of the account owner, but rather solely the account's name itself.
	 * @return The name of this account
	 */
	String getAccountName();
	/**
	 * Get the name of the account owner
	 * @return The name of the account owner
	 */
	String getAccountOwner();
	/**
	 * Get the routing number as a long
	 * @return The routing number for this account.
	 */
	long getRoutingNumber();
	/**
	 * Get the account number for this account as a long
	 * @return The account number for this account.
	 */
	long getAccountNumber();
	/**
	 * Get the routing number as a String
	 * @return The routing number for this account
	 */
	String getRoutingNumberAsString();
	/**
	 * Get the account number as a String
	 * @return The account number for this account
	 */
	String getAccountNumberAsString();
	
	/**
	 * Get the balance for this account
	 * @return The balance as a double
	 */
	double getBalance();
	/**
	 * Get a formatted balance in the US Locale.
	 * @return A formatted balance
	 */
	String getFormattedBalance();
}
