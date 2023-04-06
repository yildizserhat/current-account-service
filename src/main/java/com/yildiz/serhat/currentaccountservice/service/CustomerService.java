package com.yildiz.serhat.currentaccountservice.service;

import com.yildiz.serhat.currentaccountservice.domain.entity.Customer;
import com.yildiz.serhat.currentaccountservice.domain.model.CustomerDetails;

import java.util.List;

public interface CustomerService {

    Customer getCustomerById(Long id);

    List<Customer> findAllCustomers();

    CustomerDetails getCustomerDetails(Long id);
}
