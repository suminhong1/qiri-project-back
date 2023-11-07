package com.kh.elephant.repo;

import com.kh.elephant.domain.Place;
import com.kh.elephant.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceDAO extends JpaRepository<Place, Integer> {

    //  SQL 쿼리문을 사용해서 참조키로 참조 되어있는 POST_SEQ를 PLACE 테이블에서 검색해서 결과를 반환함 :id는 이 쿼리문을 호출할때 POST_SEQ값으로 대체됨
    @Query(value = "SELECT * FROM PLACE WHERE POST_SEQ =:id", nativeQuery = true)
    List<Place>findByPostSeq(@Param("id") int id);
}
