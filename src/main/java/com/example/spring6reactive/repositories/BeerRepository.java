package com.example.spring6reactive.repositories;

import com.example.spring6reactive.domain.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer> {
}
