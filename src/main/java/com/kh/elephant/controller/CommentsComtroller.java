package com.kh.elephant.controller;

import com.kh.elephant.domain.Comments;
import com.kh.elephant.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qiri/*")
public class CommentsComtroller {


    @Autowired
    private CommentsService service;

    @GetMapping("/Comments")
    public ResponseEntity<List<Comments>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comments> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("/comments")
    public ResponseEntity<Comments> create(@RequestBody Comments comments) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.create(comments));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping("/comments")
    public ResponseEntity<Comments> update(@RequestBody Comments comments){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(comments));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Comments> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
