package com.kh.elephant.controller;

import com.kh.elephant.domain.CategoryType;

import com.kh.elephant.service.CategoryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryTypeController {

    @Autowired
    private CategoryTypeService service;

    @GetMapping("/")
    public ResponseEntity<List<CategoryType>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /*
    @GetMapping("/categoryType/{id}")
    public ResponseEntity<CategoryType> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/categoryType")
    public ResponseEntity<CategoryType> create(@RequestBody CategoryType vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.create(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/categoryType")
    public ResponseEntity<CategoryType> update(@RequestBody CategoryType vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/categoryType/{id}")
    public ResponseEntity<CategoryType> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    */



}
