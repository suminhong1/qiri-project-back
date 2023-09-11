package com.kh.elephant.service;

import com.kh.elephant.domain.MatchingUserInfo;
import com.kh.elephant.repo.MatchingDAO;
import com.kh.elephant.repo.MatchingUserInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class MatchingUserInfoService {

    @Autowired
    private MatchingUserInfoDAO dao;


    public MatchingUserInfo getMatchingUserInfo(Long matchingUserInfoSeq) {
        return null;
    }

    public MatchingUserInfo createMatchingUserInfo(MatchingUserInfo matchingUserInfo) {
        return null;
    }
}
