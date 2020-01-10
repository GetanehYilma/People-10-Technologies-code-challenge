package com.people10.customerswebservice.repository;

import com.people10.customerswebservice.domain.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.firstName like ?1% or c.lastName like ?1% or c.email like ?1%")
    List<Customer> searchCustomersTable(String search);
}
