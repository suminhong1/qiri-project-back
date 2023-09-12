package com.kh.elephant.controller;

import com.kh.elephant.domain.BanInfo;
import com.kh.elephant.service.BanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BanInfoController {

    @Autowired
    private BanInfoService service;

    @GetMapping("/banInfo")
    public ResponseEntity<List<BanInfo>> showAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
    }

    @GetMapping("/banInfo/{id}")
    public ResponseEntity<BanInfo> show(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
    }

    @PostMapping("/banInfo")
    public ResponseEntity<BanInfo> create(@RequestBody BanInfo banInfo) {
        return ResponseEntity.status(HttpStatus.OK).body(service.create(banInfo));
    }

    @PutMapping("/banInfo")
    public ResponseEntity<BanInfo> update(@RequestBody BanInfo banInfo) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(banInfo));
    }

    @DeleteMapping("/banInfo/{id}")
    public ResponseEntity<BanInfo> delete(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }
}
