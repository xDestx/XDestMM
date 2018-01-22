package com.xdest.mm.impl;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

import com.xdest.mm.Account;
import com.xdest.mm.Automation;
import com.xdest.mm.Depositable;
import com.xdest.mm.Expense;
import com.xdest.mm.MMUser;
import com.xdest.mm.Manager;
import com.xdest.mm.ScheduledAutomation;
import com.xdest.mm.Withdrawlable;
import com.xdest.mm.exception.InsufficientFundsException;

/**
 * An implementation of the Manager class using System.out
 * 
 * @author xDest
 *
 */
public class TextManager implements Manager {

	private final MMUser user;

	public TextManager(MMUser u) {
		user = u;
	}

	@Override
	public void listAccounts() {
		System.out.println("==== Accounts ====");
		for (Account a : user.getAccounts()) {
			System.out.println(a);
		}
		System.out.println("==== Accounts ====");
	}

	@Override
	public void listExpenses() {
		System.out.println("==== Expenses ====");
		for (Expense e : user.getExpenses()) {
			System.out.println(e);
		}
		System.out.println("==== Expenses ====");
	}

	@Override
	public void listAutomations() {
		System.out.println("==== Automations ====");
		for (Automation a : user.getAutomations()) {
			System.out.println(a);
		}
		System.out.println("==== Automations ====");
	}

	/**
	 * @see #createAutomation(Account, Account, long, double)
	 */
	@Override
	@Deprecated
	public ScheduledAutomation createScheduledAutomation(Withdrawlable source, Depositable destination, long interval,
			double amount) {
		return null;
	}

	/**
	 * Simplified version of
	 * {@link #createAutomation(Withdrawlable, Depositable, long, double)}
	 * 
	 * @param source
	 *            The source account
	 * @param destination
	 *            The destination account
	 * @param interval
	 *            The interval in minutes between auto transfers
	 * @param amount
	 *            The amount per automation
	 * @return The automation
	 */
	public Automation createScheduledAutomation(Account source, Account destination, long interval, double amount) {
		DefaultAutomation da = new DefaultAutomation(source, destination, interval, amount);
		this.user.addAutomation(da);
		// TODO This
		return da;
	}

	/**
	 * Simplified version of
	 * {@link #createAutomation(Withdrawlable, Depositable, double)}
	 * 
	 * @param source
	 *            The source
	 * @param destination
	 *            The destination
	 * @param amount
	 *            The amount to transfer
	 * @return The automation instance
	 */
	public Automation createAutomation(Account source, Depositable destination, double amount) {
		SelfInitiatedAutomation sia = new SelfInitiatedAutomation(source, destination, amount);
		this.user.addAutomation(sia);
		return sia;
	}

	@Override
	public Account createLocalAccount(String accountName) {
		LocalAccount la = new LocalAccount(user.getUserName(), accountName);
		return la;
	}

	public void doAutomations() {
		for (Automation a : user.getAutomations()) {
			if (a instanceof SelfInitiatedAutomation)
				a.doTask();
		}
	}

	@Override
	public boolean addAccountToUser(Account a) {
		if (a == null)
			return false;
		return user.addAccount(a);
	}

