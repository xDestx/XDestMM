package com.xdest.mm;

import java.io.Serializable;

import com.xdest.mm.exception.InsufficientFundsException;

/**
 * An interface used for things which can be withdrawn
 * @author xDest
 *
 */
public interface Withdrawlable extends Serializable {

	/**
	 * Withdraw an amount of money
	 * @param amt The amount to withdraw
	 * @return The amount of money which was able to be taken out
	 */
	double withdraw(double amt) throws InsufficientFundsException;
}
