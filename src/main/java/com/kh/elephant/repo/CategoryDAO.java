package com.kh.elephant.repo;

import com.kh.elephant.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryDAO extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT * FROM CATEGORY WHERE CT_SEQ = :code", nativeQuery = true)
    List<Category> findByCTSEQ(@Param("code") int code);
}
