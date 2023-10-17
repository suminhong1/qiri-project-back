package com.kh.elephant.service;

import com.kh.elephant.domain.Post;
import com.kh.elephant.domain.SignUpDTO;
import com.kh.elephant.domain.UserInfo;
import com.kh.elephant.repo.UserInfoDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserInfoService {

    @Autowired
    private UserInfoDAO dao;

    @Autowired
    private UserCategoryInfoService userCategoryInfoService;

    public List<UserInfo> showAll (){
        return dao.findAll();
    }

    public UserInfo show(String id){
        return dao.findById(id).orElse(null);
    }

    public UserInfo create(UserInfo vo){
        log.info("UserInfo : " + vo);
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

    public UserInfo getByCredentials(String id, String password, PasswordEncoder encoder) {
        UserInfo userInfo = dao.findById(id).orElse(null);
        if (userInfo != null && encoder.matches(password, userInfo.getUserPwd())) {
            return userInfo;
        }
        return null;
    }

    public UserInfo createWithCategories(SignUpDTO signUpDTO) {
        UserInfo userInfo = create(signUpDTO.getUserInfoDTO().toUserInfo()); // assuming 'create' saves the user
        userCategoryInfoService.createAll(signUpDTO.getUserCategories());
        return userInfo;
    }
}
