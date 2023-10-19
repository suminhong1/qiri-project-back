package com.kh.elephant.controller;

import com.kh.elephant.domain.Post;
import com.kh.elephant.domain.QPost;
import com.kh.elephant.domain.UserInfo;
import com.kh.elephant.service.PostService;
import com.kh.elephant.service.UserInfoService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/qiri/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserInfoService userInfoService;


    // 게시글 전체 조회 http://localhost:8080/qiri/post
    @GetMapping("/public/post")
    public ResponseEntity<List<Post>>postList(@RequestParam(name="page",defaultValue = "1")int page, @RequestParam(name = "board",required = false)Integer board){
        Sort sort = Sort.by("postSEQ").descending();

        Pageable pageable = PageRequest.of(page-1, 20, sort);

        QPost qPost = QPost.post;
        BooleanBuilder builder = new BooleanBuilder();

        if(board!=null){
            BooleanExpression expression = qPost.board.boardSeq.eq(board);

            builder.and(expression);
        }
        Page<Post> result = postService.showAll(pageable,builder);
        return ResponseEntity.status(HttpStatus.OK).body(result.getContent());
    }


    // 게시글 골라 보기 http://localhost:8080/qiri/post/1 <--id
    @GetMapping("/public/post/{id}")
    public ResponseEntity<Post> show(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(postService.show(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 게시글 추가 http://localhost:8080/qiri/post
    @PostMapping("/post")
    public ResponseEntity<Post> insert(@RequestBody Post post){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(postService.create(post));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/reviews")
    public ResponseEntity<Post> saveReview(@RequestBody Post post) {
        log.info("Received post data: " + post);
        try {
            // userId를 사용하여 UserInfo 엔터티 조회
            if (post.getUserInfo() == null || post.getUserInfo().getUserId() == null) {
                log.error("UserInfo or UserId is null in the request");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            UserInfo userInfo = userInfoService.show(post.getUserInfo().getUserId());
            if (userInfo == null) {
                log.error("No UserInfo found for the given UserId");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            // 조회한 UserInfo 엔터티를 post의 userInfo 필드에 설정
            post.setUserInfo(userInfo);

            // 리뷰 내용을 postContent 필드에 저장
            Post savedPost = postService.create(post);
            return ResponseEntity.status(HttpStatus.OK).body(savedPost);
        } catch (Exception e) {
            log.error("Error while saving review: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }




    // 게시글 수정 http://localhost:8080/qiri/post
    @PutMapping("/post")
    public ResponseEntity<Post> update(@RequestBody Post post){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(postService.update(post));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 게시글 삭제 http://localhost:8080/qiri/post/1 <--id
    @DeleteMapping("/post/{id}")
    public ResponseEntity<Post> delete(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(postService.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 검색
    @GetMapping("/public/post/search/{keyword}")
    public ResponseEntity<List<Post>> search(@PathVariable String keyword,@PageableDefault(size = 20, sort = "postSEQ")Pageable pageable){
        try{
            log.info(keyword);
            return ResponseEntity.status(HttpStatus.OK).body(postService.search(keyword,pageable));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

//        return null;
    }





}
