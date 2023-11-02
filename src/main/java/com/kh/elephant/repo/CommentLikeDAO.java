package com.kh.elephant.repo;

import com.kh.elephant.domain.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentLikeDAO extends JpaRepository <CommentLike, Integer> {
    @Query(value="SELECT cl_seq,cl_date,comments_seq,user_id FROM comment_like WHERE comments_seq = :id", nativeQuery = true)
    List<CommentLike> findByCommentSeq(@Param("id") Integer id);

    @Query(value="DELETE FROM comment_like where comments_seq = :id", nativeQuery = true)
    CommentLike deleteCommentlike(@Param("id") Integer id);

}
