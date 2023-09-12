package com.kh.elephant.controller;

import com.kh.elephant.domain.ChatRoom;
import com.kh.elephant.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qiri/*")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoom;

    @GetMapping("/chatRoom")
    public ResponseEntity<List<ChatRoom>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(chatRoom.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/chatRoom/{id}")
    public ResponseEntity<ChatRoom> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(chatRoom.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("/chatRoom")
    public ResponseEntity<ChatRoom> create(@RequestBody ChatRoom vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(chatRoom.create(vo));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping("/chatRoom")
    public ResponseEntity<ChatRoom> update(@RequestBody ChatRoom vo){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(chatRoom.update(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("/chatRoom/{id}")
    public ResponseEntity<ChatRoom> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(chatRoom.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

