package com.capgemini.airplanes.Persistence;

import com.capgemini.airplanes.Model.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends CrudRepository<Airport, Long> {

}

