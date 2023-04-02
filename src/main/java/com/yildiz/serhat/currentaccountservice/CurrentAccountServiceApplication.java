package com.yildiz.serhat.currentaccountservice;

import com.yildiz.serhat.currentaccountservice.repository.AccountRepository;
import com.yildiz.serhat.currentaccountservice.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class CurrentAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrentAccountServiceApplication.class, args);
    }

}
