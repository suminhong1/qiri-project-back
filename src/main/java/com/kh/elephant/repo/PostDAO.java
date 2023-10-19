package com.kh.elephant.repo;

import com.kh.elephant.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

// 첫번째는 사용할 엔티티 두번째는 primary키의 데이터타입
public interface PostDAO extends JpaRepository<Post, Integer>, QuerydslPredicateExecutor<Post> {



    @Query(value = "SELECT * FROM post WHERE board_seq = :code", nativeQuery = true)
    List<Post>findByBoardCode(int code);


    @Query(value = "update post SET postView = postView+1 WHERE  post_seq = :code", nativeQuery = true)
    List<Post>increaseCount(int code);




    // NativeQuery 대신 JPQL(Java Persistence Query Language) 사용하니까 검색할때
    // 숫자 한자릿수만 검색하면 오류뜨던거 안뜸
    // JPQL은 JPA 엔티티와 더 호환성이 높으며, 데이터베이스 종속성을 줄일 수 있습니다.
    // JPQL을 사용하면 데이터베이스 간 이식성이 향상되며, 어플리케이션을 다양한 데이터베이스에 쉽게 이식할 수 있습니다.
    // JPQL은 객체 지향 쿼리 언어로, 엔티티와 관계를 고려하여 쿼리를 작성할 수 있습니다. JPQL은 엔티티 객체를 반환하므로 결과를 더 쉽게 객체로 매핑할 수 있습니다.
    // JPQL은 JPA가 사용자 입력을 안전하게 처리하도록 설계되어 있으므로 SQL Injection과 같은 보안 문제가 줄어듭니다.
    // JPQL은 객체-관계 매핑 계층을 통해 데이터베이스 쿼리로 변환되기 때문에 추가 오버헤드가 발생할 수 있으며, 최적화에 필요한 세부 정보를 잘 관리해야 합니다.
    // gpt피셜이긴한데...


    // @Query ("SELECT p FROM Post p WHERE p.postTitle LIKE %:keyword%")
    // Containing이 LIKE 연산을 수행하는 쿼리문을 자동으로 생성하고 실행함
    Page<Post> findByPostTitleContaining(@Param("keyword") String keyword, Pageable pageable);


}
