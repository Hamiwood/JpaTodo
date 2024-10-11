package com.sparta.jpatodoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JpaTodoProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaTodoProjectApplication.class, args);
    }

}
