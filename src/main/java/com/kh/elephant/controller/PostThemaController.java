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
    private PostThemaService service;

    @GetMapping("/postThema")
    public ResponseEntity<List<PostThema>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/postThema/{id}")
    public ResponseEntity<PostThema> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("/postThema")
    public ResponseEntity<PostThema> create(@RequestBody PostThema postThema) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.create(postThema));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/postThema")
    public ResponseEntity<PostThema> update(@RequestBody PostThema postThema){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(postThema));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/postThema/{id}")
    public ResponseEntity<PostThema> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
