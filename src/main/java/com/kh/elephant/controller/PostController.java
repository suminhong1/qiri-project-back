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
    private PlaceTypeService pTypeService;

    @Autowired
    private PostThemaService pThemaService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private CategoryService categoryService;

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
//    public ResponseEntity <String> upload(@RequestBody PostUploadDTO UploadDTO) {
//
//        List<PostAttachments>postAttachments = UploadDTO.getPostAttachments();
//
//        PostDTO postDTO = UploadDTO.getPostDTO();

//        Post upload = postService.create(postDTO);

//        if(upload != null){
//            return new ResponseEntity<>("게시물이 등록되었습니다", HttpStatus.CREATED);
//
//        }else {
//            return new ResponseEntity<>("게시물 등록에 실패했습니다",HttpStatus.BAD_REQUEST);
//        }
//        Post post = Post.builder()
//                .postSEQ(dto.getPostDTO().getSeq())
//                .postTitle(dto.getPostDTO().getTitle())
//                .postContent(dto.getPostDTO().getContent())
//                .postDate(dto.getPostDTO().getDate())
//                .postView(dto.getPostDTO().getView())
//                .postUrl(dto.getPostDTO().getUrl())
//                .userInfo(dto.getPostDTO().getUserInfo())
//                .placeSeq(dto.getPostDTO().getPlaceSeq())
//                .postThemaSeq(dto.getPostDTO().getPostThemaSeq())
//                .board(dto.getPostDTO().getBoard())
//                .postNotice(dto.getPostDTO().getPostNotice())
//                .postDelete(dto.getPostDTO().getDelete())
//                .build();

//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(postService.create(post));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

//    }









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

    // 리뷰 업데이트 테스트용
//    @PostMapping("/reviewUpdate")
//    public ResponseEntity<Post> update(@RequestBody PostDTO dto) {
//        log.info("들어옴?");
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
//
//        Post post = Post.builder()
//                .postTitle(dto.getPostTitle())
//                .postContent(dto.getPostContent())
//                .build();
//
//
//        return ResponseEntity.ok().body(postService.update(post));
//    }

    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody PostDTO dto){
        log.info("나와라이~ 나와라이~" + dto.toString());

        Place place = plService.show(dto.getPlaceSeq());

        PostThema postThema = pThemaService.show(dto.getPostThemaSeq());

        Board board = boardService.show(dto.getBoardSeq());

        String userId = tokenProvider.validateAndGetUserId(dto.getToken());
        UserInfo userInfo = userInfoService.show(userId);

// Post 객체를 post로 변수명 지정해 주고 get으로 dto안에 있는 필요한 것만 뽑아서씀
       Post post = Post.builder()
               .postTitle(dto.getPostTitle())
               .postContent(dto.getPostTitle())
               .postView(dto.getPostView())
               .postUrl("URL박아야함")
               .place(place)
               .postThema(postThema)
               .board(board)
               .build();

        for (Integer categorySEQ : dto.getCategoryList()) {
            Category category = categoryService.show(categorySEQ);
            MatchingCategoryInfo matchingCategoryInfo
                    = MatchingCategoryInfo.builder()
                                    .post(post)
                                    .category(category)
                                            .build();
            mciService.create(matchingCategoryInfo);
        }

        for(String attachmentURL: dto.getAttachmentList()) {
                        PostAttachments postAttachments
                    = PostAttachments.builder()
                    .post(post)
                    .attachmentURL(attachmentURL)
                    .build();
            paService.create(postAttachments);
        }

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
    // 게시글 좋아요

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
