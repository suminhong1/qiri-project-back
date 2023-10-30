package com.kh.elephant.repo;

import com.kh.elephant.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SearchDAO extends JpaRepository<Post, String> {
    @Query(value = "SELECT po.POST_SEQ AS POST_SEQ, po.POST_TITLE AS POST_TITLE, po.POST_CONTENT AS POST_CONTENT, po.POST_DATE AS POST_DATE, po.POST_VIEW AS POST_VIEW, po.USER_ID AS USER_ID, po.PLACE_SEQ AS PLACE_SEQ, po.BOARD_SEQ AS BOARD_SEQ, po.MATCHED AS MATCHED, po.POST_NOTICE AS POST_NOTICE, po.POST_DELETE AS POST_DELETE, pl.PLACE_NAME AS PLACE_NAME, pt.PLACE_TYPE_NAME AS PLACE_TYPE_NAME, pt.PLACE_TYPE_SEQ AS PLACE_TYPE_SEQ FROM POST po JOIN PLACE pl ON (pl.PLACE_SEQ = po.PLACE_SEQ) JOIN PLACE_TYPE pt ON (pl.PLACE_TYPE_SEQ = pt.PLACE_TYPE_SEQ) WHERE po.POST_TITLE LIKE '%:id%' OR po.POST_CONTENT LIKE '%:id%' OR po.USER_ID LIKE '%:id%' OR pl.PLACE_NAME LIKE '%:id%' OR pt.PLACE_TYPE_NAME LIKE '%:id%'", nativeQuery = true)
    List<Post> findByKeyword(@Param("id") String id);
}
