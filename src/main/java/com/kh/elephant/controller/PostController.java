package com.kh.elephant.controller;

import com.kh.elephant.domain.*;
import com.kh.elephant.security.TokenProvider;
import com.kh.elephant.service.*;
import com.kh.elephant.domain.UserInfo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/qiri/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class PostController {

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
    private PostThemaService ptService;

    @Autowired
    private BoardService boardService;


    // 게시글 전체 조회 http://localhost:8080/qiri/post
    @GetMapping("/public/post")
    public ResponseEntity<List<Post>> postList(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "board", required = false) Integer board) {
        log.info("post List 호출 컨트롤러 진입;");


        Sort sort = Sort.by("postSEQ").descending();

        Pageable pageable = PageRequest.of(page - 1, 20, sort);

        QPost qPost = QPost.post;
        BooleanBuilder builder = new BooleanBuilder();

        if (board != null) {
            BooleanExpression expression = qPost.board.boardSEQ.eq(board);

            builder.and(expression);
        }
        Page<Post> result = postService.showAll(pageable, builder);
        log.info("............LIST@@@@@@@@@@ :   " + result.toString());
        return ResponseEntity.status(HttpStatus.OK).body(result.getContent());
    }


    // 게시글 골라 보기 http://localhost:8080/qiri/post/1 <--id
    @GetMapping("/public/post/{id}")
    public ResponseEntity<Post> show(@PathVariable int id) {
        try {
            log.info("post 호출 컨트롤러 진입;");
            Post result = postService.show(id);
            log.info(".POST@@@@@@@@@ :   " + result.toString());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    // 게시글 추가 http://localhost:8080/qiri/post
//    @PostMapping("/post")
//    public ResponseEntity<Post> createPost(@AuthenticationPrincipal String id, @RequestParam(name = "video", required = false) MultipartFile video, @RequestParam(name = "image", required = false) MultipartFile image, String title, @RequestParam(name = "content") String content, String userId, String placeSeq, String postThemaSeq, String boardSeq ) {
//
////        String Id = tokenProvider.validateAndGetUserId(PostUploadDTO uploadDTO);
//
//        Post vo = new Post();
//
//        try {
//            String uuid = UUID.randomUUID().toString();
//
//            if (video != null) {
//                String originalVideo = video.getOriginalFilename();
//                String realVideo = uuid + "_" + originalVideo;
//                Path pathVideo = Paths.get(uploadPath, realVideo);
//                video.transferTo(pathVideo);
//            }
//
//            if (image != null) {
//                String originalImage = image.getOriginalFilename();
//                String realImage = uuid + "_" + originalImage;
//                Path pathImage = Paths.get(uploadPath, realImage);
//                image.transferTo(pathImage);
//            }
//
//            // 아래 되면 날짜 조회수 추가
//            vo.setPostTitle(title);
//            vo.setPostContent(content);
//
//            vo.setPostUrl(uuid);
//
//            UserInfo userInfo = new UserInfo();
//            userInfo.setUserId(userId);
//            vo.setUserInfo(userInfo);
//
//            Place place = new Place();
//            place.setPlaceSeq(Integer.parseInt(placeSeq));
//            vo.setPlaceSeq(place);
//
//            PostThema postThema = new PostThema();
//            postThema.setPostThemaSeq(Integer.parseInt(postThemaSeq));
//            vo.setPostThemaSeq(postThema);
//
//            Board board = new Board();
//            board.setBoardSEQ(Integer.parseInt(boardSeq));
//            vo.setBoard(board);
//
//            Post savedPost = postService.create(vo);
//
//            if (savedPost != null) {
//                return ResponseEntity.status(HttpStatus.OK).body(savedPost);
//            } else {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//            }
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

//    // 리뷰 추가
//    @PostMapping("/reviews")
//    public ResponseEntity<Post> saveReview(@RequestBody Post post) {
//        log.info("Received post data: " + post);
//        try {
//            // userId를 사용하여 UserInfo 엔터티 조회
//            if (post.getUserInfo() == null || post.getUserInfo().getUserId() == null) {
//                log.error("UserInfo or UserId is null in the request");
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//            }
//
//            UserInfo userInfo = userInfoService.show(post.getUserInfo().getUserId());
//            if (userInfo == null) {
//                log.error("No UserInfo found for the given UserId");
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//            }
//
//            // 조회한 UserInfo 엔터티를 post의 userInfo 필드에 설정
//            post.setUserInfo(userInfo);
//
//            // 리뷰 내용을 postContent 필드에 저장
//            Post savedPost = postService.create(post);
//            return ResponseEntity.status(HttpStatus.OK).body(savedPost);
//        } catch (Exception e) {
//            log.error("Error while saving review: ", e);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }

    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody PostDTO postDTO){
        log.info("나와라이~ 나와라이~" + postDTO.toString());

        Post post = postService.create(postDTO);

//        String userId = tokenProvider.validateAndGetUserId(userInfoDTO.getToken());


        return ResponseEntity.ok().body(post);
    }
    //    게시글 수정 http://localhost:8080/qiri/post
    @PutMapping("/post")
    public ResponseEntity<Post> update(@RequestBody Post post) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(postService.update(post));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //     게시글 삭제 http://localhost:8080/qiri/post/1 <--id
    @DeleteMapping("/post/{id}")
    public ResponseEntity<Post> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(postService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}


   //  검색
//    @GetMapping("/public/post/search/{keyword}")

//    public ResponseEntity<Page<Post>> search(@PathVariable String keyword,@PageableDefault(size = 20, sort = "postSEQ")Pageable pageable){
//        Page<Post> searchResult=postService.searchPost(keyword,pageable);
//        return ResponseEntity.status(HttpStatus.OK).body(searchResult);
//
//    public ResponseEntity<Page<Post>>search(@PathVariable String keyword,Pageable pageable){
//
//        Page<Post> list=null;
//
//        if(keyword==null){
//        pageable=PageRequest.of(0,20,Sort.by("postSEQ"));
//        list=postService.showAll(pageable); // 기존 리스트들 보여줌 얘왜 서비스에 showAll 안에 pageable 못불러오냐
//        }else{
//        list=postService.searchPost(keyword,pageable); // 검색리스트 반환
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(list);
//        }
//        }
        /*Page<Post>result = postService.search(keyword,pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);*/



//        return null;
