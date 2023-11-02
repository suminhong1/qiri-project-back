package com.kh.elephant.service;

import com.kh.elephant.domain.MatchingUserInfo;
import com.kh.elephant.repo.MatchingUserInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchingUserInfoService {

    @Autowired
    private MatchingUserInfoDAO dao;

    public List<MatchingUserInfo> showAll() {
        return dao.findAll();
    }

    public MatchingUserInfo show(int code) { return dao.findById(code).orElse(null); }

    public MatchingUserInfo create(MatchingUserInfo matchingUserInfo) { return dao.save(matchingUserInfo); }

    public MatchingUserInfo update(MatchingUserInfo matchingUserInfo) { return dao.save(matchingUserInfo); }

    public MatchingUserInfo delete(int code) {
        MatchingUserInfo data = dao.findById(code).orElse(null);
        dao.delete(data);
        return data;
    }

    // 수락정보 포스트 공용SEQ 체킹용
    public Optional<MatchingUserInfo> findByUserIdAndPostSEQ(String userId, int postSEQ) {
        return dao.findByUserInfo_UserIdAndPost_PostSEQ(userId, postSEQ);
    }

    // 매칭 정보 postSEQ확인
    public List<MatchingUserInfo> findByPostSEQ(int postSEQ) {
        return dao.findByPost_PostSEQ(postSEQ);
    }

    public int matchingAccept(int code, String id) {
      return dao.matchingAccept(code, id);
    }


}
