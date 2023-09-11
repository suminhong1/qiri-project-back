package com.kh.elephant.service;

import com.kh.elephant.domain.PostThema;
import com.kh.elephant.domain.UserLike;
import com.kh.elephant.repo.PostThemaDAO;
import com.kh.elephant.repo.UserLikeDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserLikeService {

    @Autowired
    private UserLikeDAO dao;

    public List<UserLike> showAll() {
        return dao.findAll();
    }
    public UserLike show(int code) {
        return dao.findById(code).orElse(null);
    }

    public UserLike create(UserLike userLike){
        return dao.save(userLike);
    }
    public UserLike update(UserLike userLike){
        return dao.save(userLike);
    }

    public UserLike delete(int code) {
        UserLike data = dao.findById(code).orElse(null);
        dao.delete(data);
        return data;
    }
}
