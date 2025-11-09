package org.example.repository;

import org.example.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Long> {
    Optional<Dish> findByName(String name);
    Optional<Dish> findByPrice(int price);

    boolean existsByName(String name);
    boolean existsByPrice(int price);
}
