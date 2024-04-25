package com.example.criteriaapi_v1;

import com.example.criteriaapi_v1.entities.Coffee;
import com.example.criteriaapi_v1.repositories.CoffeeRepository;
import com.example.criteriaapi_v1.repositories.CustomCoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class CriteriaApiV1Application {

    public static void main(String[] args) {
        SpringApplication.run(CriteriaApiV1Application.class, args);
    }

    private final CoffeeRepository coffeeRepository;
    private final CustomCoffeeRepository coffeeCustomRepository;

    @Bean
    CommandLineRunner init() {
        return args -> {
            // Get Coffee By ID
            System.out.println(coffeeCustomRepository.getCoffeeById(5));
            System.out.println("======================================");

            // Get Coffee By Name like and Type Like
            System.out.println(coffeeCustomRepository.getCoffeeByComplexConditions("coffee-%", Coffee.CoffeeType.HOT));
            System.out.println("======================================");
        };
    }
}
