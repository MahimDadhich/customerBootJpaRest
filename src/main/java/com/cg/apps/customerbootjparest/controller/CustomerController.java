package com.cg.apps.customerbootjparest.controller;

import com.cg.apps.customerbootjparest.dto.CreateCustomerRequest;
import com.cg.apps.customerbootjparest.dto.CustomerDetails;
import com.cg.apps.customerbootjparest.dto.UpdateCustomerRequest;
import com.cg.apps.customerbootjparest.entities.Customer;
import com.cg.apps.customerbootjparest.exception.CustomerNotFoundException;
import com.cg.apps.customerbootjparest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//import java.util.stream.Collectors;

@RequestMapping("/customers")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public CustomerDetails add(@RequestBody CreateCustomerRequest requestData) {
        Customer customer = new Customer(requestData.getName());
        customer= service.register(customer);
        CustomerDetails details = toDetails(customer);
        return details;
    }

    @PutMapping("/update")
    public CustomerDetails update(@RequestBody UpdateCustomerRequest requestData) {
        Customer customer = service.updateName(requestData.getId(),requestData.getName());
        CustomerDetails details = toDetails(customer);
        return details;
    }


    @GetMapping("/get/{id}")
    public CustomerDetails fetchCustomer(@PathVariable("id") Long id) {
        Customer customer = service.findById(id);
        CustomerDetails details = toDetails(customer);
        return details;
    }

  /*  @GetMapping
    public List<CustomerDetails> fetchAll() {
        List<Customer> customer= service.findAll();
        // List<StudentDetails>response= students.stream().map(student->toDetails(student)).collect(Collectors.toList());
        List<StudentDetails> response = toDetails(students);
        return response;
    }*/


    /*@DeleteMapping("/remove/{id}")
    public String removeStudent(@PathVariable("id") Integer id) {
        service.deleteById(id);
        String response = "removed student with id=" + id;
        return response;
    }*/


    public List<CustomerDetails> toDetails(Collection<Customer> customers) {
        List<CustomerDetails> desired = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDetails details = toDetails(customer);
            desired.add(details);
        }
        return desired;
    }

    public CustomerDetails toDetails(Customer customer) 
    {
        CustomerDetails details = new CustomerDetails(customer.getId(), customer.getName());
        return details;

    }


}