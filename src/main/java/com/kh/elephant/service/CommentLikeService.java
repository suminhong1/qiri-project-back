package com.kh.elephant.service;

import com.kh.elephant.domain.Board;
import com.kh.elephant.domain.CommentLike;
import com.kh.elephant.repo.BoardDAO;
import com.kh.elephant.repo.CommentLikeDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentLikeService {

    @Autowired
    private CommentLikeDAO commentLikeDAO;

    public List<CommentLike> showAll() {
        return commentLikeDAO.findAll();
    }
    public CommentLike show(int id) {
        return commentLikeDAO.findById(id).orElse(null);
    }

    public CommentLike create(CommentLike commentLike){
        return commentLikeDAO.save(commentLike);
    }
    public CommentLike update(CommentLike commentLike) {
        CommentLike target = commentLikeDAO.findById(commentLike.getCommentSeq()).orElse(null);
        if(target!=null){
            return commentLikeDAO.save(commentLike);
        }
        return null;
    }

    public CommentLike delete(int id) {
        CommentLike target = commentLikeDAO.findById(id).orElse(null);
        commentLikeDAO.delete(target);
        return target;
    }
}
