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

    // 게시글 전체 보기 http://localhost:8080/qiri/post
    // OR
    // 게시글 키워드로 검색 http://localhost:8080/qiri/post?keyword=1
    @GetMapping("/public/post")
    public ResponseEntity<List<Post>> postList(@RequestParam(name = "page", defaultValue = "1") int page,
                                               @RequestParam(name = "board", required = false) Integer board,
                                               @RequestParam(name = "keyword", required = false) String keyword) {
        log.info("post List 호출 컨트롤러 진입;");
        Sort sort = Sort.by("postSEQ").descending();
        Pageable pageable = PageRequest.of(page - 1, 20, sort);
        QPost qPost = QPost.post;
        BooleanBuilder builder = new BooleanBuilder();

        if (board != null) {
            BooleanExpression expression = qPost.board.boardSEQ.eq(board);
            builder.and(expression);
        }

        Page<Post> result = null;

        if (keyword == null) {
            // keyword가 없거나 비어있는 경우 전체 게시물을 가져옴
            result = postService.showAll(pageable, builder);
        } else {
            List<Post> searchResults = searchService.searchByKeyword(keyword);
            return ResponseEntity.status(HttpStatus.OK).body(searchResults);
        }

        log.info("............LIST@@@@@@@@@@ :   " + result.toString());

        // Page 객체에서 목록을 추출하여 반환
        return ResponseEntity.status(HttpStatus.OK).body(result.getContent());
    }



    // 게시글 골라 보기 http://localhost:8080/qiri/post/1 <--id
    @GetMapping("/public/post/{id}")
    public ResponseEntity<Post> show (@PathVariable int id){
        try {
            log.info("post 호출 컨트롤러 진입;");
            Post result = postService.show(id);
            log.info(".POST@@@@@@@@@ :   " + result.toString());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
//    public ResponseEntity<PostDTO> show(@PathVariable int id) {
//        try {
//            log.info("post 호출 컨트롤러 진입;");
//            Post result = postService.show(id);
//            log.info(".POST@@@@@@@@@ :   " + result.toString());
//
//             List<PostAttachments> postAttachments = paService.findByPostSeq(id);
//             List<MatchingCategoryInfo>matchingCategoryInfoList = mciService.findByPostSEQ(id);
//
//             List<String> attList = new ArrayList<>();
//             List<Category> matchList = new ArrayList<>();
//
//             for(PostAttachments item :  postAttachments){
//
//                 attList.add(item.getAttachmentURL());
//             }
//             for(MatchingCategoryInfo item : matchingCategoryInfoList)
//             {
//                 matchList.add(item.getCategory());
//             }
//
//             PostDTO responseDTO = PostDTO.builder()
//                     .attList(attList)
//                     .matchList(matchList)
//                     .build();
//
//
//            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }





    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody PostDTO dto) {
        // 지금 dto를 이용한 post 방식이기 때문에 post에 데이터를 넣어주고 db에 저장을 해야함

//        log.info(dto.toString());

//        log.info("카테고리리스트"+dto.getCategoryList().toString());
        try {
            // Place 객체를 Service.show로 가져옴 show가 get방식이랑 유사한 역할임
            Place place = plService.show(dto.getPlaceSEQ()); // dto에 담긴 int placeSeq 값을 사용해서 plService를 통해서 Place 객체의 정보를 가져와서 내가 선택해서 사용함

            PlaceType placeType = placeTypeService.show(dto.getPlaceTypeSEQ()); //place 안에 placeType가 join 돼있어도 선언해야함

            place.setPlaceType(placeType); //place 안에 placeType 데이터를 넣어줌
            // 걍 place에 placeType을 합친거임

            Board board = boardService.show(dto.getBoardSEQ());

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
                    .board(board)
                    .build();
            log.info("나와라이~ 나와라이~" + post);
            return ResponseEntity.ok().body(postService.create(post));// create, save도 post랑 유사한 역할
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    //   매칭 게시글 수정 http://localhost:8080/qiri/post
    @PutMapping("/post")
    public ResponseEntity<Post> update(@RequestBody PostDTO dto) {
        try {
            log.info("dto : " + dto.toString());

            Place place = plService.show(dto.getPlaceSEQ());
            log.info("여긴 오냐?");

            String userId = tokenProvider.validateAndGetUserId(dto.getToken());

            UserInfo userinfo = userInfoService.show(userId); // 서비스에서 찾은 Id와 같다면

            Board board = boardService.show(dto.getBoardSEQ());

//            if (updatePost.getUserInfo().getUserId().equals(userId)) {// userInfo에 들어있는 userId와
            log.info("유저 ID : " + userId);
            log.info("유저 : " + userinfo);

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
//            Post updatedPost = postService.update(post);
//
//            log.info("되라고 씨발아" + updatedPost);

            return ResponseEntity.status(HttpStatus.OK).body(postService.update(post));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    //  매칭 게시글 삭제 http://localhost:8080/qiri/post/1 <--id
    // update 형식으로 db에 데이터는 남기고 클라이언트 쪽에선 안보이게 처리
    @PutMapping("/post/{postSeq}")
    public ResponseEntity<String> delete(@PathVariable int postSeq) {
        try {
            Post post = postService.show(postSeq);
            if(post==null){
                return ResponseEntity.badRequest().body("게시물을 찾을 수 없습니다.");
            }
            post.setPostDelete("Y");
            postService.update(post);
            return ResponseEntity.ok().body("삭제된 게시물 입니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("게시물 삭제에 실패했습니다.");
        }
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

}



