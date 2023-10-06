package com.kh.elephant.controller;
import com.kh.elephant.domain.Matching;
import com.kh.elephant.service.MatchingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/matching/*")

public class MatchingController {
    @Autowired
    private MatchingService service;

    @GetMapping("/showAll")
    public ResponseEntity<List<Matching>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matching> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Matching> create(@RequestBody Matching matching) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.create(matching));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Matching> update(@RequestBody Matching matching) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(matching));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Matching> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
// 매칭 취소
    // 매칭 취소 사유
    // 매칭 자주 취소하는 유저 밴
}