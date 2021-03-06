package net.codejava.javaee.bank;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDAO customerDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		customerDAO = new CustomerDAO(jdbcURL, jdbcUsername, jdbcPassword);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertCustomer(request, response);
				break;
			case "/delete":
				deleteCustomer(request, response);
				break;
			case "/transact":
				showTransactForm(request, response);
				break;
			case "/depositForm":
				showDepositForm(request, response);
				break;
			case "/withdrawForm":
				showWithdrawForm(request, response);
				break;
			case "/deposit":
				deposit(request, response);
				break;
			case "/withdraw":
				withdraw(request, response);
				break;
			default:
				listCustomer(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listCustomer(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Customer> listCustomer = customerDAO.GetAllAccounts();
		request.setAttribute("listCustomer", listCustomer);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Menu.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerForm.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showDepositForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("id", id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("DepositForm.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showWithdrawForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("id", id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WithdrawalForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showTransactForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Customer existingCustomer = customerDAO.GetAccount(id);
		request.setAttribute("existingCustomer", existingCustomer);
		List<Transactions> listTransactions = customerDAO.GetAllTransactions(id);
		Collections.reverse(listTransactions);
		request.setAttribute("listTransactions", listTransactions);
		RequestDispatcher dispatcher = request.getRequestDispatcher("TransactForm.jsp");
		dispatcher.forward(request, response);

	}

	private void insertCustomer(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String ssn = request.getParameter("ssn");
		Double initialDeposit = Double.parseDouble(request.getParameter("initialDeposit"));
		customerDAO.AddAccount(firstName, lastName, ssn, initialDeposit);
		response.sendRedirect("list");
	}

	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		customerDAO.DeleteAccount(id);
		response.sendRedirect("list");

	}
	
	private void deposit(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Double deposit = Double.parseDouble(request.getParameter("deposited")); 
		Customer existingCustomer = customerDAO.GetAccount(id);
		Double balance = existingCustomer.accBalance() + deposit;
		customerDAO.Deposit(id, deposit, balance);
		response.sendRedirect("transact?id="+id);
	}
	
	private void withdraw(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Double withdraw = Double.parseDouble(request.getParameter("withdrew")); 
		Customer existingCustomer = customerDAO.GetAccount(id);
		Double balance = existingCustomer.accBalance() - withdraw; 
		customerDAO.Withdraw(id, withdraw, balance);
		response.sendRedirect("transact?id="+id);
	}

}
