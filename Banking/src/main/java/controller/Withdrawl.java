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

@WebServlet("/Withdraw")
public class Withdrawl extends HttpServlet{

	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		double amount = Double.parseDouble(req.getParameter("Wamount"));
		
		long acno= (long) req.getSession().getAttribute("acno");
		
		BankDao bankDao = new BankDao();
		
		BankAccount account= bankDao.fetch_by_accno(acno);
		
		if(account.getAmount()<amount) {		
			resp.getWriter().print("<h1>Insufficient Balance Your Actual Balance is :<h1>"+ account.getAmount());
		
		}else {
			
			if(amount>account.getAcc_limit()) {
				resp.getWriter().print("<h1>Your Account Limit is Exceeding. Your Actual Account Limit is : <h1>"+ account.getAcc_limit());
				req.getRequestDispatcher("Withdrawl.html").include(req, resp);
			}else {
				account.setAmount(account.getAmount()-amount);
				
				 BankTransactions bankTransactions = new BankTransactions();
				 
				 bankTransactions.setDeposit(0);
				 bankTransactions.setWithdraw(amount);
				 bankTransactions.setBalance(account.getAmount());
				 bankTransactions.setLocalDateTime(LocalDateTime.now());
				 
				 List<BankTransactions> list = account.getBankTransactions(); //Older Transaction History
				 list.add(bankTransactions);  //Inside this list now we are having Older transaction history + current transaction history
				 
				
				bankDao.update(account);
				
				resp.getWriter().print("<h1>Amount Withdrawn Successfull.<h1>");
				req.getRequestDispatcher("Transactionpage.jsp").include(req, resp);
			}
		}
	}
}
