package com.yildiz.serhat.currentaccountservice.service.impl;

import com.yildiz.serhat.currentaccountservice.domain.entity.Customer;
import com.yildiz.serhat.currentaccountservice.domain.model.CustomerDetails;
import com.yildiz.serhat.currentaccountservice.exception.CustomerNotFoundException;
import com.yildiz.serhat.currentaccountservice.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository repository;

    @Test
    void shouldGetCustomerById() {
        Customer customer = Customer.builder().id(1L).firstName("Serhat").lastName("Yildiz").build();

        when(repository.findById(1L)).thenReturn(Optional.of(customer));

        CustomerDetails customerDetails = customerService.getCustomerDetails(1L);

        assertEquals(customerDetails.getFirstName(), "Serhat");
        assertEquals(customerDetails.getLastName(), "Yildiz");
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerDetails(1L));
    }

    @Test
    void shouldGetAllCustomers() {
        Customer customer = Customer.builder().id(1L).firstName("Serhat").lastName("Yildiz").build();

        when(repository.findAll()).thenReturn(List.of(customer));

        List<Customer> allCustomers = customerService.findAllCustomers();

        assertEquals(allCustomers.size(), 1);
        assertEquals(allCustomers.get(0).getFirstName(), "Serhat");
        assertEquals(allCustomers.get(0).getLastName(), "Yildiz");
    }

    @Test
    void shouldGetAllCustomerDetails() {
        Customer customer = Customer.builder().id(1L).firstName("Serhat").lastName("Yildiz").build();

        when(repository.findById(1L)).thenReturn(Optional.of(customer));

        CustomerDetails customerDetails = customerService.getCustomerDetails(1L);

        assertEquals(customerDetails.getFirstName(), "Serhat");
        assertEquals(customerDetails.getLastName(), "Yildiz");
    }

}