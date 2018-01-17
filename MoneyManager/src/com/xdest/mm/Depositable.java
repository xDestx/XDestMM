package com.xdest.mm;

import java.io.Serializable;

/**
 * A class representing something can can accept deposits
 * @author xDest
 *
 */
public interface Depositable extends Serializable  {

	/**
	 * Accept an amount and return however much money cannot be accepted.
	 * @param d The amount to send to this instance
	 * @return the amount which cannot be accepted
	 */
	double deposit(double d);
}
