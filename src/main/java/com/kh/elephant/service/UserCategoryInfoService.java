package com.kh.elephant.service;

import com.kh.elephant.domain.UserCategoryInfo;
import com.kh.elephant.repo.UserCategoryInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCategoryInfoService {

    @Autowired
    private UserCategoryInfoDAO dao;

    public List<UserCategoryInfo> showAll() { return dao.findAll(); }

    public UserCategoryInfo show(int code) { return dao.findById(Integer.valueOf(String.valueOf(code))).orElse(null); }

    public UserCategoryInfo create(UserCategoryInfo userCategoryInfo) { return dao.save(userCategoryInfo); }

    public List<UserCategoryInfo> createAll(List<UserCategoryInfo> userCategoryInfoList) {
        return dao.saveAll(userCategoryInfoList);
    }

    public UserCategoryInfo update(UserCategoryInfo userCategoryInfo) { return dao.save(userCategoryInfo); }

    public UserCategoryInfo delete(int code) {

        UserCategoryInfo data = dao.findById(Integer.valueOf(String.valueOf(code))).orElse(null);
        dao.delete(data);
        return data;
    }

}
