package com.yildiz.serhat.currentaccountservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yildiz.serhat.currentaccountservice.controller.request.AccountCreateRequestDTO;
import com.yildiz.serhat.currentaccountservice.domain.entity.Account;
import com.yildiz.serhat.currentaccountservice.domain.entity.Customer;
import com.yildiz.serhat.currentaccountservice.repository.AccountRepository;
import com.yildiz.serhat.currentaccountservice.repository.CustomerRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void setup() {
        customerRepository.save(Customer.builder().id(1L).firstName("Serhat").lastName("Yildiz").build());
    }


    @AfterEach
    public void tearDown() {
        customerRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    @SneakyThrows
    void shouldNotCreateAccountIfCustomerNotFound() {
        AccountCreateRequestDTO createRequestDTO = new AccountCreateRequestDTO(3L, TEN);
        mockMvc.perform(post("/v1/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequestDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    void shouldCreateAccount() {
        AccountCreateRequestDTO createRequestDTO = new AccountCreateRequestDTO(1L, valueOf(55));

        mockMvc.perform(post("/v1/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequestDTO)))
                .andExpect(status().isCreated());

        List<Account> all = accountRepository.findAll();

        assertEquals(all.get(0).getBalance().setScale(0), valueOf(55));
        assertEquals(all.get(0).getCustomer().getId(), 1L);
    }

    @Test
    @SneakyThrows
    void shouldNotCreateAccountIfInitialCreditIsNegativeOrNonDigit() {
        AccountCreateRequestDTO createRequestDTO = new AccountCreateRequestDTO(1L, valueOf(-1));

        mockMvc.perform(post("/v1/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequestDTO)))
                .andExpect(status().isBadRequest());

        List<Account> all = accountRepository.findAll();

        assertEquals(all.size(), 2);
    }

    @Test
    @SneakyThrows
    void shouldNotCreateAccountIfCustomerIdNull() {
        AccountCreateRequestDTO createRequestDTO = new AccountCreateRequestDTO(null, valueOf(1));

        mockMvc.perform(post("/v1/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequestDTO)))
                .andExpect(status().isBadRequest());

        List<Account> all = accountRepository.findAll();

        assertEquals(all.size(), 2);
    }
}