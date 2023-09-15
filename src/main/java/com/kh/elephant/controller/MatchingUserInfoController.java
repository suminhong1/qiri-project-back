package com.kh.elephant.controller;

import com.kh.elephant.domain.MatchingUserInfo;
import com.kh.elephant.service.MatchingUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matching-user-info")
public class MatchingUserInfoController {

    @Autowired
    private MatchingUserInfoService service;

    @GetMapping("/matchingUserInfo")
    public ResponseEntity<List<MatchingUserInfo>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/matchingUserInfo/{id}")
    public ResponseEntity<MatchingUserInfo> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/MatchingUserInfo")
    public ResponseEntity<MatchingUserInfo> create(@RequestBody MatchingUserInfo matchingUserInfo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.create(matchingUserInfo));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/MatchingUserInfo")
    public ResponseEntity<MatchingUserInfo> update(@RequestBody MatchingUserInfo matchingUserInfo){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(matchingUserInfo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/MatchingUserInfo/{id}")
    public ResponseEntity<MatchingUserInfo> delete(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(Integer.parseInt(id)));
    }

}