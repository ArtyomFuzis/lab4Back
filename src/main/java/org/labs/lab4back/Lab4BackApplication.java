package org.labs.lab4back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
public class Lab4BackApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab4BackApplication.class, args);
    }
}
