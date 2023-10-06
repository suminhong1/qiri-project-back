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
@CrossOrigin(origins = {"*"}, maxAge = 6000)

public class BoardController {

    @Autowired
    private BoardService board;

    // 게시판 종류 전체 보기
    @GetMapping("/board")
    public ResponseEntity<List<Board>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(board.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 게시판 하나 클릭 상세 보기
    @GetMapping("/board/{id}")
    public ResponseEntity<Board> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(board.show(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    // 게시판 생성
    @PostMapping("/board")
    public ResponseEntity<Board> create(@RequestBody Board vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(board.create(vo));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    // 게시판 수정
    @PutMapping("/board")
    public ResponseEntity<Board> update(@RequestBody Board vo){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(board.update(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 게시판 삭제
    @DeleteMapping("/board/{id}")
    public ResponseEntity<Board> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(board.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
