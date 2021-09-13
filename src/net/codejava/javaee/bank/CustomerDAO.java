package net.codejava.javaee.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table book
 * in the database.
 * @author www.codejava.net
 *
 */
public class CustomerDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public CustomerDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(
										jdbcURL, jdbcUsername, jdbcPassword);
		}
	}
	
	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	//Create (Add Account)
    int AddAccount(String firstName, String lastName, String ssn, Double balance) throws SQLException {
        int userId = -1;
        int accountId = -1;
        connect();
        try {
        	jdbcConnection.setAutoCommit(false);
            //Add User
            try (PreparedStatement addUser = jdbcConnection.prepareStatement("INSERT INTO Users(FirstName, LastName, SSN) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                addUser.setString(1, firstName);
                addUser.setString(2, lastName);
                addUser.setString(3, ssn);
                addUser.executeUpdate();
                ResultSet addUserResults = addUser.getGeneratedKeys();
                if (addUserResults.next()) {
                    userId = addUserResults.getInt(1);
                }
            }
            //Add Account
            try (PreparedStatement addAccount = jdbcConnection.prepareStatement("INSERT INTO Accounts(Balance) VALUES(?)", Statement.RETURN_GENERATED_KEYS)) {
                addAccount.setDouble(1, balance);
                addAccount.executeUpdate();
                ResultSet addAccountResults = addAccount.getGeneratedKeys();
                if (addAccountResults.next()) {
                    accountId = addAccountResults.getInt(1);
                }
            }
            //Link User to Account
            if (userId > 0 && accountId > 0) {
                try (PreparedStatement linkAccount = jdbcConnection.prepareStatement("INSERT INTO mappings(UserId, AccountId) VALUES(?,?)")) {
                    linkAccount.setInt(1, userId);
                    linkAccount.setInt(2, accountId);
                    linkAccount.executeUpdate();
                }
                jdbcConnection.commit();
            } else {
            	jdbcConnection.rollback();
            }
            //Disconnect
            jdbcConnection.close();
        } catch (SQLException ex) {
            System.err.println("An error has occurred." + ex.getMessage());
        }
        return accountId;
    }
    //Read (Get Details)
    Customer GetAccount(int accountId) throws SQLException {
        Customer customer = null;
        connect();
        try {
            try (PreparedStatement findUser = jdbcConnection.prepareStatement(
                    "SELECT FirstName,LastName,SSN,Balance "
                            + "FROM Users a JOIN mappings b on a.ID = b.UserId "
                            + "JOIN Accounts c on b.AccountId = c.ID "
                            + "WHERE c.ID = ?")) {
                findUser.setInt(1, accountId);
                ResultSet findUserResults = findUser.executeQuery();
                if (findUserResults.next()) {
                    String firstName = findUserResults.getString("FirstName");
                    String lastName = findUserResults.getString("LastName");
                    String ssn = findUserResults.getString("SSN");
                    Double balance = findUserResults.getDouble("Balance");
                    Account account = new Account(accountId, balance);
                    customer = new Customer(firstName, lastName, ssn, account);
                }
            }
        } catch (SQLException ex) {
            System.err.println("An error has occurred." + ex.getMessage());
        }
        return customer;
    }
    //Update (Edit Account)
    boolean UpdateAccount(int accountId, Double newBalance) throws SQLException {
        boolean success = false;
        connect();
        try {
            try (PreparedStatement updateBalance = jdbcConnection.prepareStatement(
                    "UPDATE Accounts SET Balance = ? WHERE ID = ?")) {
                updateBalance.setDouble(1, newBalance);
                updateBalance.setInt(2, accountId);
                updateBalance.executeUpdate();
            }
            success = true;
        } catch (SQLException ex) {
            System.err.println("An error has occurred." + ex.getMessage());
        }
        return success;
    }
    //Delete (Remove Account)
    boolean DeleteAccount(int accountId) throws SQLException {
        boolean success = false;
        connect();
        try {
            try (PreparedStatement deleteRecords = jdbcConnection.prepareStatement(
                    "DELETE Users,Accounts FROM Users "
                            + "JOIN mappings on Users.ID = mappings.UserId "
                            + "JOIN Accounts on Accounts.ID = mappings.AccountId "
                            + "WHERE Accounts.ID = ?")) {
                deleteRecords.setInt(1, accountId);
                deleteRecords.executeUpdate();
            }
            success = true;
        } catch (SQLException ex) {
            System.err.println("An error has occurred." + ex.getMessage());
        }
        return success;
    }

    ArrayList<Customer> GetAllAccounts() throws SQLException {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        connect();
        try {
            try (PreparedStatement findUser = jdbcConnection.prepareStatement(
                    "SELECT AccountId,FirstName,LastName,SSN,Balance "
                            + "FROM Users a JOIN mappings b on a.ID = b.UserId "
                            + "JOIN Accounts c on b.AccountId = c.ID")) {
                ResultSet findUserResults = findUser.executeQuery();
                while (findUserResults.next()) {
                    String firstName = findUserResults.getString("FirstName");
                    String lastName = findUserResults.getString("LastName");
                    String ssn = findUserResults.getString("SSN");
                    Double balance = findUserResults.getDouble("Balance");
                    int accountId = findUserResults.getInt("AccountId");
                    Account account = new Account(accountId, balance);
                    Customer customer = new Customer(firstName, lastName, ssn, account);
                    customers.add(customer);
                }
            }
        } catch (SQLException ex) {
            System.err.println("An error has occurred." + ex.getMessage());
        }
        return customers;
    }
}
