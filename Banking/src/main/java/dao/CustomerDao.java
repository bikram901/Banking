package dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Customer;

public class CustomerDao {
	
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dev");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();
	
	public void save(Customer customer) {
		entityTransaction.begin();
		entityManager.persist(customer);
		entityTransaction.commit();
	}

	public List<Customer> check1(long mobile) {
		List<Customer> list1= entityManager.createQuery("select x from Customer x where mob=?1").setParameter(1, mobile).getResultList();
		return list1;
	}
	
	public List<Customer> check2(String email) {
		List<Customer> list2= entityManager.createQuery("select x from Customer x where email=?1").setParameter(1, email).getResultList();
		return list2;
	}
	
	public Customer fetch(long custid)
	{
		Customer customer= entityManager.find(Customer.class, custid);
		return customer;	
	}

	public void update(Customer curr_customer) {
		entityTransaction.begin();
		entityManager.merge(curr_customer);
		entityTransaction.commit();
		
	}
	

}
