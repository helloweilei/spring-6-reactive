package com.example.spring6reactive.controllers;


import com.example.spring6reactive.domain.Beer;
import com.example.spring6reactive.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BeerController {
    private final BeerService beerService;

    @GetMapping("/beers")
    public Flux<Beer> getAll() {
        return this.beerService.listBeers();
    }

    @GetMapping("/beers/{beerId}")
    public Mono<Beer> getBeerById(@PathVariable("beerId") Integer beerId) {
        return this.beerService.getById(beerId);
    }
    @PostMapping("/beers")
    public Mono<ResponseEntity<Beer>> createNewBeer(@RequestBody Beer beer) {
        return beerService.createBeer(beer).map(newBeer -> ResponseEntity.created(
                UriComponentsBuilder.fromHttpUrl("http://localhost:8080/beers/" + newBeer.getId())
                        .build().toUri()
        ).build());
    }
}
