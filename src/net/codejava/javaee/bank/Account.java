package net.codejava.javaee.bank;

import java.util.List;

public class Account {
    private double balance = 0;
    private int accountNumber;

    Account(int accountNumber, double balance){
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public final void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

}
