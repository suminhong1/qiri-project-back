package com.kh.elephant.repo;

import com.kh.elephant.domain.MatchingCategoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MatchingCategoryInfoDAO extends JpaRepository<MatchingCategoryInfo, Integer> {


    @Query(value = "SELECT * FROM MATCHING_CATEGORY_INFO JOIN CATEGORY USING(CATEGORY_SEQ) WHERE CT_SEQ = :code", nativeQuery = true)
    List<MatchingCategoryInfo> findByCTSEQ(@Param("code") int code);


    @Query(value = "SELECT * FROM matching_category_info WHERE post_seq = :id",nativeQuery = true)
    List<MatchingCategoryInfo> findByPostSeq(@Param("id") int id);
}
