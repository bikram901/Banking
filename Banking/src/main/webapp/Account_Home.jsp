<%@page import="java.util.List"%>
<%@page import="dto.BankAccount"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bank Details</title>
</head>
<body>
	<h1>Welcome to Account Home </h1>
	
	<% List<BankAccount> list = (List<BankAccount>) request.getSession().getAttribute("list"); %>
	<table border="1">
		<tr>
			<th>Account_Number</th>
			<th>Account_Type</th>
			<th>Balance</th>
			<th>Account_Limit</th>
			<th>Account_Status</th>
			<th>Customer_Name</th>
			<th>Cuatomer_Id</th>
			<th>Changed_Status</th>
		</tr>	
		
		<% for(BankAccount bankaccount : list){ %>
			<tr>
			<th><%=bankaccount.getAccno() %></th>
			<th><%=bankaccount.getAccount_type() %></th>
			<th><%=bankaccount.getAmount() %></th>
			<th><%=bankaccount.getAcc_limit() %></th>
			<th><%=bankaccount.isStatus() %></th>
			<th><%=bankaccount.getCustomer().getName() %></th>
			<th><%=bankaccount.getCustomer().getCustid() %></th>
			<th><a href="changestatus?acno=<%=bankaccount.getAccno() %>"><button>Changed_Status</button></a></th>
		</tr>			

		
		<%} %>
		
	</table>

</body>
</html>