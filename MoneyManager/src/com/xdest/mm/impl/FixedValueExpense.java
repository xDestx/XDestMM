package com.xdest.mm.impl;

import java.text.NumberFormat;

import java.util.Locale;

public class FixedValueExpense extends BasicExpense {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5810440785032769680L;
	
	private int totalOccuranceCount;
	private int occurances;
	
	/**
	 * Create a new FixedValue expense
	 * @param expenseName The name of this expense
	 * @param occuranceCount The amount of payments desired
	 * @param totalExpenseCost The total amount of value required to pay this expense
	 * @param balancePaidAlready The amount of this expense already paid
	 */
	public FixedValueExpense(String expenseName, int occuranceCount, double totalExpenseCost, double balancePaidAlready) {
		super(expenseName,totalExpenseCost,balancePaidAlready);
		this.totalOccuranceCount = occuranceCount;
		this.occurances = 0;
	}

	@Override
	public double getOccurrenceCost() {
		return totalCost/totalOccuranceCount;
	}

	@Override
	public double getTotalCost() {
		return this.totalCost;
	}

	@Override
	public int getTotalOccurrences() {
		return this.totalOccuranceCount;
	}

	@Override
	public int getPastOccurrenceCount() {
		return this.occurances;
	}
	
	@Override
	public String getName() {
		return this.expenseName;
	}

	@Override
	public String toString() {
		return "[FixedExpense: {" + getName() + "} " + NumberFormat.getCurrencyInstance(Locale.US).format(getTotalCost()) + " (per Occurrence: " + NumberFormat.getCurrencyInstance(Locale.US).format(getOccurrenceCost())+ ") (remaining: " + NumberFormat.getCurrencyInstance(Locale.US).format(getTotalRemainingPayValue()) + ")]";
	}

}
