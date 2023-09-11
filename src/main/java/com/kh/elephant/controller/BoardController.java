package com.kh.elephant.controller;

import com.kh.elephant.domain.Board;
import com.kh.elephant.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qiri/*")
public class BoardController {

    @Autowired
    private BoardService service;

    @GetMapping("/board")
    public ResponseEntity<List<Board>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<Board> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("/board")
    public ResponseEntity<Board> create(@RequestBody Board board) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.create(board));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/board")
    public ResponseEntity<Board> update(@RequestBody Board board){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(board));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity<Board> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
