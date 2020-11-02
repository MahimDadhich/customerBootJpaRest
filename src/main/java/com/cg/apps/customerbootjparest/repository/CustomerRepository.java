package com.cg.apps.customerbootjparest.repository;

import com.cg.apps.customerbootjparest.entities.Customer;

public interface CustomerRepository 
{
	Customer addCustomer(Customer customer);
	Customer updateCustomer(Customer customer);
	Customer findById(Long id);
}
