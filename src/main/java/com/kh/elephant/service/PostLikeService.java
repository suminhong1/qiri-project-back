package com.kh.elephant.service;

import com.kh.elephant.domain.PostLike;
import com.kh.elephant.repo.PostLikeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostLikeService {

    @Autowired
    private PostLikeDAO dao;

    public List<PostLike> showAll(){
        return dao.findAll();
    }
    public PostLike show(int postLikeSeq){
        return dao.findById(postLikeSeq).orElse(null);
    }
    public PostLike create(PostLike postLike){
        return dao.save(postLike);
    }
    public PostLike update(PostLike postLike){
        PostLike target = dao.findById(postLike.getPostLikeSeq()).orElse(null);
        if (target!=null){
            return dao.save(postLike);
        }
        return null;
    }
    public PostLike delete(int postLikeSeq){
        PostLike postLike = dao.findById(postLikeSeq).orElse(null);
        dao.delete(postLike);
        return postLike;
    }

}
