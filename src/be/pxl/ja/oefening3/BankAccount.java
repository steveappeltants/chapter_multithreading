package be.pxl.ja.oefening3;

import java.io.BufferedWriter;
import java.io.IOException;

public class BankAccount {
	private int balance;
	private final String accountNumber;
	private final BufferedWriter logger;
	
	public BankAccount(String accountNumber, int initialBalance, BufferedWriter logger) {
		this.accountNumber = accountNumber;
		this.balance = initialBalance;
		this.logger = logger;
	}
	
	public synchronized void deposit(int amount, String user) {
		// TODO
		balance += amount;
		System.out.printf("User: %s deposited €%d. New Balance: €%d%n", user, amount, balance);
		logTransaction(user, "Deposit: ", amount);
	}
	
	public synchronized void withdraw(int amount, String user) {
		// TODO
		if (balance >= amount) {
			balance -= amount;
			System.out.printf("User: %s withdrew €%d. New Balance: €%d%n", user, amount, balance);
			logTransaction(user, "Withdrawal", amount);
		} else {
			System.out.printf("User: %s attempted to withdraw €%d but insufficient balance. " +
					"Current Balance: €%d%n", user, amount, balance);
			logTransaction(user, "Failed Withdrawal", amount);
		}
	}
	
	public double getBalance() {
		return balance;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	private void logTransaction(String user, String transactionType, int amount) {
		String logMessage = String.format("User: %s, Transaction: %s, Amount: %d, Balance: %d%n",
				user, transactionType, amount, balance);
		synchronized (logger) {
			try {
				logger.write(logMessage);
				logger.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}