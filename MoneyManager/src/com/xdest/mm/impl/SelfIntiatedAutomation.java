package com.xdest.mm.impl;

import com.xdest.mm.Depositable;
import com.xdest.mm.Withdrawlable;

public class SelfIntiatedAutomation extends BasicAutomation {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3221421574562527618L;

	/**
	 * Create a self initiated automation. Similar to normal automations except this requires an a call to go off.
	 * @param source The source for this automation
	 * @param destination The destination for this automation
	 * @param amount The amount used per execution
	 */
	public SelfIntiatedAutomation(Withdrawlable source, Depositable destination, double amount) {
		super(source, destination, amount);
	}

	@Override
	public boolean doTask() {
		// TODO Auto-generated method stub
		return false;
	}

}
