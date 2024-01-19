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


import java.awt.*;
import java.util.*;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/qiri/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class PostController {

    @Value("C:\\ClassQ_team4_frontend\\qoqiri\\public\\upload") // 첨부파일 업로드 경로
    private String uploadPath;
    @Autowired
    private TokenProvider tokenProvider; // token을 이용한 유저 정보
    @Autowired
    private PostService postService; // post관련 서비스
    @Autowired
    private UserInfoService userInfoService; // 유저 관련 서비스
    @Autowired
    private CommentsService commService; // 댓글 관련 서비스
    @Autowired
    private PlaceService plService; // 지역 관련 서비스
    @Autowired
    private PlaceTypeService placeTypeService; // 지역 관련 서비스
    @Autowired
    private PlaceTypeService pTypeService;

    @Autowired
    private BoardService boardService; // 게시판 관련 서비스
    @Autowired
    private CategoryService categoryService; // 카테고리 관련 서비스
    @Autowired
    private PostAttachmentsService paService; // 첨부 파일 관련 서비스

    @Autowired
    private MatchingCategoryInfoService mciService; // 선택한 카테고리를 MatchingCategoryInfo 테이블로 저장시키기 위한 서비스

    // 검색
    @Autowired
    private SearchService searchService; // 검색 관련 서비스

    // 게시글 전체 보기 http://localhost:8080/qiri/post
    // OR
    // 게시글 키워드로 검색 http://localhost:8080/qiri/post?keyword=1
    @GetMapping("/public/post")
    public ResponseEntity<List<Post>> postList(@RequestParam(name = "page", defaultValue = "1") int page,
                                               @RequestParam(name = "board", required = false) Integer board,
                                               @RequestParam(name = "keyword", required = false) String keyword) {
        log.info("post List 호출 컨트롤러 진입;");
        Sort sort = Sort.by("postSEQ").descending();
        Pageable pageable = PageRequest.of(page - 1, 15, sort);
        QPost qPost = QPost.post;
        BooleanBuilder builder = new BooleanBuilder();

        // postdelete가 'Y'가 아닌 게시물만 가져오도록 조건 추가
        builder.and(qPost.postDelete.ne("Y"));

        if (board != null) {
            BooleanExpression expression = qPost.board.boardSEQ.eq(board);
            builder.and(expression);
        }
        Page<Post> result = null;
        if (keyword == null) {
            // 키워드가 없거나 비어있는 경우 전체 게시물을 가져옴
            result = postService.showAll(pageable, builder);
        } else {
            List<Post> searchResults = searchService.searchByKeyword(keyword);
            return ResponseEntity.status(HttpStatus.OK).body(searchResults);
        }

        log.info("............LIST@@@@@@@@@@ :   " + result.toString());

        // Page 객체에서 목록을 추출하여 반환
        return ResponseEntity.status(HttpStatus.OK).body(result.getContent());
    }


    // 게시글 상세 보기 http://localhost:8080/qiri/post/1 <--id
    @GetMapping("/public/post/{id}") //GetMapping을 위한 mapping 메소드 경로 설정
    public ResponseEntity<Post> show (@PathVariable int id){ // id를 경로 변수로 받고, 해당 id를 사용하여 게시물 정보를 조회
        try {
            log.info("post 호출 컨트롤러 진입;");
            Post result = postService.show(id); // 상세 보기와 조회수 증가 postService를 호출해서 result에 담음
            // 게시물 상세보기를 위한 게시글 SEQ값을 검색해 정보 조회를 위한 컨트롤러
            log.info(".POST@@@@@@@@@ :   " + result.toString());
            return ResponseEntity.status(HttpStatus.OK).body(result); // 게시물 정보를 body에 담아서 클라이언트에 전송해서 클라이언트에서 볼 수 있게함
        } catch (Exception e) {
            return null; // 예외가 발생했을때 Bad Request를 클라이언트에 응답
        }
    }


    @PostMapping("/post") //PostMapping을 위한 메소드 경로 설정
    public ResponseEntity<Post> createPost(@RequestBody PostDTO dto) {
        // dto를 이용한 post 방식이기 때문에 post에 데이터를 넣어주고 db에 저장을 해야함
        try {
            // Place 객체를 Service.show로 가져옴 show가 get방식이랑 유사한 역할임
            Place place = plService.show(dto.getPlaceSEQ()); // dto에 담긴 int placeSeq 값을 사용해서 plService를 통해서 Place 객체의 정보를 가져와서 내가 선택해서 사용함

            PlaceType placeType = placeTypeService.show(dto.getPlaceTypeSEQ()); //place 안에 placeType가 join 돼있어도 선언해야함

            place.setPlaceType(placeType); //place 안에 placeType 데이터를 넣어줌 그냥 place와 placeType을 합친것

            Board board = boardService.show(dto.getBoardSEQ()); // board에 저장된 정보를 조회하기 위해 boardService에 있는 show 메소드로 정보 조회

            String userId = tokenProvider.validateAndGetUserId(dto.getToken()); // 토큰을 추출해 토큰에서 추출한 ID를 userId에 저장
            log.info(userId);
            UserInfo userInfo = userInfoService.show(userId); // userInfo의 userId 데이터를 조회하기 위해 userInfoService의 show 메소드로 정보 조회

            // Post 객체를 post로 변수명 지정해 주고 get으로 dto안에 있는 필요한 것만 뽑아서 생성 
            Post post = Post.builder()
                    .postTitle(dto.getPostTitle())
                    .postContent(dto.getPostContent())
                    .postView(dto.getPostView())
                    .place(place) // 위에서 선언한 place를 여기다 담아줌 여기가 place를 저장 받고 다시 보내는 곳
                    .userInfo(userInfo)
                    .board(board)
                    .build();
            log.info("create dto : " + dto.toString());
            return ResponseEntity.ok().body(postService.create(post));// service에 있는 create 메소드를 사용해 post 생성
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 예외가 발생했을때 Bad Request를 클라이언트에 전송
        }
    }

    //   매칭 게시글 수정 http://localhost:8080/qiri/post
    @PutMapping("/post") //PutMapping을 위한 메소드 경로 설정
    public ResponseEntity<Post> update(@RequestBody PostDTO dto) {
        try {
            Place place = plService.show(dto.getPlaceSEQ()); // Place의 대한 정보를 조회하기 위해 Place Service의 show 메소드로 정보 조회

            String userId = tokenProvider.validateAndGetUserId(dto.getToken()); // 토큰을 추출해 토큰에서 추출한 ID를 userId에 저장

            UserInfo userinfo = userInfoService.show(userId); // userInfoService의 show 메소드를 사용하여 userId 정보 조회

            Board board = boardService.show(dto.getBoardSEQ()); // board에 저장된 정보를 조회하기 위해 boardService에 있는 show 메소드로 정보 조회

            // post 안에 있는 수정할 정보들
            Post post = Post.builder()
                    .postSEQ(dto.getPostSEQ())
                    .postTitle(dto.getPostTitle())
                    .postContent(dto.getPostContent())
                    .postDate(new Date())
                    .postView(dto.getPostView())
                    .postDelete(dto.getPostDelete())
                    .matched(dto.getMatched())
                    .postTitleDropbox(dto.getTitleDropbox())
                    .place(place)
                    .userInfo(userinfo)
                    .board(board)
                    .build();
            log.info("수정 : " + post);
            log.info("dto : " + dto);
            return ResponseEntity.status(HttpStatus.OK).body(postService.update(post));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    //  매칭 게시글 삭제 http://localhost:8080/qiri/post/1 <--id

    @PutMapping("/post/{postSeq}")   // update 형식으로 db에 데이터는 남기고 클라이언트 쪽에선 안보이게 처리
    public ResponseEntity<String> hidePost(@PathVariable int postSeq) {
        try {
            Post post = postService.show(postSeq); // postSEQ로 게시물 정보를 조회함
            if(post==null){ // post가 null일 경우
                return ResponseEntity.badRequest().body("게시물을 찾을 수 없습니다."); // 문자열 반환
            }
            post.setPostDelete("Y"); // postDelete를 Y로 설정 후 db에 저장
            postService.update(post); // 삭제지만 update 형식으로 클라이언트 쪽에서만 안보이게 처리함
            log.info("삭제 ::: " + post);
            return ResponseEntity.ok().body("삭제된 게시물 입니다."); // 삭제 후 문자열 전송
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("게시물 삭제에 실패했습니다."); // 예외가 발생 했을때 String문자열을 클라이언트에 전송
        }
    }

    @DeleteMapping("/post/{postSeq}") // DELETEMapping을 위한 메소드 경로 설정
    public ResponseEntity<Post>delete(@PathVariable int id){ // postSeq를 @PathVariable로 id라는 변수 명칭으로 사용
        log.info("삭제 ::: "+ postService.delete(id));
        return ResponseEntity.status(HttpStatus.OK).body(postService.delete(id));
        // postService에 Spring Data JPA에서 제공하는 delete로 일치하는 id를 찾아서 삭제
    }

    //카테고리타입SEQ받아서 해당하는 POST가져오기
    @GetMapping("/post/categoryType/{code}")
    public ResponseEntity<List<Post>> getPostsByCategoryType(@PathVariable int code) {
        try {
            List<MatchingCategoryInfo> matchingCategoryInfoList = mciService.findByCTSEQ(code);

            // 겹치지 않는 post 객체만 저장할 Set 생성
            Set<Post> uniquePosts = new HashSet<>();

            for (MatchingCategoryInfo mci : matchingCategoryInfoList) {
                Post post = mci.getPost();
                if (!uniquePosts.contains(post)) {
                    uniquePosts.add(post);
                }
            }
            // Set을 다시 List로 변환
            List<Post> postList = new ArrayList<>(uniquePosts);
            return ResponseEntity.status(HttpStatus.OK).body(postList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 내가 매칭글 가지고 오기
    @GetMapping("/post/get/{userId}")
    public ResponseEntity<List<Post>> getUserComments(@PathVariable String userId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(postService.findPostByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }



}



