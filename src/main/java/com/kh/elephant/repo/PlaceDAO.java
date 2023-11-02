package com.kh.elephant.repo;

import com.kh.elephant.domain.Place;
import com.kh.elephant.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceDAO extends JpaRepository<Place, Integer> {

    @Query(value = "SELECT * FROM PLACE WHERE POST_SEQ =:id", nativeQuery = true)
    List<Place>findByPostSeq(@Param("id") int id);
}
