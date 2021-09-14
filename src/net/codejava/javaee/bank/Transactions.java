package net.codejava.javaee.bank;

public class Transactions {
	private double deposit;
	private double withdrawal;
	private double balance;
	
	Transactions(double deposit, double withdrawal, double balance){
		this.deposit = deposit;
		this.withdrawal = withdrawal;
		this.balance = balance;
	}
	
	
	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	public double getWithdrawal() {
		return withdrawal;
	}
	public void setWithdrawal(double withdrawal) {
		this.withdrawal = withdrawal;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	

}
