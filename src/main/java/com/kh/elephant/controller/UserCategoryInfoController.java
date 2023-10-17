package com.kh.elephant.controller;

import com.kh.elephant.domain.UserCategoryInfo;
import com.kh.elephant.service.UserCategoryInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/qiri/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class UserCategoryInfoController {

    @Autowired
    private UserCategoryInfoService categoryInfoService;

    // 관심사 카테고리 전체 조회
    @GetMapping("/userCategoryInfo")
    public ResponseEntity<List<UserCategoryInfo>> showAllCategories() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoryInfoService.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 관심사 카테고리 상세 조회
    @GetMapping("/userCategoryInfo/{id}")
    public ResponseEntity<UserCategoryInfo> showCategory(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoryInfoService.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 유저 관심사 카테고리 정보 등록
    @PostMapping("/userCategoryInfo")
    public ResponseEntity<List<UserCategoryInfo>> createCategories(@RequestBody List<UserCategoryInfo> categories) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryInfoService.createAll(categories));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 관심사 카테고리 정보 수정
    @PutMapping("/userCategoryInfo/{id}")
    public ResponseEntity<UserCategoryInfo> updateCategory(@PathVariable int id, @RequestBody UserCategoryInfo category) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoryInfoService.update(category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 관심사 카테고리 정보 삭제
    @DeleteMapping("/userCategoryInfo/{id}")
    public ResponseEntity<UserCategoryInfo> deleteCategory(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoryInfoService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
