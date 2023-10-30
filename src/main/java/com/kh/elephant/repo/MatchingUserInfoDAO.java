package com.kh.elephant.repo;

import com.kh.elephant.domain.MatchingUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MatchingUserInfoDAO extends JpaRepository<MatchingUserInfo, Integer> {



    Optional<MatchingUserInfo> findByUserInfo_UserIdAndPost_PostSEQ(String userId, int postSEQ);

    // 수락정보 포스트 공용SEQ 체킹용

}
