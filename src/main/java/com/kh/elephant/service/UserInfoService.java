package com.kh.elephant.service;

import com.kh.elephant.domain.Post;
import com.kh.elephant.domain.UserInfo;
import com.kh.elephant.repo.UserInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoDAO dao;

    public List<UserInfo> showAll (){
        return dao.findAll();
    }
    public UserInfo show(String id){

        return dao.findById(id).orElse(null);
    }

    public UserInfo create(UserInfo vo){
        return dao.save(vo);
    }

    public UserInfo update(UserInfo vo) {
        UserInfo target = dao.findById(vo.getUserId()).orElse(null);
        if (target != null) {
            return dao.save(vo);
        }
        return null;
    }

    public UserInfo delete(String id){
        UserInfo userInfo = dao.findById(id).orElse(null);
        dao.delete(userInfo);
        return userInfo;
    }
}
