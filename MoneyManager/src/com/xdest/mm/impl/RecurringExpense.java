package com.xdest.mm.impl;

import java.text.NumberFormat;
import java.util.Locale;

public class RecurringExpense extends BasicExpense {

	/**
	 * 
	 */
	private static final long serialVersionUID = 803065393161468986L;

	private double instanceCost;
	private int pastOccurances;
	
	public RecurringExpense(String expenseName, double instanceCost, double balancePaidAlready) {
		super(expenseName, -1, balancePaidAlready);
		this.instanceCost = instanceCost;
		pastOccurances = 0;
	}

	@Override
	public double getOccurrenceCost() {
		return this.instanceCost;
	}

	@Override
	public int getTotalOccurrences() {
		return -1;
	}

	@Override
	public int getPastOccurrenceCount() {
		return pastOccurances;
	}

	@Override
	public double getTotalRemainingPayValue() {
		return -1;
	}
	
	@Override
	public double getTotalCost() {
		return -1;
	}
	
	@Override
	public int getRemainingOccurrences() {
		return -1;
	}

	@Override
	public double deposit(double d) {
		this.balance+=d;
		pastOccurances++;
		return 0;
	}
	
	
	@Override
	public String toString() {
		return "[RecurringExpense: {" + getName() + "} " + " (per Occurrence: " + NumberFormat.getCurrencyInstance(Locale.US).format(getOccurrenceCost())+ ") (remaining for this instance: " + NumberFormat.getCurrencyInstance(Locale.US).format(this.getRemainingOccurrenceValue()) + ") "  + this.getTotalAmountPaid() + "]";
	}


	
	
}
