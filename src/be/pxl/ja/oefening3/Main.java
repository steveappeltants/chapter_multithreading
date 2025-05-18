package be.pxl.ja.oefening3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        try (BufferedWriter logger = new BufferedWriter(new FileWriter("transaction.log", true))) {
            Properties properties = new Properties();
            properties.load(Main.class.getResourceAsStream("config.properties"));
            
            int initialBalance = Integer.parseInt(properties.getProperty("initial.balance"));
            int numberOfUsers = Integer.parseInt(properties.getProperty("number.of.users"));
            int numberOfTransactions = Integer.parseInt(properties.getProperty("number.of.transactions"));
            int transactionLimit = Integer.parseInt(properties.getProperty("transaction.limit"));
            
            System.out.printf("Initial Balance: €%d | Users: %d | Transactions per User: €%d | Transaction Limit: "
                    + "€%d%n", initialBalance, numberOfUsers, numberOfTransactions, transactionLimit);
            
            BankAccount bankAccount = new BankAccount("BE48 1234 5678 9012", initialBalance, logger);
            
            System.out.println("AccountNumber: " + bankAccount.getAccountNumber() +
                    "| Balance: €" + bankAccount.getBalance());
            
            // Start threads
            Thread[] threads = new Thread[numberOfUsers];
            for (int i = 0; i < numberOfUsers; i++) {
                final int userId = i + 1;
                threads[i] = new Thread(() -> {
                    Random random = new Random();
                    
                    System.out.printf("User-%d started transactions.%n", userId);
                    
                    for (int j = 0; j < numberOfTransactions; j++) {
                        int amount = random.nextInt(transactionLimit) + 1;
                        if (random.nextBoolean()) {
                            bankAccount.deposit(amount, "User-" + userId);
                        } else {
                            bankAccount.withdraw(amount, "User-" + userId);
                        }
                    }
                    System.out.printf("User-%d completed all transactions.%n", userId);
                });
                threads[i].start();
            }
            // Wait threads to complete
            for (Thread thread : threads) {
                thread.join();
            }
            System.out.println("All user threads have completed execution.");
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Bank account simulation has finished.");
    }
}