package com.capgemini.airplanes.controller.rest;
import com.capgemini.airplanes.Model.Airport;
import com.capgemini.airplanes.Persistence.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/airports")
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

    @GetMapping
    public ResponseEntity<Iterable<Airport>> list() {
        return new ResponseEntity<Iterable<Airport>>(this.airportRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Airport> create(@RequestBody Airport newAirport) {

        this.airportRepository.save(newAirport);
        return new ResponseEntity<Airport>(newAirport, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Airport> deleteById(@PathVariable long id) {

        Optional<Airport> result = (this.airportRepository.findById(id));

        if (result.isPresent()) {
            this.airportRepository.deleteById(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}