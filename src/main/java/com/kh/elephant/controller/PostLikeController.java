package com.kh.elephant.controller;

import com.kh.elephant.domain.PostLike;
import com.kh.elephant.service.PostLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/*")
public class PostLikeController {

    @Autowired
    private PostLikeService service;

    @GetMapping("/postLike")
    public ResponseEntity<List<PostLike>> showAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/postLike/{id}")
    public ResponseEntity<PostLike> show(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/postLike")
    public ResponseEntity<PostLike> insert(@RequestBody PostLike vo){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(service.create(vo));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping("/postLike")
    public ResponseEntity<PostLike> update(@RequestBody PostLike vo){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.update(vo));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("/postLike{id}")
    public ResponseEntity<PostLike> delete(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
