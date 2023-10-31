package com.kh.elephant.repo;

import com.kh.elephant.domain.MatchingUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MatchingUserInfoDAO extends JpaRepository<MatchingUserInfo, Integer> {


    // 수락정보 포스트 공용SEQ 체킹용
    Optional<MatchingUserInfo> findByUserInfo_UserIdAndPost_PostSEQ(String userId, int postSEQ);

    


    // 같은 포스트seq로 확인하기
    public List<MatchingUserInfo> findByPost_PostSEQ(int postSEQ);
}
