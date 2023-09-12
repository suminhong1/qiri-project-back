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
    private CommentsService comments;

    @GetMapping("/comments")
    public ResponseEntity<List<Comments>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(comments.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comments> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(comments.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("/comments")
    public ResponseEntity<Comments> create(@RequestBody Comments vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(comments.create(vo));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping("/comments")
    public ResponseEntity<Comments> update(@RequestBody Comments vo){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(comments.update(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Comments> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(comments.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
