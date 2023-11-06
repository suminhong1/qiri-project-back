package com.kh.elephant.controller;

import com.kh.elephant.domain.*;
import com.kh.elephant.security.TokenProvider;
import com.kh.elephant.service.*;
import com.kh.elephant.domain.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Date;



@Slf4j
@RestController
@RequestMapping("/qiri/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class ReviewController {

    @Value("D:\\ClassQ_team4_frontend\\qoqiri\\public\\upload")
    private String uploadPath;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private PostService postService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private CommentsService commService;
    @Autowired
    private PlaceService plService;
    @Autowired
    private PlaceTypeService placeTypeService;
    @Autowired
    private PlaceTypeService pTypeService;

    @Autowired
    private BoardService boardService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostAttachmentsService paService;

    @Autowired
    private MatchingCategoryInfoService mciService;

    // 검색
    @Autowired
    private SearchService searchService;


    // 리뷰 DB 저장
    @PostMapping("/reviewWrite")
    public ResponseEntity<Post> reviewCreate(@RequestBody PostDTO dto) {
        log.info("들어옴?");
        Board board = boardService.show(dto.getBoardSEQ());
        String userId = tokenProvider.validateAndGetUserId(dto.getToken());
        UserInfo userinfo = userInfoService.show(userId);

        Post post = Post.builder()
                .postTitle(dto.getPostTitle())
                .postContent(dto.getPostContent())
                .userInfo(userinfo)
                .board(board)

                .postTitleDropbox("Y")
                .build();
        return ResponseEntity.ok().body(postService.create(post));
    }

    // 리뷰 수정
    @PutMapping("/reviewUpdate")
    public ResponseEntity<Post> reviewUpdate(@RequestBody PostDTO dto) {

        Board board = boardService.show(dto.getBoardSEQ());
        String userId = tokenProvider.validateAndGetUserId(dto.getToken());
        UserInfo userinfo = userInfoService.show(userId);
        Place place = plService.show(dto.getPlaceSEQ());
        Post post = Post.builder()
                .postSEQ(dto.getPostSEQ())  // 여기서 ID를 설정해야 합니다.
                .postTitle(dto.getPostTitle())
                .postContent(dto.getPostContent())
                .place(place)
                .postDate(new Date())
                .userInfo(userinfo)
                .postDelete("N")
                .matched("N")
                .postTitleDropbox("Y")
                .board(board)
                .build();
        Post updatedPost = postService.update(post);
        if (updatedPost == null) {
        }
        return ResponseEntity.ok().body(updatedPost);
    }



    // 리뷰 삭제 (update사용)
    @PutMapping("/reviewDelete/{postSEQ}")
    public ResponseEntity<String> reviewDelete(@PathVariable int postSEQ) {
        try {
            Post reviewPost = postService.show(postSEQ);
            if (reviewPost == null) {
                return ResponseEntity.badRequest().body("찾았다");
            }
            reviewPost.setPostDelete("Y");
            postService.update(reviewPost);
            Post mainPost = postService.findByBoardSeqAndPostTitle(1, reviewPost.getPostTitle());
            if (mainPost != null) {
                mainPost.setPostTitleDropbox("N");
                postService.update(mainPost);
            }
            return ResponseEntity.ok().body("업데이트완료");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("업데이트완료실패");
        }
    }

    // 드롭박스 선택한 포스트 드롭박스 Y로 업데이트
    @PutMapping("/updatePostTitleDropbox/{postSEQ}")
    public ResponseEntity<String> updatePostTitleDropbox(@PathVariable int postSEQ) {
        try {
            Post post = postService.show(postSEQ);
            if (post == null) {
                return ResponseEntity.badRequest().body("Post not");
            }
            post.setPostTitleDropbox("Y");
            postService.update(post);
            return ResponseEntity.ok().body("Post deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed!");
        }
    }
}