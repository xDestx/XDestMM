package com.xdest.mm.exception;

import com.xdest.mm.Withdrawlable;

/**
 * An exception for when a Withdrawlable tries to dip below 0 balance.
 * @author xDest
 *
 */
public class InsufficientFundsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1884164154705640326L;

	private final Withdrawlable w;
	private final double overdrawAmount,bal;
	
	/**
	 * Create a new Insufficient funds exception for when a {@link com.xdest.mm.Withdrawlable Withdrawlable} does not have enough funds to continue the operation.
	 * @param w The offending instance
	 * @param overdrawn The amount overdrawn
	 * @param bal The balance available in the withdrawlable
	 */
	public InsufficientFundsException(Withdrawlable w, double overdrawn, double bal) {
		super(w + " tried to pull more value than it had, going " + overdrawn + " under 0.");
		this.w = w;
		this.overdrawAmount = overdrawn;
		this.bal = bal;
	}
	
	/**
	 * The amount of balance available in the withdrawlable. The possibility exists that the withdrawlable is not able to provide a balance. In that case, the balance will be <0.
	 * @return The balance of the withdrawlable, if visible
	 */
	public double getBalance() {
		return this.bal;
	}
	
	/**
	 * Get the amount under 0 the withdrawlable would have went if this transaction had went through.
	 * @return The amount below 0
	 */
	public double getAmountOverdrawn() {
		return this.overdrawAmount;
	}
	
	/**
	 * The Withdrawlable which was drawn from
	 * @return It's instance
	 */
	public Withdrawlable getOffendingInstance() {
		return this.w;
	}
	
}
