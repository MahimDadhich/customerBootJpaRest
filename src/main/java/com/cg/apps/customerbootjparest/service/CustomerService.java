package com.cg.apps.customerbootjparest.service;

import com.cg.apps.customerbootjparest.entities.Customer;

public interface CustomerService 
{
	Customer register(Customer customer);
	Customer updateName(Long id, String name);
	Customer findById(Long id);
}
