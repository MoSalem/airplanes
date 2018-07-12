package com.capgemini.airplanes.controller.rest;


import com.capgemini.airplanes.Model.Airplane;
import com.capgemini.airplanes.Persistence.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/airplanes")
public class AirplaneController {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @GetMapping("{id}")
    public ResponseEntity<Airplane> findById(@PathVariable long id) {

        Optional<Airplane> result = (this.airplaneRepository.findById(id));

        if (result.isPresent()) {
            return new ResponseEntity<Airplane>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<Airplane>> get() {
        return new ResponseEntity<Iterable<Airplane>>(this.airplaneRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Airplane> create(@RequestBody Airplane newAirplane) {

        this.airplaneRepository.save(newAirplane);
        return new ResponseEntity<Airplane>(newAirplane, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Airplane> updateById(@PathVariable long id, @RequestBody Airplane update) {

        Optional<Airplane> possibleTarget = (this.airplaneRepository.findById(id));

        if (possibleTarget.isPresent()) {
            Airplane target = possibleTarget.get();

            target.setFuel(update.getFuel());
            target.setName(update.getName());

            airplaneRepository.save(target);
            return new ResponseEntity<Airplane>(this.airplaneRepository.save(target), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Airplane> deleteById(@PathVariable long id) {

        Optional<Airplane> result = (this.airplaneRepository.findById(id));

        if (result.isPresent()) {
            this.airplaneRepository.deleteById(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}