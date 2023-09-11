package com.kh.elephant.controller;

import com.kh.elephant.domain.Board;
import com.kh.elephant.domain.CommentLike;
import com.kh.elephant.service.BoardService;
import com.kh.elephant.service.CommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qiri/*")
public class CommentLikeController {


    @Autowired
    private CommentLikeService service;

    @GetMapping("/commentLike")
    public ResponseEntity<List<CommentLike>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/commentLike/{id}")
    public ResponseEntity<CommentLike> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("/commentLike")
    public ResponseEntity<CommentLike> create(@RequestBody CommentLike commentLike) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.create(commentLike));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/commentLike")
    public ResponseEntity<CommentLike> update(@RequestBody CommentLike commentLike){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(commentLike));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/commentLike/{id}")
    public ResponseEntity<CommentLike> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
