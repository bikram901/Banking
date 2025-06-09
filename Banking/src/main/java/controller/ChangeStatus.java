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

@WebServlet("/changestatus")
public class ChangeStatus extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		long acn= Long.parseLong(req.getParameter("acno"));
		
		BankDao bankDao= new BankDao();
		
		BankAccount account=bankDao.fetch_by_accno(acn);
		
		if(account.isStatus())
		{
			account.setStatus(false);
		}
		else {
			account.setStatus(true);
		}
		
		bankDao.update(account);
		List<BankAccount> list= bankDao.fetch_all_bank_details();
		req.getSession().setAttribute("list", list);
		req.getRequestDispatcher("Account_Home.jsp").include(req, resp);
		resp.getWriter().print("<h1>Bank Status has been Updated<h1>");
	}
}
