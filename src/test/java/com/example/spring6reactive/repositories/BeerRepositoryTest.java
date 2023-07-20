package com.example.spring6reactive.repositories;

import com.example.spring6reactive.config.DatabaseConfig;
import com.example.spring6reactive.domain.Beer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@DataR2dbcTest
@Import({DatabaseConfig.class})
class BeerRepositoryTest {
    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeer() {
        Beer beer = getNewBeer();
        beerRepository.save(beer).subscribe(newBeer -> {
            System.out.print(beer);
            Assertions.assertThat(beer.getId()).isNotNull();
        });
    }

    private Beer getNewBeer() {
        return Beer.builder()
                .beerName("Beer Name")
                .beerStyle("xxx")
                .upc("opc")
                .quantityOnHand(10)
                .build();
    }
}
