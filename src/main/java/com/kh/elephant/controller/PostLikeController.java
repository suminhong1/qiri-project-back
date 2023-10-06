package com.kh.elephant.controller;

import com.kh.elephant.domain.PostLike;
import com.kh.elephant.service.PostLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//으악
@RestController
@RequestMapping("/qiri/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)

public class PostLikeController {

    @Autowired
    private PostLikeService service;

    // 게시물 좋아요 전체 보기 http://localhost:8080/qiri/postlike
    @GetMapping("/postLike")
    public ResponseEntity<List<PostLike>> showAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 게시물 좋아요 보기????? http://localhost:8080/qiri/postlike/1 <-- id
    @GetMapping("/postLike/{id}")
    public ResponseEntity<PostLike> show(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 게시물 좋아요 추가? 누르기?? http://localhost:8080/qiri/postlike
    @PostMapping("/postLike")
    public ResponseEntity<PostLike> create(@RequestBody PostLike vo){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(service.create(vo));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 게시물 좋아요 수정???????? http://localhost:8080/qiri/postlike
    @PutMapping("/postLike")
    public ResponseEntity<PostLike> update(@RequestBody PostLike vo){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.update(vo));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 게시물 좋아요 취소 http://localhost:8080/qiri/postlike/1 <--id
    @DeleteMapping("/postLike{id}")
    public ResponseEntity<PostLike> delete(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