	/**
	 * Does repeated text actions until the user quits.
	 * 
	 * @param s
	 *            The scanner. Will not be closed.
	 */
	public void manage(Scanner s) {
		String input = "";
		while (!input.equalsIgnoreCase("\\e")) {
			System.out.println("\n\nSelect an option");
			displayOptions();
			input = s.nextLine();
			if (input.equalsIgnoreCase("0")) {
				// Create account
				boolean validName = false;
				String name = "";
				while (!validName) {
					validName = true;
					System.out.println("Enter a valid name for the new account");
					name = s.nextLine();
					if (name.equalsIgnoreCase("")) {
						validName = false;
					}
					for (Account a : user.getAccounts()) {
						if (a.getAccountName().equalsIgnoreCase(name)) {
							validName = false;
							System.out.println("That account name is being used already\n");
							break;
						}
					}

				}
				if (addAccountToUser(createLocalAccount(name))) {
					System.out.println("Successfully created account " + name + "!");
				} else {
					System.out.println("Failed to create account " + name);
				}
			} else if (input.equalsIgnoreCase("1")) {
				System.out.println(
						"Is this a fixed value expense or a recurring expense (ex: monthly for indefinite time)\n0. Fixed\n1. Recurring");
				int choice = -1;
				while (choice == -1) {
					try {
						choice = Integer.parseInt(s.nextLine());
						if (choice < 0 || choice > 1) {
							choice = -1;
							System.out.println("Please enter 0 or 1 to select your expense type");
						}
					} catch (Exception e) {
						System.out.println("Please enter 0 or 1 to select your expense type");
					}
				}
				// Create expense
				System.out.println("Enter expense name");
				String expenseName = s.nextLine();
				if (expenseName.equalsIgnoreCase(""))
					expenseName = UUID.randomUUID().toString();
				if (choice == 0) {
					double expenseTotal = -1;
					while (expenseTotal == -1) {
						System.out.println("Enter the total cost for this expense (omit currency signs)");
						String total = s.nextLine();
						try {
							expenseTotal = Double.parseDouble(total);
						} catch (Exception e) {
							expenseTotal = -1;
							System.out.println("Invalid amount");
						}
					}

					int count = -1;
					while (count == -1) {
						System.out.println("Enter the total amount of payments for this expense");
						String total = s.nextLine();
						try {
							count = Integer.parseInt(total);
						} catch (Exception e) {
							count = -1;
							System.out.println("Invalid count");
						}
					}

					Expense e = new FixedValueExpense(expenseName, count, expenseTotal, 0);
					user.addExpense(e);
				} else if (choice == 1) {
					double occurrencTotal = -1;
					while (occurrencTotal == -1) {
						System.out.println("Enter the total cost for this expense per occcurrence(omit currency signs)");
						String total = s.nextLine();
						try {
							occurrencTotal = Double.parseDouble(total);
						} catch (Exception e) {
							occurrencTotal = -1;
							System.out.println("Invalid amount");
						}
					}
					
					Expense e = new RecurringExpense(expenseName, occurrencTotal, 0);
					user.addExpense(e);
				}
			} else if (input.equalsIgnoreCase("2")) {
				// Create automation
				Account source;
				Depositable destination;

				System.out.println("Automation creation\n\nSelect an source--\n");
				source = userSelectAccount(s);
				System.out.println("\nSelect a destination: ");

				destination = selectDepositable(s);

				double amountToTransfer = -1;

				while (amountToTransfer == -1) {
					try {
						String amt = "";
						System.out.println("Enter amount to transfer per occurrence(Omit currency signs): ");
						amt = s.nextLine();
						amountToTransfer = Double.parseDouble(amt);
					} catch (Exception e) {
						amountToTransfer = -1;
						System.out.println("Invalid, try again");
					}
				}
				System.out.println("New automation: " + this.createAutomation(source, destination, amountToTransfer));
			} else if (input.equalsIgnoreCase("3")) {
				// List accounts
				listAccounts();
			} else if (input.equalsIgnoreCase("4")) {
				// List expenses
				listExpenses();
			} else if (input.equalsIgnoreCase("5")) {
				// List automations
				listAutomations();
			} else if (input.equalsIgnoreCase("6")) {
				// List account summary
				String totalBalance = "";
				String totalOwed = "";
				String totalRecurringExpenseAmount = "";
				String expenseCount = "";
				String accountCount = "";
				String totalNet = "";

				double bal = 0;
				int accC = 0;
				for (Account a : user.getAccounts()) {
					bal += a.getBalance();
					accC++;
				}
				totalBalance = NumberFormat.getCurrencyInstance(Locale.US).format(bal);
				accountCount = "" + accC;

				double owed = 0;
				double recurred = 0;
				int expenses = 0;
				for (Expense e : user.getExpenses()) {
					if (e.getTotalCost() != -1) {
						owed += e.getTotalCost()-e.getTotalAmountPaid();
					} else {
						recurred += e.getOccurrenceCost();
					}
					expenses++;
				}
				totalOwed = NumberFormat.getCurrencyInstance(Locale.US).format(owed);
				totalRecurringExpenseAmount = NumberFormat.getCurrencyInstance(Locale.US).format(recurred);
				expenseCount = "" + expenses;

				totalNet = (bal - owed < 0 ? "-" : "")
						+ NumberFormat.getCurrencyInstance(Locale.US).format(Math.abs(bal - owed));

				System.out.println("\n" + "Total Account Count: " + accountCount + "\n" + "Total Expense Count: "
						+ expenseCount + "\n" + "\nTotal balance: " + totalBalance + "\n"
						+ "Total owed (Not including indefinite): " + totalOwed + "\n" + "Total recurring: "
						+ totalRecurringExpenseAmount + "\n" + "\nNet (not including indefinite): " + totalNet + "\n");
			} else if (input.equalsIgnoreCase("7")) {
				// Manage an account
				String secondaryInput = "";

				Account selectedAccount = userSelectAccount(s);

				System.out.println(
						"\nSelect another option: \n" + "0. Deposit\n" + "1. Withdraw\n" + "2. Check balance\n");
				boolean actionComplete = false;
				while (!actionComplete) {
					secondaryInput = s.nextLine();
					actionComplete = true;
					if (secondaryInput.equalsIgnoreCase("0")) {
						double amountToDeposit = -1;

						while (amountToDeposit == -1) {
							try {
								String amt = "";
								System.out.println("Enter amount to deposit (Omit currency signs): ");
								amt = s.nextLine();
								amountToDeposit = Double.parseDouble(amt);
							} catch (Exception e) {
								amountToDeposit = -1;
								System.out.println("Invalid, try again");
							}
						}
						selectedAccount.deposit(amountToDeposit);
						System.out.println("Done!");
						actionComplete = true;
					} else if (secondaryInput.equalsIgnoreCase("1")) {

						double amountToWithdraw = -1;

						while (amountToWithdraw == -1) {
							try {
								String amt = "";
								System.out.println("Enter amount to withdraw (Omit currency signs): ");
								amt = s.nextLine();
								amountToWithdraw = Double.parseDouble(amt);
							} catch (Exception e) {
								amountToWithdraw = -1;
								System.out.println("Invalid, try again");
							}
						}
						try {
							selectedAccount.withdraw(amountToWithdraw);
							System.out.println("Done!");
						} catch (InsufficientFundsException e) {
							System.out.println("Not enough funds!");
							// e.printStackTrace();
						}
						actionComplete = true;

					} else if (secondaryInput.equalsIgnoreCase("2")) {
						System.out.println("Balance: " + selectedAccount.getFormattedBalance());
					} else {
						System.out.println("\nSorry, I didn't understand. Try again: ");
						actionComplete = false;
					}
				}
			} else if (input.equalsIgnoreCase("8")) {
				// Initiate transfer
				System.out.println("Initiate a transfer\n\nSelect an source--\n");
				Account source = userSelectAccount(s);
				System.out.println("\nSelect a destination: ");

				// TODO: Transfer...Select a destination (Expense or another account for
				// now)...choose amount..move c4$h...fin

				Depositable destination = selectDepositable(s);

				double amountToTransfer = -1;

				while (amountToTransfer == -1) {
					try {
						String amt = "";
						System.out.println("Enter amount to transfer (Omit currency signs): ");
						amt = s.nextLine();
						amountToTransfer = Double.parseDouble(amt);
					} catch (Exception e) {
						amountToTransfer = -1;
						System.out.println("Invalid, try again");
					}
				}
				try {
					source.deposit(destination.deposit(source.withdraw(amountToTransfer)));
					// This part wouldn't work if source was only withdrawable
				} catch (InsufficientFundsException e) {
					System.out.println("Sorry, there weren't enough funds in your account.");
				}

			} else if (input.equalsIgnoreCase("9")) {
				this.listAccounts();
				this.listAutomations();
				System.out.println("Are you sure you want to execute all automations? Y/N");
				String in = s.nextLine();
				if (in.equalsIgnoreCase("y")) {
					System.out.println("Enacting...");
					doAutomations();
				} else {
					System.out.println("Cancelling\n");
				}
			} else if (input.equalsIgnoreCase("\\e")) {
				System.out.println("au revoir chico");
				user.save();
			} else {
				System.out.println("これは何ですか。ww");
			}
		}
	}

