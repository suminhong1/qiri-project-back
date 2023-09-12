package com.kh.elephant.controller;

import com.kh.elephant.domain.Board;
import com.kh.elephant.domain.UserLike;
import com.kh.elephant.service.BoardService;
import com.kh.elephant.service.UserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qiri/*")
public class UserLikeController {

    @Autowired
    private UserLikeService userLike;

    @GetMapping("/userLike")
    public ResponseEntity<List<UserLike>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userLike.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/userLike/{id}")
    public ResponseEntity<UserLike> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userLike.show(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("/userLike")
    public ResponseEntity<UserLike> create(@RequestBody UserLike vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userLike.create(vo));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/userLike")
    public ResponseEntity<UserLike> update(@RequestBody UserLike vo){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userLike.update(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/userLike/{id}")
    public ResponseEntity<UserLike> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userLike.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
