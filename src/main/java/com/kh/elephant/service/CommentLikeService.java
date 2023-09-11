package com.kh.elephant.service;

import com.kh.elephant.domain.Board;
import com.kh.elephant.domain.CommentLike;
import com.kh.elephant.repo.BoardDAO;
import com.kh.elephant.repo.CommentLikeDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentLikeService {

    @Autowired
    private CommentLikeDAO dao;

    public List<CommentLike> showAll() {
        return dao.findAll();
    }
    public CommentLike show(int code) {
        return dao.findById(code).orElse(null);
    }

    public CommentLike create(CommentLike commentLike){
        return dao.save(commentLike);
    }
    public CommentLike update(CommentLike commentLike){
        return dao.save(commentLike);
    }

    public CommentLike delete(int code) {
        CommentLike data = dao.findById(code).orElse(null);
        dao.delete(data);
        return data;
    }
}
