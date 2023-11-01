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
import org.springframework.transaction.annotation.Transactional;
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



    @Autowired UserInfoService userService;
    @Autowired
    private TokenProvider tokenProvider;

    public Page<Post> showAll(Pageable pageable, BooleanBuilder builder) {


        return dao.findAll(builder, pageable);
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


    public Post create(Post post){

        return dao.save(post);
    }



    public Post update(Post post) {

        Post target = dao.findById(post.getPostSEQ()).orElse(null);
        if (target != null) {
            target.setPostTitle(post.getPostTitle());
            target.setPostContent(post.getPostContent());
            target.setPlace(post.getPlace());
            return dao.save(target);
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

}
