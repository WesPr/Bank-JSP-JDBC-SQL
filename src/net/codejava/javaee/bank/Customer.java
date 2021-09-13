package net.codejava.javaee.bank;

public class Customer{
    private final String firstName;
    private final String lastName;
    private final String ssn;
    private final Account account;

    Customer(String firstName, String lastName, String ssn, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.account = account;
    }

    public int numberOfAcc(){
        return account.getAccountNumber();
    }
    
    public double accBalance(){
        return account.getBalance();
    }

    Account getAccount(){
        return account;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSsn() {
        return ssn;
    }
}
