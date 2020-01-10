package com.people10.customerswebservice.service.impl;

import com.people10.customerswebservice.domain.Customer;
import com.people10.customerswebservice.repository.CustomerRepository;
import com.people10.customerswebservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Customer customer) {

        return null;
    }

    @Override
    public Customer getCustomerById(long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public String deleteCustomer(long id) {
        Customer customer = getCustomerById(id);

        if (customer == null){
            return "Unable to delete. Customer is not found!";
        }

       customerRepository.delete(customer);
        return "Customer Successfully deleted!";
    }

    @Override
    public List<Customer> searchCustomer(String search) {
//        return customerRepository.findAllByFirstNameOrLastNameOrEmail(search, search, search);
        return customerRepository.searchCustomersTable(search);
    }

}
