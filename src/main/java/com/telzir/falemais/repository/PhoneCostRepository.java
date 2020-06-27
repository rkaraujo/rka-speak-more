package com.telzir.falemais.repository;

import com.telzir.falemais.model.PhoneCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneCostRepository extends JpaRepository<PhoneCost, Integer> {

    Optional<PhoneCost> findBySourceDDDAndDestinationDDD(Integer sourceDDD, Integer destinationDDD);
}
