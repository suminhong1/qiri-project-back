package com.kh.elephant.service;

import com.kh.elephant.domain.UserCategoryInfo;
import com.kh.elephant.domain.UserPlaceInfo;
import com.kh.elephant.repo.UserPlaceInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserPlaceInfoService {

    @Autowired
    private UserPlaceInfoDAO dao;

    public List<UserPlaceInfo> showAll() { return dao.findAll(); }

    public UserPlaceInfo show(int code) { return dao.findById(Integer.valueOf(String.valueOf(code))).orElse(null); }

    public UserPlaceInfo create(UserPlaceInfo userPlaceInfo) { return dao.save(userPlaceInfo); }

    public UserPlaceInfo update(UserPlaceInfo userPlaceInfo) { return dao.save(userPlaceInfo); }

    public UserPlaceInfo delete(int code) {

        UserPlaceInfo data = dao.findById(Integer.valueOf(String.valueOf(code))).orElse(null);
        dao.delete(data);
        return data;
    }

}
