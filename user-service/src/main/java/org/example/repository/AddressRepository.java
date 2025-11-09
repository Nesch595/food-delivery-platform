package org.example.repository;

import org.example.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByState(String state);
    @Query("SELECT a FROM address a WHERE a.street = :street AND a.city = :city AND a.zip = :zip AND a.state = :state AND a.country = :country")
    Optional<Address> findByFull(@Param("street") String street,
                                 @Param("city") String city,
                                 @Param("zip") String zip,
                                 @Param("state") String state,
                                 @Param("country") String country);
    boolean existsByState(String state);
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM address a WHERE " +
            "a.street = :street AND a.city = :city AND a.zip = :zip AND " +
            "a.state = :state AND a.country = :country")
    boolean existsByFull(@Param("street") String street,
                         @Param("city") String city,
                         @Param("zip") String zip,
                         @Param("state") String state,
                         @Param("country") String country);}
