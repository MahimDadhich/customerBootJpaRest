package com.cg.apps.customerbootjparest.repository;

import java.util.Map;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.cg.apps.customerbootjparest.entities.Customer;
import com.cg.apps.customerbootjparest.exception.CustomerNotFoundException;


@Repository
public class CustomerRepositoryImpl implements CustomerRepository 
{
	private static final Logger Log= LoggerFactory.getLogger(CustomerRepositoryImpl.class);
	
	@PersistenceContext
    private EntityManager entityManager;

	
	@Override
	public Customer addCustomer(Customer customer) {
		entityManager.persist(customer);		
		return customer;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		 boolean success=checkExists(customer.getId());
	        if(!success)
	        {
	        	throw new CustomerNotFoundException("Can't update, Customer doesn't exist for id="+customer.getId());

	        }
	        customer = entityManager.merge(customer);
	        return customer;
		}
	
	public boolean checkExists(Long id){
        Customer customer = entityManager.find(Customer.class, id);
        boolean result= customer!=null;
        return result;
    }
	@Override
	public Customer findById(Long id) {
		Customer customer = entityManager.find(Customer.class, id);
        if (customer == null) {
            throw new  CustomerNotFoundException("Customer not found for id=" + id);
        }
        return customer;
	}
}
