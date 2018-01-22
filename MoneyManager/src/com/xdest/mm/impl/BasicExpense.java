package com.xdest.mm.impl;

import java.text.NumberFormat;
import java.util.Locale;

import com.xdest.mm.Expense;

public abstract class BasicExpense implements Expense {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5810440785032769680L;
	
	protected double balance, totalCost;
	protected String expenseName;
	
	/**
	 * Create a new FixedValue expense
	 * @param expenseName The name of this expense
	 * @param totalExpenseCost The total amount of value required to pay this expense
	 * @param balancePaidAlready The amount of this expense already paid
	 */
	public BasicExpense(String expenseName, double totalExpenseCost, double balancePaidAlready) {
		this.totalCost = totalExpenseCost;
		this.balance = balancePaidAlready;
		this.expenseName = expenseName;
	}


	@Override
	public double getTotalCost() {
		return this.totalCost;
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
		return getTotalCost()-this.getTotalAmountPaid();
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
			System.out.println("balyeet");
			amountToReturn+=balance-this.totalCost;
			balance-=amountToReturn;
		}
		return amountToReturn;
	}
	
	@Override
	public String toString() {
		return "[BasicExpense: {" + getName() + "} " + NumberFormat.getCurrencyInstance(Locale.US).format(getTotalCost()) + "(remaining: " + NumberFormat.getCurrencyInstance(Locale.US).format(getTotalRemainingPayValue()) + ")]";
	}
	
}
