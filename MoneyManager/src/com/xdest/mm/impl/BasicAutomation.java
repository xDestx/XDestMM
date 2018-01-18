package com.xdest.mm.impl;

import com.xdest.mm.Account;
import com.xdest.mm.Automation;
import com.xdest.mm.Depositable;
import com.xdest.mm.Withdrawlable;

public abstract class BasicAutomation implements Automation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7251426030716410463L;

	protected final Withdrawlable source;
	protected final Depositable destination;
	protected double amount;
	
	public BasicAutomation(Withdrawlable source, Depositable destination) {
		this.source = source;
		this.destination = destination;
	}
	
}
