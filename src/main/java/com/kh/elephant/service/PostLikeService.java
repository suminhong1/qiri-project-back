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

    public PostLike show(int id){
        return dao.findById(id).orElse(null);
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

    public PostLike delete(int id){
        PostLike postLike = dao.findById(id).orElse(null);
        dao.delete(postLike);
        return postLike;
    }

    public PostLike increasePostLike (int likeCode){

        dao.increasePostlike(likeCode);
        return dao.findById(likeCode).orElse(null);
    }
//    public PostLike decreasePostLike (int likeCode){
//
//        dao.decreasePostlike(likeCode);
//        return dao.findById(likeCode).orElse(null);
//    }
    // 중복방지
    public PostLike duplicatedLike(String id, int likeCode){
        return dao.postLikeByUserAndPost(id, likeCode);
    }

}
