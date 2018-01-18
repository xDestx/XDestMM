package com.xdest.mm;

/**
 * A class to represent a "money-out" object
 * @author xDest
 *
 */
public interface Expense extends Depositable {

	/**
	 * Get the total cost for a single occurrence of this Expense
	 * @return The cost of a single occurrence as a double
	 */
	double getOccurrenceCost();
	
	/**
	 * Get the total cost for all occurrences.
	 * <p>If {@link #getTotalOccurances()} returns -1 (indefinite occurrences), total cost will also return -1. This is because it is not possible to calculate the total cost without knowing a fixed amount of occurrences.</p>
	 * @return The total cost of all occurrences combined.
	 */
	double getTotalCost();
	
	
	/**
	 * How many times this expense will occur. -1 for indefinite.
	 * @return The amount of payments needed for this expense. -1 for repeating payment.
	 */
	int getTotalOccurrences();
	
	/**
	 * Get the amount of expense instances to have already occurred.
	 * @return The amount of past expense instances
	 */
	int getPastOccurrenceCount();
	
	/**
	 * Retrieve the amount of remaining occurrences.
	 * <p>If {@link #getTotalOccurrences()} returns -1, this method will also return -1.</p>
	 * @return the remaining occurrences left for this expense, or -1 for an indefinite amount of total occurrences.
	 */
	int getRemainingOccurrences();
	
	/**
	 * Get the amount of money paid to the current occurrence of this expense.
	 * @return The amount paid as a double
	 */
	double getAmountPaid();
	
	/**
	 * Get the total amount paid through all occurrences so far.
	 * @return The total amount paid as a double
	 */
	double getTotalAmountPaid();
	
	/**
	 * Get the amount of money required to completely pay off this expense occurrence.
	 * @return The amount of money required to pay off this expense occurrence
	 */
	double getRemainingOccurrenceValue();
	
	/**
	 * Get the total remaining money through all occurrences of this expense left to pay.
	 * <p>If {@link #getTotalOccurrences()} returns -1, the return value will also be -1.</p>
	 * @return The total remaining value to pay through all occurrences as a double, or -1 for indefinite occurrences.
	 */
	double getTotalRemainingPayValue();
	
	/**
	 * Get the name of this expense
	 * @return This expense name
	 */
	String getName();
	
	/**
	 * Pay off a specific amount of this expense. Overflow will flow into the next occurrence of this expense. Any overflow money that can't be used for a next occurrence will be returned.
	 * @param amountPaid The amount paid.
	 * @return The amount of money left over, most likely 0.
	 */
	@Override
	double deposit(double amountPaid);
	
	
	
}
