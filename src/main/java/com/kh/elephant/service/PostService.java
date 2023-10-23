package com.kh.elephant.service;

import com.kh.elephant.domain.*;
import com.kh.elephant.repo.UserInfoDAO;
import com.kh.elephant.security.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import com.kh.elephant.repo.PostDAO;
import com.querydsl.core.BooleanBuilder;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
@Slf4j
@Service
public class PostService {

    @Autowired
    private PostDAO dao;

    @Autowired
    private UserInfoDAO userDao;

    @Autowired
    private PostAttachmentsService postAttachmentsService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private PostThemaService postThemaService;

    @Autowired UserInfoService userService;
    @Autowired
    private TokenProvider tokenProvider;

    public Page<Post> showAll(Pageable pageable, BooleanBuilder builder) {


        return dao.findAll(builder, pageable);
    }

    // 검색 기능
    public Page<Post> searchPost(String keyword, Pageable pageable) {
        System.out.println("서비스 키워드 : " + keyword);
        return dao.findByPostTitleContaining(keyword, pageable);
    }


    // 하나의 서비스 안에 여러 가지 기능의 로직을 짤수 있음
    public Post show(int id) {

//        return dao.findById(id).orElse(null);

        Post post = dao.findById(id).orElse(null);

        if(post != null){
            post.setPostView(post.getPostView()+1);// 조회수 1 증가

            dao.save(post); // 증가된거 저장
    }

        return post;
    }

    public Post create(PostDTO postDTO){




        // UserInfo 준영이가 수정해주면 UserService로 불러와서 짧게 넣으면됨

//        UserInfo userInfo = userDao.findById(post.getUserInfo().getUserId().orElse(null));

//        PostUploadDTO uploadDTO = buildPostDTO()

        Post post = buildPostDTO(postDTO);






//        Post post = Post.builder().userInfo(userInfo).build();


        return dao.save(post);
}

    public Post update(Post post) {
        Post target = dao.findById(post.getPostSEQ()).orElse(null);
        if (target != null) {
            return dao.save(post);
        }
        return null;
    }

    public Post delete(int id) {
        Post post = dao.findById(id).orElse(null);
        dao.delete(post);
        return post;
    }

    public List<Post> findByBoardCode(int code) {

        return dao.findByBoardCode(code);
    }






//    public Post increaseViewCount(int code){
//        log.info("조회수가 증가하나여"+code);
//
//        Post post = dao.findById(code).orElse(null);
//
//        if(post != null){
//            post.setPostView(post.getPostView()+1);// 조회수 1 증가
//
//            dao.save(post); // 증가된거 저장
//
//        }
//        return post;
//    }


    public Post buildPostDTO (PostDTO dto){

        String userId = tokenProvider.validateAndGetUserId(dto.getToken());
        UserInfo userInfo = userService.show(userId);

        Place place = placeService.show(dto.getPlaceSeq());

        Board board = boardService.show(dto.getBoardSeq());



        Post post = Post.builder()

                .postTitle(dto.getPostTitle())
                .postContent(dto.getPostContent())
                .postView(dto.getPostView())
                .postUrl(dto.getPostUrl())
                .userInfo(userInfo)
//                .placeSeq(place)
//                .board(board)
                .build();

        return post;

    }

}
