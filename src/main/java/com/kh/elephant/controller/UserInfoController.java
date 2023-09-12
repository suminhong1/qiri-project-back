package com.kh.elephant.controller;

import com.kh.elephant.domain.UserInfo;
import com.kh.elephant.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/*")
public class UserInfoController {

    @Autowired
    private UserInfoService userService;

    // 유저 전체 조회 http://localhost:8080/api/userInfo
    @GetMapping("/userInfo")
    public ResponseEntity<List<UserInfo>> showAllUser(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.showAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 유저 상세 조회 http://localhost:8080/api/userInfo/1 <--id
    @GetMapping("/userInfo/{id}")
    public ResponseEntity<UserInfo> showUser(@PathVariable String id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.show(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 유저 추가 http://localhost:8080/api/userInfo
    @PostMapping("/userInfo")
    public ResponseEntity<UserInfo> createUser(@RequestBody UserInfo user){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.create(user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 유저 수정 http://localhost:8080/api/userInfo/1 <--id
    @PutMapping("/userInfo")
    public ResponseEntity<UserInfo> updateUser(@RequestBody UserInfo user){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.update(user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 유저 삭제 http://localhost:8080/api/userInfo/1 <--id
    @DeleteMapping("/userInfo/{id}")
    public ResponseEntity<UserInfo> deleteUser(@PathVariable String id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
