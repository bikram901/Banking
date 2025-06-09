package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BankDao;
import dto.BankAccount;
import dto.BankTransactions;
@WebServlet("/deposit")
public class Deposit extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		double amount= Double.parseDouble(req.getParameter("amount"));
		
		long acno = (long) req.getSession().getAttribute("acno");
		
		BankDao bankDao = new BankDao();
		
		BankAccount account = bankDao.fetch_by_accno(acno);
		
		 account.setAmount(account.getAmount()+amount); //before putting any data inside db we should set the data
		 

		 
		 BankTransactions bankTransactions = new BankTransactions();
		 
		 bankTransactions.setDeposit(amount);
		 bankTransactions.setWithdraw(0);
		 bankTransactions.setBalance(account.getAmount());
		 bankTransactions.setLocalDateTime(LocalDateTime.now());
		 
		 List<BankTransactions> list = account.getBankTransactions(); //Older Transaction History
		 list.add(bankTransactions);  //Inside this list now we are having Older transaction history + current transaction history
		 
		 
		 bankDao.update(account);
		 resp.getWriter().print("<h1>Amount has been added successfully<h1>");
		req.getRequestDispatcher("Transactionpage.jsp").include(req, resp);
	}

	
}
