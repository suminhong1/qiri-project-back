package com.kh.elephant.controller;

import com.kh.elephant.domain.UserCategoryInfo;
import com.kh.elephant.domain.UserPlaceInfo;
import com.kh.elephant.service.UserPlaceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserPlaceInfoController {

    @Autowired
    private UserPlaceInfoService service;

    @GetMapping("/userPlaceInfo")
    public ResponseEntity<List<UserPlaceInfo>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/userPlaceInfo/{id}")
    public ResponseEntity<UserPlaceInfo> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/userPlaceInfo")
    public ResponseEntity<UserPlaceInfo> insert(@RequestBody UserPlaceInfo userPlaceInfo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.create(userPlaceInfo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/userPlaceInfo/")
    public ResponseEntity<UserPlaceInfo> update(@RequestBody UserPlaceInfo userPlaceInfo){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(userPlaceInfo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("/userPlaceInfo/{id}")
    public ResponseEntity<UserPlaceInfo> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
