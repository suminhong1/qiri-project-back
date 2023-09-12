package com.kh.elephant.service;

import com.kh.elephant.domain.Post;
import com.kh.elephant.repo.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostDAO dao;

    public List<Post> showAll(){
        return dao.findAll();
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
}
