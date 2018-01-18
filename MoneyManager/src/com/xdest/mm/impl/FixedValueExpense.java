package com.xdest.mm.impl;

import java.text.NumberFormat;
import java.util.Locale;

import com.xdest.mm.Expense;

public class FixedValueExpense implements Expense {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5810440785032769680L;
	
	private int totalOccuranceCount;
	private int occurances;
	private double balance, totalCost;
	private String expenseName;
	
	/**
	 * Create a new FixedValue expense
	 * @param expenseName The name of this expense
	 * @param occuranceCount The amount of payments desired
	 * @param totalExpenseCost The total amount of value required to pay this expense
	 * @param balancePaidAlready The amount of this expense already paid
	 */
	public FixedValueExpense(String expenseName, int occuranceCount, double totalExpenseCost, double balancePaidAlready) {
		this.totalOccuranceCount = occuranceCount;
		this.totalCost = totalExpenseCost;
		this.balance = balancePaidAlready;
		this.occurances = 0;
		this.expenseName = expenseName;
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
	public int getRemainingOccurrences() {
		return getTotalOccurrences()-getPastOccurrenceCount();
	}

	@Override
	public double getAmountPaid() {
		return balance%getOccurrenceCost();
	}

	@Override
	public double getTotalAmountPaid() {
		return balance;
	}

	@Override
	public double getRemainingOccurrenceValue() {
		return getOccurrenceCost()-getAmountPaid();
	}

	@Override
	public double getTotalRemainingPayValue() {
		return getTotalCost();
	}

	@Override
	public String getName() {
		return this.expenseName;
	}

	@Override
	public double deposit(double amountPaid) {
		double amountToReturn = 0;
		this.balance+=amountPaid;
		if(balance>this.totalCost) {
			amountToReturn+=balance-this.totalCost;
			balance-=amountToReturn;
		}
		return amountToReturn;
	}
	
	@Override
	public String toString() {
		return "[Expense: {" + getName() + "} " + NumberFormat.getCurrencyInstance(Locale.US).format(getTotalCost()) + " (per Occurrence: " + NumberFormat.getCurrencyInstance(Locale.US).format(getOccurrenceCost())+ ") (remaining: " + NumberFormat.getCurrencyInstance(Locale.US).format(getTotalRemainingPayValue()) + ")]";
	}

}
