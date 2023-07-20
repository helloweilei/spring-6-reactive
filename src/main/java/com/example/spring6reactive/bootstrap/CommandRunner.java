package com.example.spring6reactive.bootstrap;

import com.example.spring6reactive.domain.Beer;
import com.example.spring6reactive.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class CommandRunner implements CommandLineRunner {
    private final BeerRepository beerRepository;
    @Override
    public void run(String... args) throws Exception {
        Beer beer1 = Beer.builder()
                .quantityOnHand(100)
                .beerName("青岛啤酒")
                .upc("123")
                .beerStyle("Cold")
                .build();
        Beer beer2 = Beer.builder()
                .quantityOnHand(100)
                .beerName("汉斯啤酒")
                .upc("123")
                .beerStyle("Normal")
                .build();

        beerRepository.save(beer1).subscribe();
        beerRepository.save(beer2).subscribe();
    }
}
