<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Bank Application</title>
</head>
<body>
	<center>
		<h1>Bank Management</h1>
	</center>
    <div align="center">
			<form action="insert" method="post">
        <table border="1" cellpadding="5">
            <caption>
            	<h2>Add New Customer </h2>
            </caption>        
            <tr>
                <th>First Name: </th>
                <td>
                	<input type="text" name="firstName" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Last Name: </th>
                <td>
                	<input type="text" name="lastName" size="45"/>
                </td>
            </tr>
            <tr>
                <th>SSN: </th>
                <td>
                	<input type="text" name="ssn" size="5"/>
                </td>
            </tr>
            <tr>
                <th>Initial Deposit: </th>
                <td>
                	<input type="text" name="initialDeposit" size="45"/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Save" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
