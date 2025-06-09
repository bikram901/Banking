package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import dto.Customer;

@WebServlet("/customerlogin")
public class CustomerLogin extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		long custid = Long.parseLong(req.getParameter("custid"));
		
		String pwd = req.getParameter("pwd");
	
		CustomerDao customerDao = new CustomerDao();
		
		Customer customer = customerDao.fetch(custid);
		
		if(customer==null)
		{
			resp.getWriter().print("<h1>You Enterred Invalid custID");
			req.getRequestDispatcher("CustLogin.html").include(req, resp);
		}
		else {
			
			if(customer.getPwd().equals(pwd)) {
				resp.getWriter().print("<h1>Login Success<h1>");
				req.getSession().setAttribute("customer", customer);
				req.getRequestDispatcher("customerhome.html").include(req, resp);
			}
			else {
				resp.getWriter().print("<h1>You Enterred Invalid Pwd");
				req.getRequestDispatcher("CustLogin.html").include(req, resp);
			}
		}
		
	}

}
