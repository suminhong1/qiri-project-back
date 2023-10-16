package com.kh.elephant.controller;

import com.kh.elephant.domain.UserCategoryInfo;
import com.kh.elephant.service.UserCategoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qiri/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)

public class UserCategoryInfoController {

    @Autowired
    private UserCategoryInfoService service;

    @GetMapping("public/userCategoryInfo")
    public ResponseEntity<List<UserCategoryInfo>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/userCategoryInfo/{id}")
    public ResponseEntity<UserCategoryInfo> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/public/userCategoryInfo")
    public ResponseEntity<UserCategoryInfo> insert(@RequestBody UserCategoryInfo userCategoryInfo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.create(userCategoryInfo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/UserCategoryInfo/")
    public ResponseEntity<UserCategoryInfo> update(@RequestBody UserCategoryInfo userCategoryInfo){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(userCategoryInfo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("/UserCategoryInfo/{id}")
    public ResponseEntity<UserCategoryInfo> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
