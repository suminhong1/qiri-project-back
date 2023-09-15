package com.kh.elephant.controller;

import com.kh.elephant.domain.ChatInfo;
import com.kh.elephant.service.ChatInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qiri/*")
public class ChatInfoController {


    @Autowired
    private ChatInfoService chatInfo;

    
    @GetMapping("/chatInfo")
    public ResponseEntity<List<ChatInfo>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(chatInfo.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    
    @GetMapping("/chatInfo/{id}")
    public ResponseEntity<ChatInfo> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(chatInfo.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    // 채팅 치기
    @PostMapping("/chatInfo")
    public ResponseEntity<ChatInfo> create(@RequestBody ChatInfo vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(chatInfo.create(vo));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping("/chatInfo")
    public ResponseEntity<ChatInfo> update(@RequestBody ChatInfo vo){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(chatInfo.update(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("/chatInfo/{id}")
    public ResponseEntity<ChatInfo> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(chatInfo.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 채팅 관련 컨트롤러는 이쪽에서 한번에 처리

    // 특정 채팅방 전체 채팅 보기

    // 내 채팅방 목록 보기
    
    // 채팅 미리 보기


}