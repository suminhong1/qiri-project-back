package com.kh.elephant.controller;

import com.kh.elephant.domain.MatchingCategoryInfo;
import com.kh.elephant.domain.Post;
import com.kh.elephant.domain.PostAttachments;
import com.kh.elephant.service.MatchingCategoryInfoService;
import com.kh.elephant.service.PostAttachmentsService;
import com.kh.elephant.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qiri/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class MatchingCategoryInfoController {

    @Autowired
    private MatchingCategoryInfoService service;

    // 게시글 전체 조회 http://localhost:8080/qiri/post
    @GetMapping("/MatchingCategoryInfo")
    public ResponseEntity<List<MatchingCategoryInfo>> showAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 게시글 골라 보기 http://localhost:8080/qiri/post/1 <--id
    @GetMapping("/MatchingCategoryInfo/{id}")
    public ResponseEntity<MatchingCategoryInfo> show(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 게시글 추가 http://localhost:8080/qiri/post
    @PostMapping("/MatchingCategoryInfo")
    public ResponseEntity<MatchingCategoryInfo> insert(@RequestBody MatchingCategoryInfo matchingCategoryInfo){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.create(matchingCategoryInfo));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 게시글 수정 http://localhost:8080/qiri/post
    @PutMapping("/MatchingCategoryInfo")
    public ResponseEntity<MatchingCategoryInfo> update(@RequestBody MatchingCategoryInfo matchingCategoryInfo){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.update(matchingCategoryInfo));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 게시글 삭제 http://localhost:8080/qiri/post/1 <--id
    @DeleteMapping("/MatchingCategoryInfo/{id}")
    public ResponseEntity<MatchingCategoryInfo> delete(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
