package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BankDao;
import dto.BankAccount;

@WebServlet("/adminlogin")
public class AdminLogin extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		
		String email = req.getParameter("adminemail");
		
		String password = req.getParameter("adminpassword");
		
		if(email.equals("admin@gmail.com") && password.equals("admin")){
			resp.getWriter().print("<h1> Admin Login Success<h1>");
			
			BankDao bankDao= new BankDao();
			
			List<BankAccount> list= bankDao.fetch_all_bank_details();
			req.getSession().setAttribute("list", list);
			
			req.getRequestDispatcher("Account_Home.jsp").include(req, resp);

		}
		else {
			resp.getWriter().print("<h1>Admin Credential Mismatched</h1>");
			req.getRequestDispatcher("admin.html").include(req, resp);
		}
		
	}

}
