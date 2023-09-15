package com.kh.elephant.controller;

import com.kh.elephant.domain.BlockUsers;
import com.kh.elephant.domain.Category;
import com.kh.elephant.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/category")
    public ResponseEntity<List<Category>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/category")
    public ResponseEntity<Category> create(@RequestBody Category vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.create(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/category")
    public ResponseEntity<Category> update(@RequestBody Category vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Category> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    // 카테고리 찾기
}
