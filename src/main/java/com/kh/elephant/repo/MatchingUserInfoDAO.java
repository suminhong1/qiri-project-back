package com.kh.elephant.repo;

import com.kh.elephant.domain.MatchingUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface MatchingUserInfoDAO extends JpaRepository<MatchingUserInfo, Integer> {


    // 수락정보 포스트 공용SEQ 체킹용
    Optional<MatchingUserInfo> findByUserInfo_UserIdAndPost_PostSEQ(String userId, int postSEQ);




    @Query(value = "SELECT * FROM MATCHING_USER_INFO WHERE USER_ID !=:id AND POST_SEQ =:postSEQ AND MATCHING_ACCEPT !='H'", nativeQuery = true)
    List<MatchingUserInfo> findMatchingByPostSEQ(@Param("id") String id, @Param("postSEQ") int postSEQ);


    @Transactional
    @Modifying
    @Query(value = "UPDATE MATCHING_USER_INFO SET MATCHING_ACCEPT = 'Y' WHERE POST_SEQ = :code AND USER_ID = :id", nativeQuery = true)
    int matchingAccept(@Param("code") int code, @Param("id") String id);
    @Transactional
    @Modifying
    @Query(value = "UPDATE MATCHING_USER_INFO SET MATCHING_ACCEPT = 'H' WHERE POST_SEQ = :code AND USER_ID = :id", nativeQuery = true)
    int hideMachingUser(@Param("code") int code, @Param("id") String id);

    @Query(value = "SELECT * FROM MATCHING_USER_INFO WHERE POST_SEQ = :code AND MATCHING_ACCEPT = 'Y'", nativeQuery = true)
    List<MatchingUserInfo> findAccept (@Param("code") int code);

    @Query(value = "SELECT * FROM MATCHING_USER_INFO WHERE USER_ID = :id AND MATCHING_ACCEPT = 'Y' AND POST_REVIEW = 'N'", nativeQuery = true)
    List<MatchingUserInfo> findByUserIdForPostReview(@Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE MATCHING_USER_INFO SET POST_REVIEW = 'Y' WHERE POST_SEQ = :postSEQ", nativeQuery = true)
    int postReview(@Param("postSEQ") int postSEQ);
}
