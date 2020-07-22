package com.salman.salapp.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.salman.salapp")
@EntityScan("com.salman.salapp.library.entity")
public class SalAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalAppApplication.class, args);
    }
}
