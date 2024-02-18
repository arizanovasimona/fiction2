package com.bankAccount2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class BankAccountBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountBasicApplication.class, args);
    }
}