package com.kh.elephant.controller;

import com.kh.elephant.domain.BanInfo;
import com.kh.elephant.service.BanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qiri/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class BanInfoController {

    @Autowired
    private BanInfoService service;
    
    // 이용 정지 당한 전체 유저 보기
    @GetMapping("/banInfo")
    public ResponseEntity<List<BanInfo>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 이용 정지 당한 특정 유저 보기
    @GetMapping("/banInfo/{id}")
    public ResponseEntity<BanInfo> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 이용 정지 유저 추가
    @PostMapping("/banInfo")
    public ResponseEntity<BanInfo> create(@RequestBody BanInfo banInfo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.create(banInfo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 이용 정지 유저 수정
    @PutMapping("/banInfo")
    public ResponseEntity<BanInfo> update(@RequestBody BanInfo banInfo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(banInfo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 이용 정지 유저 삭제(사용 안함) 이용 정지 기간을 정해두면 알아서 정지 후 풀릴수 있게
    @DeleteMapping("/banInfo/{id}")
    public ResponseEntity<BanInfo> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 이용 정지 사유 보기, 이용 정지 기간 보기
}
