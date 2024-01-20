package com.kh.elephant.controller;

import com.kh.elephant.domain.BlockUsers;
import com.kh.elephant.service.BlockUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qiri/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class BlockUsersController {

    @Autowired
    private BlockUsersService service;

    // 유저간 밴 전체 보기
    @GetMapping("/blockUsers")
    public ResponseEntity<List<BlockUsers>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 내가 차단한 유저 전체 보기
    @GetMapping("/blockUsers/{id}")
    public ResponseEntity<List<BlockUsers>> showBlocUsers(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.showBlockUser(id));
    }

    // 유저 차단하기
    @PostMapping("/blockUsers")
    public ResponseEntity<BlockUsers> create(@RequestBody BlockUsers blockUsers) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.create(blockUsers.getUserInfo().getUserId(), blockUsers.getBlockInfo().getUserId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 다시 차단 (unblock "Y"으로 변경)
    @PutMapping("/blockUsers/update/{id}")
    public ResponseEntity<Void> updateBlockUser(@PathVariable String id) {
        service.updateBlockUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    // 차단 해제 (unblock "N"으로 변경)
    @PutMapping("/blockUsers/delete/{id}")
    public ResponseEntity<Void> deleteBlockUser(@PathVariable String id) {
        service.deleteBlockUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}


