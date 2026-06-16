/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package atm_system;

/**
 *
 * @author ACER
 */
import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private int accountNumber;
    private int pin;
    private double balance;

    public Account(int accountNumber, int pin) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = 0;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public boolean validatePin(int enteredPin) {
        return pin == enteredPin;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful.");
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class ATM_System {
    static ArrayList<Account> accounts = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static Account findAccount(int accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accountNumber) {
                return acc;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Account Number: ");
                    int accNum = scanner.nextInt();

                    if (findAccount(accNum) != null) {
                        System.out.println("Account already exists.");
                        break;
                    }

                    System.out.print("Create PIN: ");
                    int pin = scanner.nextInt();

                    accounts.add(new Account(accNum, pin));
                    System.out.println("Account created successfully.");
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    accNum = scanner.nextInt();

                    Account depositAcc = findAccount(accNum);

                    if (depositAcc == null) {
                        System.out.println("Account not found.");
                        break;
                    }

                    System.out.print("Enter PIN: ");
                    pin = scanner.nextInt();

                    if (!depositAcc.validatePin(pin)) {
                        System.out.println("Incorrect PIN.");
                        break;
                    }

                    System.out.print("Enter Deposit Amount: ");
                    double deposit = scanner.nextDouble();

                    depositAcc.deposit(deposit);
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    accNum = scanner.nextInt();

                    Account withdrawAcc = findAccount(accNum);

                    if (withdrawAcc == null) {
                        System.out.println("Account not found.");
                        break;
                    }

                    System.out.print("Enter PIN: ");
                    pin = scanner.nextInt();

                    if (!withdrawAcc.validatePin(pin)) {
                        System.out.println("Incorrect PIN.");
                        break;
                    }

                    System.out.print("Enter Withdrawal Amount: ");
                    double withdraw = scanner.nextDouble();

                    withdrawAcc.withdraw(withdraw);
                    break;

                case 4:
                    System.out.print("Enter Account Number: ");
                    accNum = scanner.nextInt();

                    Account balanceAcc = findAccount(accNum);

                    if (balanceAcc == null) {
                        System.out.println("Account not found.");
                        break;
                    }

                    System.out.print("Enter PIN: ");
                    pin = scanner.nextInt();

                    if (!balanceAcc.validatePin(pin)) {
                        System.out.println("Incorrect PIN.");
                        break;
                    }

                    System.out.println("Current Balance: R" + balanceAcc.getBalance());
                    break;

                case 5:
                    System.out.println("Thank you for using the ATM.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}