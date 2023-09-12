package com.kh.elephant.service;

import com.kh.elephant.domain.CommentLike;
import com.kh.elephant.domain.PostThema;
import com.kh.elephant.repo.PostThemaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostThemaService {
    @Autowired
    private PostThemaDAO postThemaDAO;

    public List<PostThema> showAll() {
        return postThemaDAO.findAll();
    }
    public PostThema show(int code) {
        return postThemaDAO.findById(code).orElse(null);
    }

    public PostThema create(PostThema postThema){
        return postThemaDAO.save(postThema);
    }
    public PostThema update(PostThema postThema) {
        PostThema target = postThemaDAO.findById(postThema.getPostThemaSeq()).orElse(null);
        if(target!=null){
            return postThemaDAO.save(postThema);
        }
        return null;
    }

    public PostThema delete(int id) {
        PostThema target = postThemaDAO.findById(id).orElse(null);
        postThemaDAO.delete(target);
        return target;
    }
}
