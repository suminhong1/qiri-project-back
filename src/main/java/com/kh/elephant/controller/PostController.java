package com.kh.elephant.controller;

import com.kh.elephant.domain.Post;
import com.kh.elephant.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/*")
public class PostController {

    @Autowired
    private PostService service;

    @GetMapping("/post")
    public ResponseEntity<List<Post>> shwoAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/post/{id}")
    public ResponseEntity<Post> show(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("/post")
    public ResponseEntity<Post> insert(@RequestBody Post post){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.create(post));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping("/post")
    public ResponseEntity<Post> update(@RequestBody Post post){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.update(post));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("/post/{id}")
    public ResponseEntity<Post> delete(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
