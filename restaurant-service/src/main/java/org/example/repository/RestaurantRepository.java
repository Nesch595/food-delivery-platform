package org.example.repository;

import org.example.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByName(String name);
    Optional<Restaurant> findByCuisine(String cuisine);
    Optional<Restaurant> findByAddress(String address);

    boolean existsByName(String name);
    boolean existsByAddress(String address);
    boolean existsByCuisine(String cuisine);
}
