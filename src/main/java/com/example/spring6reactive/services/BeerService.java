package com.example.spring6reactive.services;

import com.example.spring6reactive.domain.Beer;
import com.example.spring6reactive.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BeerService {
    private final BeerRepository beerRepository;

    public Flux<Beer> listBeers() {
        return this.beerRepository.findAll();
    }

    public Mono<Beer> getById(Integer beerId) {
        return this.beerRepository.findById(beerId);
    }

    public Mono<Beer> createBeer(Beer beer) {
        return this.beerRepository.save(beer);
    }

    public Mono<Beer> updateBeer(Integer beerId, Beer beer) {
        return this.getById(beerId).map(foundBeer -> {
            foundBeer.setBeerName(beer.getBeerName());
            foundBeer.setBeerStyle(beer.getBeerStyle());
            return foundBeer;
        }).flatMap(this.beerRepository::save);

    }

    public Mono<Void> deleteById(Integer beerId) {
        return this.beerRepository.deleteById(beerId);
    }
}
