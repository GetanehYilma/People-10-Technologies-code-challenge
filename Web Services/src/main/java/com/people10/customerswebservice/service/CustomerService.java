package com.people10.customerswebservice.service;

import com.people10.customerswebservice.domain.Customer;

import java.util.List;

public interface CustomerService {

    public Customer saveCustomer(Customer customer);

    public List<Customer> getAllCustomers();

    public Customer updateCustomer(Customer customer);

    public Customer getCustomerById(long id);

    public String deleteCustomer(long id);

    public List<Customer> searchCustomer(String search);

}
