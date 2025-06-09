<%@page import="dto.Customer" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome_to_Account_Creaction_page</h1>

	<% Customer customer = (Customer)request.getSession().getAttribute("customer"); %>
	<h1>Hello, <%=customer.getName() %></h1>
	
	<form action="createbankaccount" method="post">
		<input type="radio" name="banktype" value="savings" required="required">Savings
		<input type="radio" name="banktype" value="current" required="required">Current<br><br>
		<button>Submit</button>   <button type="reset">Cancel</button>
	</form>

</body>
</html>