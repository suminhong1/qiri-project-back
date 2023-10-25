package com.kh.elephant.repo;

import com.kh.elephant.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentsDAO extends JpaRepository<Comments, Integer> {

    // 게시물 1개에 따른 댓글 전체 조회
    @Query(value="SELECT * FROM comments WHERE post_seq = :id", nativeQuery = true)
    List<Comments> findByPostSeq(@Param("id") int id);
}
