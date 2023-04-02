package com.yildiz.serhat.currentaccountservice;

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
