package com.kh.elephant.service;

import com.kh.elephant.domain.PostAttachments;
import com.kh.elephant.domain.PostDTO;
import lombok.extern.slf4j.Slf4j;
import com.kh.elephant.domain.Post;
import com.kh.elephant.repo.PostDAO;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



import java.util.List;
@Slf4j
@Service
public class PostService {

    @Autowired
    private PostDAO dao;






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


    public Post create(PostDTO postDTO, List<PostAttachments> postattachments) {
        Post post =postDTO.ToPost();

        Post savePost = dao.save(post);

        for (PostAttachments attachments : postattachments){
            attachments.setPost(savePost);
        }
        return savePost;
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



}
