package com.people10.customerswebservice.controller;

import com.people10.customerswebservice.domain.Customer;
import com.people10.customerswebservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/")
    public Customer saveCustomer(@RequestBody Customer customer){

        return customerService.saveCustomer(customer);
    }
    @GetMapping("/")
    public List<Customer> getCustomers(){
        return customerService.getAllCustomers();
    }

    @PutMapping("/")
    public Customer updateCustomer(@RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable long id){
        return customerService.deleteCustomer(id);
    }

    @GetMapping("/{search}")
    public List<Customer> searchCustomer(@PathVariable String search){

        return customerService.searchCustomer(search);
    }
}
