package com.yildiz.serhat.currentaccountservice.service.impl;

import com.yildiz.serhat.currentaccountservice.domain.entity.Customer;
import com.yildiz.serhat.currentaccountservice.domain.model.CustomerDetails;
import com.yildiz.serhat.currentaccountservice.exception.CustomerNotFoundException;
import com.yildiz.serhat.currentaccountservice.repository.CustomerRepository;
import com.yildiz.serhat.currentaccountservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;

    @Override
    public Customer getCustomerById(Long id) {
        log.info("Customer is received for id:{}", id);
        return repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with id {%s} does not exist", id)));
    }

    @Override
    public List<Customer> findAllCustomers() {
        log.info("Customers are received");
        return repository.findAll();
    }

    @Override
    public CustomerDetails getCustomerDetails(Long id) {
        Customer customer = getCustomerById(id);
        return CustomerDetails.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .accounts(customer.getAccounts())
                .build();
    }
}
