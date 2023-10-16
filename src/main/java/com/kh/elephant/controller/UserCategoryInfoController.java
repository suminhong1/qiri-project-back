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


//    @GetMapping("public/userCategoryInfo")
//    public ResponseEntity<List<UserCategoryInfo>> showAll() {}

    // 관심사 카테고리 전체 조회
    @GetMapping("/userCategoryInfo")
    public ResponseEntity<List<UserCategoryInfo>> showAllCategories() {
        try {
            List<UserCategoryInfo> categories = categoryInfoService.showAll();
            return ResponseEntity.status(HttpStatus.OK).body(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 관심사 카테고리 상세 조회
    @GetMapping("/userCategoryInfo/{id}")
    public ResponseEntity<UserCategoryInfo> showCategory(@PathVariable int id) {
        try {
            UserCategoryInfo category = categoryInfoService.show(id);
            return ResponseEntity.status(HttpStatus.OK).body(category);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


//    @PostMapping("/public/userCategoryInfo")
//    public ResponseEntity<UserCategoryInfo> insert(@RequestBody UserCategoryInfo userCategoryInfo) {}

    // 관심사 카테고리 정보 생성
    @PostMapping("/userCategoryInfo")
    public ResponseEntity<UserCategoryInfo> createCategory(@RequestBody UserCategoryInfo category) {

        try {
            UserCategoryInfo createdCategory = categoryInfoService.create(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 관심사 카테고리 정보 수정
//    @PutMapping("/userCategoryInfo/{id}")
//    public ResponseEntity<UserCategoryInfo> updateCategory(@PathVariable int id, @RequestBody UserCategoryInfo category) {
//        try {
//            UserCategoryInfo updatedCategory = categoryInfoService.update(id, category);
//            return ResponseEntity.status(HttpStatus.OK).body(updatedCategory);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }

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
