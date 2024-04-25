package com.example.criteriaapi_v1.config;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.criteriaapi_v1.entities.Coffee;
import com.example.criteriaapi_v1.repositories.CoffeeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DatasourceConfig {
    // inject bởi RequiredArgsConstructor
    private final CoffeeRepository coffeeRepository;

    @PostConstruct
    public void init() {
        // Insert 100 Coffee vào H2 Database sau khi
        // DatasourceConfig được khởi tạo
        final Random random = new Random();
        coffeeRepository.saveAll(IntStream.range(0, 100)
                .mapToObj(i -> Coffee.builder()
                        .name("coffee-" + i)
                        .type(random.nextDouble() >= 0.5 ? Coffee.CoffeeType.HOT : Coffee.CoffeeType.ICE)
                        .build())
                .collect(Collectors.toList()));
    }
}
