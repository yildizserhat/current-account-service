package com.yildiz.serhat.currentaccountservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yildiz.serhat.currentaccountservice.domain.entity.Customer;
import com.yildiz.serhat.currentaccountservice.repository.CustomerRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;


    @AfterEach
    public void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    @SneakyThrows
    void shouldGetCustomerById() {
        customerRepository.save(Customer.builder().id(1L).firstName("Serhat").lastName("Yildiz").build());

        mockMvc.perform(get("/v1/api/customers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").exists())
                .andExpect(jsonPath("$.lastName").exists())
                .andExpect(jsonPath("$.firstName").value("Serhat"))
                .andExpect(jsonPath("$.lastName").value("Yildiz"));
    }

    @Test
    @SneakyThrows
    void shouldReturnNotFoundIfCustomerNotFound() {
        mockMvc.perform(get("/v1/api/customers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}