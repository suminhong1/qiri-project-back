package com.kh.elephant.controller;

import com.kh.elephant.domain.*;
import com.kh.elephant.service.MatchingCategoryInfoService;
import com.kh.elephant.service.PostAttachmentsService;
import com.kh.elephant.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/qiri/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class MatchingCategoryInfoController {

    @Autowired
    private MatchingCategoryInfoService service;

    @Autowired
    private PostService postService;

    // 게시글 전체 조회 http://localhost:8080/qiri/post
    @GetMapping("/matchingCategoryInfo")
    public ResponseEntity<List<MatchingCategoryInfo>> showAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 게시글seq로 게시글카테고리정보가져오기
    @GetMapping("/matchingCategoryInfo/{id}")
    public ResponseEntity<List<MatchingCategoryInfo>> getMatchingCategory(@PathVariable int id){
        log.info("category :: " + id);
        try{
            if(service.findByPostSEQ(id) == null) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(service.findByPostSEQ(id));
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 게시글 추가 http://localhost:8080/qiri/post
    @PostMapping("/matchingCategoryInfo")
    public ResponseEntity<List<MatchingCategoryInfo>> insert(@RequestBody MatchingCategoryInfoDTO dto) {


        List<MatchingCategoryInfo> list = new ArrayList<>();

        log.info("matching category list : " + dto.toString());

        for(int i=0; i<dto.getCategories().size(); i++) {
            MatchingCategoryInfo info = new MatchingCategoryInfo();

            Post post = new Post();
            post.setPostSEQ(dto.getPostSEQ());
            info.setPost(post);

            Category category = new Category();
            category.setCategorySEQ(dto.getCategories().get(i).getCategorySEQ());
            info.setCategory(category);

            list.add(info);
        }

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.createAll(list));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

        // 게시글 수정 http://localhost:8080/qiri/post
        @PutMapping("/matchingCategoryInfo")
        public ResponseEntity<List<MatchingCategoryInfo>> update(@RequestBody MatchingCategoryInfoDTO dto) {

            List<MatchingCategoryInfo> list = new ArrayList<>();

            log.info("matching category list : " + dto.toString());

            for(int i=0; i<dto.getCategories().size(); i++) {
                MatchingCategoryInfo info = new MatchingCategoryInfo();

                Post post = new Post();
                post.setPostSEQ(dto.getPostSEQ());
                info.setPost(post);

                Category category = new Category();
                category.setCategorySEQ(dto.getCategories().get(i).getCategorySEQ());
                info.setCategory(category);

                list.add(info);
            }

            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(service.updateAll(list));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
    // 게시글 삭제 http://localhost:8080/qiri/post/1 <--id
    @DeleteMapping("/matchingCategoryInfo/{id}")
    public ResponseEntity<MatchingCategoryInfo> delete ( @PathVariable int id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    }
