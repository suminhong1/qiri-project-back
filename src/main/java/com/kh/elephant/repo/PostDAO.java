package com.kh.elephant.repo;

import com.kh.elephant.domain.Post;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

// 첫번째는 사용할 엔티티 두번째는 primary키의 데이터타입
public interface PostDAO extends JpaRepository<Post, Integer>, QuerydslPredicateExecutor<Post> {


    List<Post> findByPostTitleContaining(String keyword, Pageable pageable);
    @Query(value = "SELECT * FROM post WHERE board_seq = :code", nativeQuery = true)
    List<Post>findByBoardCode(int code);

    @Query(value = "SELECT * FROM post WHERE post_title LIKE %:keyword%", nativeQuery = true)
    List<Post>findByTitle(@Param("keyword") String keyword);




}
