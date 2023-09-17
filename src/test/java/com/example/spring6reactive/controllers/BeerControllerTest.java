package com.example.spring6reactive.controllers;

import com.example.spring6reactive.domain.Beer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BeerControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    @Order(1)
    void getAll() {
        webTestClient.get().uri("/beers")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody().jsonPath("$.size()").isEqualTo(2);
    }

    @Test
    @Order(3)
    void getBeerById() {
        webTestClient.get().uri("/beers/{beerId}", 1)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody(Beer.class);
    }

    @Test
    void getBeerByIdNotFound() {
        webTestClient.get().uri("/beers/{beerId}", 999)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Order(2)
    void createNewBeer() {
        webTestClient.post().uri("/beers")
                .body(Mono.just(this.getNewBeer()), Beer.class)
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().location("http://localhost:8080/beers/3");
    }

    @Test
    @Order(4)
    void updateBeer() {
        webTestClient.put().uri("/beers/{beerId}", 1)
                .body(Mono.just(getNewBeer()), Beer.class)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    @Order(5)
    void deleteBeerById() {
        webTestClient.delete().uri("/beers/{beerId}", 1)
                .exchange()
                .expectStatus().isNoContent();
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
