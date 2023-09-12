package com.kh.elephant.controller;

import com.kh.elephant.domain.PostThema;
import com.kh.elephant.service.PostThemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qiri/*")
public class PostThemaController {

    @Autowired
    private PostThemaService postThema;

    @GetMapping("/postThema")
    public ResponseEntity<List<PostThema>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(postThema.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/postThema/{id}")
    public ResponseEntity<PostThema> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(postThema.show(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("/postThema")
    public ResponseEntity<PostThema> create(@RequestBody PostThema vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(postThema.create(vo));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/postThema")
    public ResponseEntity<PostThema> update(@RequestBody PostThema vo){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(postThema.update(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/postThema/{id}")
    public ResponseEntity<PostThema> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(postThema.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
