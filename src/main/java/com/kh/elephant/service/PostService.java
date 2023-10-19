package com.kh.elephant.service;


import com.kh.elephant.domain.Post;
import com.kh.elephant.repo.PostDAO;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostDAO dao;



    public Page<Post> showAll(Pageable pageable, BooleanBuilder builder){

        return dao.findAll(builder, pageable);
    }

    public Post show(int id){

        return dao.findById(id).orElse(null);
    }

    public Post create(Post post){
        return dao.save(post);
    }

    public Post update(Post post){
       Post target = dao.findById(post.getPostSEQ()).orElse(null);
       if(target !=null){
           return dao.save(post);
       }
       return null;
    }

    public Post delete(int id){
        Post post = dao.findById(id).orElse(null);
        dao.delete(post);
        return post;
    }
    public List<Post> findByBoardCode(int code){

        return dao.findByBoardCode(code);
    }

    public List<Post> search(String keyword, Pageable pageable){
        System.out.println("서비스 키워드 : " + keyword);
        return dao.findByTitle(keyword);
    }


}


