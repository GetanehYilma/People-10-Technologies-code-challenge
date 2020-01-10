package com.people10.datauploader.processor;

import com.people10.datauploader.domain.Customer;
import org.springframework.batch.item.ItemProcessor;

import java.sql.Date;
import java.time.LocalDate;

/* This is for preprocessing the data, do some business logic if we want before saving to our customers datbase */

public class CustomerItemProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(final Customer customer) throws Exception {

        return customer;
    }

}