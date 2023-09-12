package com.kh.elephant.controller;


import com.kh.elephant.domain.ChatUserInfo;
import com.kh.elephant.service.ChatUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qiri/*")
public class ChatUserInfoController {

    @Autowired
    private ChatUserInfoService ChatUserInfo;

    @GetMapping("/chatUserInfo")
    public ResponseEntity<List<ChatUserInfo>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ChatUserInfo.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/chatUserInfo/{id}")
    public ResponseEntity<ChatUserInfo> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ChatUserInfo.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("/chatUserInfo")
    public ResponseEntity<ChatUserInfo> create(@RequestBody ChatUserInfo vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ChatUserInfo.create(vo));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping("/chatUserInfo")
    public ResponseEntity<ChatUserInfo> update(@RequestBody ChatUserInfo vo){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ChatUserInfo.update(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("/chatUserInfo/{id}")
    public ResponseEntity<ChatUserInfo> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ChatUserInfo.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
