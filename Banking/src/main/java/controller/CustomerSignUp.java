package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import dto.Customer;
@WebServlet("/customersignup")
public class CustomerSignUp extends HttpServlet
{
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("custname");
		String mobile = req.getParameter("mobile");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		String dob = req.getParameter("dob");
		
		 long mob = Long.parseLong(mobile);
		
		// Way-2
		/*resp.getWriter().println("<h1>Customer Name: "+name
				+"<h1>Mobile: "+mobile
				+ "<h1>Password: "+ password
				+ "<h1>Email: "+ email
				+ "<h1>Gender: "+ gender
				+  "<h1>DOB: "+ dob
				+"<h1>");*/
		
		/*Way-1
//		resp.getWriter().println("<h1>Mobile: "+mobile);
//		resp.getWriter().println("<h1>Password: "+ password);
//		resp.getWriter().println("<h1>Email: "+ email);
//		resp.getWriter().println("<h1>Gender: "+ gender);
//		resp.getWriter().println("<h1>DOB: "+ dob);*/
		
		Date date = Date.valueOf(dob);			//convert the dob from string to date type
		System.out.println(date);
		Period period =Period.between(date.toLocalDate(), LocalDate.now());		//get the age from dob till now
		System.out.println(period);
		int age = period.getYears();		
	 	if(age <=18) {
			resp.getWriter().println("<h1>Not Eligible!!!!");
		}
	 	else
	 	{
//			resp.getWriter().println("<h1>You R Eligible!!!!");
			CustomerDao dao = new CustomerDao();
			List<Customer> cust1 = dao.check1(mob);
			List<Customer> cust2 = dao.check2(email);
			
			
				if(cust1.isEmpty() && cust2.isEmpty())
				{
					Customer customer = new Customer();
					customer.setName(name);
					customer.setPwd(password);
					customer.setGender(gender);
					customer.setMob(mob);
					customer.setEmail(email);
					customer.setDob(date);
					dao.save(customer);
//					resp.getWriter().println("<h1>Account has been created Scucesfully....<h1>");
					Customer customer2  = dao.check2(email).get(0);
					long id= customer2.getCustid();
					
//					if(customer2.getGender().equals("male"))
					if(customer2.getGender().equals("male")) {
						resp.getWriter().println("<h1>Hello Sir Account has been created Scucesfully....<h1>");
						resp.getWriter().println("<h1>Your Customer id is:"+id+ "<h1>");
						req.getRequestDispatcher("Home.html").include(req, resp);
						
					}
					else {
						resp.getWriter().println("<h1> Hello Madam Account has been created Scucesfully....<h1>");
						resp.getWriter().println("<h1>Your Customer id is:"+id+ "<h1>");
						req.getRequestDispatcher("Home.html").include(req, resp);
					}
				}
				else {
					resp.getWriter().println("<h1> "+name+" Account has already Created.....");
				}
		}
	}
}