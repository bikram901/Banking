package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/setactiveaccount")
public class SetActiveAccount extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		long acno= Long.parseLong(req.getParameter("acno"));
		
		req.getSession().setAttribute("acno", acno);// Here i can tell my active account has been set for further operation.
		req.getRequestDispatcher("Transactionpage.jsp").include(req, resp);
		
	}
	
	
}
