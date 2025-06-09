package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.BankAccount;

public class BankDao {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dev");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();
	
	public void save(BankAccount bankaccount) {
		entityTransaction.begin();
		entityManager.persist(bankaccount);
		entityTransaction.commit();
	}
	
	public List<BankAccount> fetch_all_bank_details() {
		
		List<BankAccount> list = entityManager.createQuery("select x from BankAccount x").getResultList();
		return list;
	}
	
	public BankAccount fetch_by_accno(long acno) {
		
		BankAccount account=entityManager.find(BankAccount.class, acno);
		return account;
	}
	
	public void update(BankAccount account) {
		entityTransaction.begin();
		entityManager.merge(account);
		entityTransaction.commit();
	}
}
