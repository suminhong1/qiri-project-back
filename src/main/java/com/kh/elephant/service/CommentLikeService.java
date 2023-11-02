package com.kh.elephant.service;

import com.kh.elephant.domain.Board;
import com.kh.elephant.domain.CommentLike;
import com.kh.elephant.repo.BoardDAO;
import com.kh.elephant.repo.CommentLikeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentLikeService {

    @Autowired
    private CommentLikeDAO dao;

    // 댓글 1개에 따른 좋아요 전체 조회
    public List<CommentLike> findByCommentSeq(int id) { return dao.findByCommentSeq(id); }

    public List<CommentLike> showAll() {
        return dao.findAll();
    }
    
    public CommentLike show(int id) {
        return dao.findById(id).orElse(null);
    }

    // 좋아요 추가
    public CommentLike create(CommentLike commentLike){
        return dao.save(commentLike);
    }

//    public CommentLike update(CommentLike commentLike) {
//        CommentLike target = dao.findById(commentLike.getCommentSeq()).orElse(null);
//        if(target!=null){
//            return dao.save(commentLike);
//        }
//        return null;
//    }

    // 좋아요 삭제 (comments_seq 받아서 삭제)
    public CommentLike deleteCommentlike(int id) {
        return dao.deleteCommentlike(id);
    }

    // 좋아요 삭제 (cl_seq 받아서 삭제)
    public CommentLike delete(int id) {
        CommentLike target = dao.findById(id).orElse(null);
        dao.delete(target);
        return target;
    }

}
