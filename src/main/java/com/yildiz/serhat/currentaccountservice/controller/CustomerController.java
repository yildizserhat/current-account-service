package com.yildiz.serhat.currentaccountservice.controller;


import com.yildiz.serhat.currentaccountservice.domain.entity.Customer;
import com.yildiz.serhat.currentaccountservice.domain.model.CustomerDetails;
import com.yildiz.serhat.currentaccountservice.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/customers")
@RequiredArgsConstructor
@Tag(name = "Customer", description = "Endpoints about customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    @Operation(summary = "Get Customer Details By id")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

}
