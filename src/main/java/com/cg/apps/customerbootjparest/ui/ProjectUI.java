package com.cg.apps.customerbootjparest.ui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cg.apps.customerbootjparest.entities.Customer;
import com.cg.apps.customerbootjparest.exception.CustomerNotFoundException;
import com.cg.apps.customerbootjparest.service.*;

@Component
public class ProjectUI 
{		
		@Autowired
		private CustomerService customerService;

		public void runUI()
		{
			try
			{
			Customer customerObj1 = addObj("Harshit");
			Customer customerObj2 = addObj("Shivam");
			Customer customerObj3= addObj("Riya");
			
			System.out.println("Customer 1: "+customerObj1+"\nCustomer 2: "+customerObj2+"\nCustomer 3: "+customerObj3);

			Long idUpdate = customerObj1.getId();	
			customerObj1 = updateObj(idUpdate, "Naman");
			System.out.println("Customer 1: "+customerObj1);
			}
			catch(CustomerNotFoundException e)
			{
				System.out.println(e.getMessage());
			}
		}
		
		public Customer addObj(String name)
		{
			Customer customer = new Customer(name);
			customer = customerService.register(customer);
			return customer;
		}
		
		public Customer updateObj(Long id, String name)
		{
			return customerService.updateName(id, name);
		}
		
}

