<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<center>
        <h1>Bank Management</h1>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Customer Account</h2></caption>
            <h3><a href="list">Customers</a></h3>
            <tr>
            	<th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>SSN</th>
                <th>Transact</th>
            </tr>
            
                	<td><c:out value="${existingCustomer.numberOfAcc()}" /></td>
                    <td><c:out value="${existingCustomer.firstName}" /></td>
                    <td><c:out value="${existingCustomer.lastName}" /></td>
                    <td><c:out value="${existingCustomer.ssn}" /></td>
                    <td><a href="depositForm?id=<c:out value='${existingCustomer.numberOfAcc()}' />">Deposit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="withdrawForm?id=<c:out value='${existingCustomer.numberOfAcc()}' />">Withdraw</a></td>
                      

        </table>
    </div>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h3>Transactions</h3></caption>
            <tr>
            	<th>Deposits</th>
                <th>Withdrawals</th>
                <th>Balance</th>
            </tr>
            <c:forEach var="transaction" items="${listTransactions}">
                <tr>
                	<td><c:out value="${transaction.deposit}" /></td>
                    <td><c:out value="${transaction.withdrawal}" /></td>
                    <td><c:out value="${transaction.balance}" /></td>
                </tr>
            </c:forEach>    
        </table>
    </div>      
</body>
</html>