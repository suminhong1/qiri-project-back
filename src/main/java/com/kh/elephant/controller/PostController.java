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
    // 게시글 전체 조회 http://localhost:8080/qiri/post
    @GetMapping("/public/post")
    public ResponseEntity<List<Post>> postList(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "board", required = false) Integer board) {
        log.info("post List 호출 컨트롤러 진입;");
    //get 방식은 db에 저장돼 있는 걸 가져 오기 때문에 데이터를 안 넣어도 됨
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

    // 리뷰 DB 저장
    @PostMapping("/reviewWrite")
    public ResponseEntity<Post> create(@RequestBody PostDTO dto) {
        log.info("들어옴?");
        Board board = boardService.show(dto.getBoardSeq());
        String userId = tokenProvider.validateAndGetUserId(dto.getToken());
        UserInfo userinfo = userInfoService.show(userId);


        Post post = Post.builder()
                .postTitle(dto.getPostTitle())
                .postContent(dto.getPostContent())
                .userInfo(userinfo)
                .board(board)
                .build();

        
        return ResponseEntity.ok().body(postService.create(post));
    }

    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody PostDTO dto){
    // 지금 dto를 이용한 post 방식이기 때문에 post에 데이터를 넣어주고 db에 저장을 해야함
    try{
        // Place 객체를 Service.show로 가져옴 show가 get방식이랑 유사한 역할임
        Place place = plService.show(dto.getPlaceSeq()); // dto에 담긴 int placeSeq 값을 사용해서 plService를 통해서 Place 객체의 정보를 가져와서 내가 선택해서 사용함
        PlaceType placeType = placeTypeService.show(dto.getPlaceTypeSeq()); //place 안에 placeType 있어도 선언해야함
        
        place.setPlaceType(placeType); //place 안에 placeType 데이터를 넣어줌
        // 걍 place에 placeType을 합친거임

//        Board board = boardService.show(dto.getBoardSeq());

        String userId = tokenProvider.validateAndGetUserId(dto.getToken());
        log.info(userId);
        UserInfo userInfo = userInfoService.show(userId);

// Post 객체를 post로 변수명 지정해 주고 get으로 dto안에 있는 필요한 것만 뽑아서씀
       Post post = Post.builder()
               .postTitle(dto.getPostTitle())
               .postContent(dto.getPostContent())
               .postView(dto.getPostView())
               .place(place) // 위에서 선언한 place를 여기다 담아줌 여기가 ㄹㅇ로 저장 받고 다시 보내는 곳
               .userInfo(userInfo)
//               .board(board)
               .build();

       // dto로 가져오는 List들에 아직 데이터가 없어서 분기처리 해줘야함
        // 카테고리 선택은 5개까지
//        for (Integer categorySEQ : dto.getCategorys()) {
//            Category category = categoryService.show(categorySEQ);
//            MatchingCategoryInfo matchingCategoryInfo
//                    = MatchingCategoryInfo.builder()
//                    .post(post)
//                    .category(category)
//                    .build();
//            mciService.create(matchingCategoryInfo);
//        }
//
////         첨부 파일 선택은 3개까지
//        for (String attachmentURL : dto.getAttachments()) {
//            PostAttachments postAttachments
//                    = PostAttachments.builder()
//                    .post(post)
//                    .attachmentURL(attachmentURL)
//                    .build();
//            paService.create(postAttachments);
//        }

        log.info("나와라이~ 나와라이~" + post);
        return ResponseEntity.ok().body(postService.create(post));// create, save도 post랑 유사한 역할
    }catch (Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
         }
    }

    //    게시글 수정 http://localhost:8080/qiri/post
    @PutMapping("/post")
    public ResponseEntity<Post> update(@RequestBody PostDTO dto) {

            Post updatePost = postService.show(dto.getPostSeq());

            Place place = plService.show(dto.getPlaceSeq());
            PlaceType placeType = placeTypeService.show(dto.getPlaceTypeSeq());
            place.setPlaceType(placeType);

            String userId = tokenProvider.validateAndGetUserId(dto.getToken());

            if(updatePost.getUserInfo().getUserId().equals(userId)){// userInfo에 들어있는 userId와

           UserInfo userInfo = userInfoService.findByNickname(userId); // 서비스에서 찾은 Id와 같다면
                Post post = Post.builder()
                        .postSEQ(dto.getPostSeq())
                        .postTitle(dto.getPostTitle())
                        .postContent(dto.getPostContent())
                        .place(place)
                        .userInfo(userInfo)
                        .build();
                return ResponseEntity.status(HttpStatus.OK).body(postService.update(post));
            }
            return ResponseEntity.badRequest().build();
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
