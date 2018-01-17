package com.xdest.mm.exception;

import com.xdest.mm.Withdrawlable;

public class InsufficientFundsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1884164154705640326L;

	private final Withdrawlable w;
	private final double overdrawAmount;
	
	public InsufficientFundsException(Withdrawlable w, double overdrawn) {
		super(w + " tried to pull more value than it had, going " + overdrawn + " under 0.");
		this.w = w;
		this.overdrawAmount = overdrawn;
	}
	
	public double getAmountOverdrawn() {
		return this.overdrawAmount;
	}
	
	public Withdrawlable getOffendingInstance() {
		return this.w;
	}
	
}
