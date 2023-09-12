package com.kh.elephant.service;

import com.kh.elephant.domain.MatchingUserInfo;
import com.kh.elephant.repo.MatchingDAO;
import com.kh.elephant.repo.MatchingUserInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
}
