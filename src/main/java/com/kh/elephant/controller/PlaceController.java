package com.kh.elephant.controller;

import com.kh.elephant.domain.Place;
import com.kh.elephant.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    private PlaceService service;

    @GetMapping("/place")
    public ResponseEntity<List<Place>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/place")
    public ResponseEntity<Place> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/place/{placeSeq}")
    public ResponseEntity<Place> create(@RequestBody Place place) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.create(place));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/place")
    public ResponseEntity<Place> update(@RequestBody Place place){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(place));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/place/{placeSeq}")
    public ResponseEntity<Place> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}