	private Account userSelectAccount(Scanner s) {
		Account[] accounts = new Account[user.getAccounts().size()];
		int i = 0;
		for (Account a : user.getAccounts()) {
			accounts[i] = a;
			i++;
		}
		String accs = "";
		for (int k = 0; k < accounts.length; k++) {
			accs += k + ". " + accounts[k].getAccountName() + "\n";
		}
		System.out.println("Select an account by number:\n" + accs);

		int accountNum = -1;
		while (accountNum == -1) {
			try {
				accountNum = Integer.parseInt(s.nextLine());
				if (accountNum >= accounts.length || accountNum < 0)
					accountNum = -1;
			} catch (Exception e) {
				accountNum = -1;
			}
		}

		Account selectedAccount = accounts[accountNum];
		return selectedAccount;
	}

	private Depositable selectDepositable(Scanner s) {
		Depositable[] ds = new Depositable[user.getAccounts().size() + user.getExpenses().size()];
		int i = 0;
		for (Account a : user.getAccounts()) {
			ds[i] = a;
			i++;
		}
		for (Expense e : user.getExpenses()) {
			ds[i] = e;
			i++;
		}
		String accs = "";
		for (int k = 0; k < ds.length; k++) {
			accs += k + ". " + ds[k] + "\n";
		}
		System.out.println("Select a depositable location by number:\n" + accs);

		int selectedNum = -1;
		while (selectedNum == -1) {
			try {
				selectedNum = Integer.parseInt(s.nextLine());
				if (selectedNum >= ds.length || selectedNum < 0)
					selectedNum = -1;
			} catch (Exception e) {
				selectedNum = -1;
			}
		}

		Depositable selected = ds[selectedNum];
		return selected;
	}

	private void displayOptions() {
		System.out.println("" + "0. Create a new account\n" + "1. Create a new expense\n"
				+ "2. Create a new automation\n" + "3. List accounts\n" + "4. List expenses\n" + "5. List automations\n"
				+ "6. List summary of accounts\n" + "7. Manage an account\n" + "8. Initiate a transfer\n"
				+ "9. Enact all non scheduled automations\n" + "\\e. Quit");
	}

	/**
	 * @see #createAutomation(Account, Account, double)
	 */
	@Override
	@Deprecated
	public Automation createAutomation(Withdrawlable source, Depositable destination, double amount) {
		return null;
	}

}
