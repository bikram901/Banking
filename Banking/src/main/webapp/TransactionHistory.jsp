<%@page import="dao.BankDao"%>
<%@page import="dto.BankTransactions"%>
<%@page import="java.util.List"%>
<%@page import="dto.BankAccount"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Welcome to Transaction HistoryT</h1>
	
	<%	long acno = (long) request.getSession().getAttribute("acno"); 
		BankDao bankDao = new BankDao();
		BankAccount bankAccount = bankDao.fetch_by_accno(acno);
		List<BankTransactions> list=bankAccount.getBankTransactions();
	
	%>
	
	<table border="1">
		<tr>
			<th>Transction Id</th>
			<th>Deposit</th>
			<th>Withdrawl</th>
			<th>Balance</th>
			<th>Date & Time</th>
		</tr>
		
		<%for(BankTransactions bankTransactions : list){ %>
		<tr>
			<th><%=bankTransactions.getTid() %></th>
			<th><%=bankTransactions.getDeposit() %></th>
			<th><%=bankTransactions.getWithdraw() %></th>
			<th><%=bankTransactions.getBalance() %></th>
			<th><%=bankTransactions.getLocalDateTime() %></th>
		</tr>
		<%} %>
	
	</table>
	
	<a href="Transactionpage.jsp"><button>Back</button></a>
	
</body>
</html>