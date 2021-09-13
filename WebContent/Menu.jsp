<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bank Application</title>
</head>
<body>
    <center>
        <h1>Bank Management</h1>
        <h2>
            <a href="new">Add New Customer</a>  
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Accounts</h2></caption>
            <tr>
            	<th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>SSN</th>
                <th>Balance</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="customer" items="${listCustomer}">
                <tr>
                	<td><c:out value="${customer.numberOfAcc()}" /></td>
                    <td><c:out value="${customer.firstName}" /></td>
                    <td><c:out value="${customer.lastName}" /></td>
                    <td><c:out value="${customer.ssn}" /></td>
                    <td><c:out value="${customer.accBalance()}" /></td>
                    <td>
                        <a href="transact?id=<c:out value='${customer.numberOfAcc()}' />">Transact</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?id=<c:out value='${customer.numberOfAcc()}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>