package com.kh.elephant.controller;

import com.kh.elephant.domain.Post;
import com.kh.elephant.domain.PostDTO;
import com.kh.elephant.domain.PostLike;
import com.kh.elephant.domain.PostLikeDTO;
import com.kh.elephant.repo.PostLikeDAO;
import com.kh.elephant.security.TokenProvider;
import com.kh.elephant.service.PostLikeService;
import com.kh.elephant.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//으악
@Slf4j
@RestController
@RequestMapping("/qiri/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)

public class PostLikeController {

    @Autowired
    private PostLikeService service;

    @Autowired
    private PostLikeDAO dao;

    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private PostService postService;

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
    @PostMapping("/public/postLike")
    public ResponseEntity<PostLike> create(@RequestBody PostLikeDTO dto) {

//        Post post = postService.show(dto.getPostSEQ());
//
//       String userId = dto.getUserId();

//        PostLike postLike = PostLike.builder()
//                .post(dto.getUserId())
//                .post(dto.getPostSEQ())
//                .build();
//        return ResponseEntity.ok().body(service.create(postLike));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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
