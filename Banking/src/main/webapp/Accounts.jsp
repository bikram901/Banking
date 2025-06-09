<%@page import="dto.BankAccount"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome to account </h1>
	<%List<BankAccount> list= (List<BankAccount>)request.getSession().getAttribute("list"); 
	
		if(list.isEmpty()){	
	%>
	<h1>NO Active Accounts Found</h1>
	<% } else{ %>
		<%for(BankAccount account: list){ %>
		<a href="setactiveaccount?acno=<%=account.getAccno() %>"><button><%=account.getAccno() %></button></a>
		<%} %>
	<%} %>



</body>
</html>