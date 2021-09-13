<center>
        <h1>Bank Management</h1>
        <h2>
            <a href="new">Deposit or Withdraw</a>  
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Customer Account</h2></caption>
            <tr>
            	<th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>SSN</th>
                <th>Balance</th>
            </tr>
                	<td><c:out value="${customer.numberOfAcc()}" /></td>
                    <td><c:out value="${customer.firstName}" /></td>
                    <td><c:out value="${customer.lastName}" /></td>
                    <td><c:out value="${customer.ssn}" /></td>
                    <td><c:out value="${customer.accBalance()}" /></td>

        </table>
    </div>   
</body>
</html>