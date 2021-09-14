<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Bank Application</title>
</head>
<body>
    <div align="center">
			<form action="deposit?id=${param.id}" method="post">
        <table border="1" cellpadding="5">
            <caption>
            	<h2>Deposit</h2>
            </caption>        
            <tr>
                <th>Amount to deposit: </th>
                <td>
                	<input type="text" name="deposited" size="45"/>
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
