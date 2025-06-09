package dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class BankAccount {
	
	@Id
	@SequenceGenerator(initialValue = 300091100, allocationSize = 1 , sequenceName = "accno" , name = "accno")
	@GeneratedValue(generator = "accno")
	
	long accno;
	
	String account_type;
	
	double amount;
	
	double acc_limit;
	
	boolean status;
	
	@ManyToOne //Bank should have many details(i.e savings and current) of  one customer information 
	Customer customer;
	
	@OneToMany (cascade = CascadeType.ALL)  // Directly we can save the data in db without et.begin(),em.persist(), et.commit().
	List<BankTransactions> bankTransactions;

	public long getAccno() {
		return accno;
	}

	public void setAccno(long accno) {
		this.accno = accno;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAcc_limit() {
		return acc_limit;
	}

	public void setAcc_limit(double acc_limit) {
		this.acc_limit = acc_limit;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<BankTransactions> getBankTransactions() {
		return bankTransactions;
	}

	public void setBankTransactions(List<BankTransactions> bankTransactions) {
		this.bankTransactions = bankTransactions;
	}



	
	
	
	

}
