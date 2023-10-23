package com.kh.elephant.service;

import com.kh.elephant.domain.UserChatRoomInfo;
import com.kh.elephant.repo.UserChatRoomInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserChatRoomInfoService {

    @Autowired
    private UserChatRoomInfoDAO dao;

    public List<UserChatRoomInfo> showAll() {
        return dao.findAll();
    }

    public UserChatRoomInfo show(int code) {
        return dao.findById(code).orElse(null);
    }

    public UserChatRoomInfo create(UserChatRoomInfo userChatRoomInfo) {
        return dao.save(userChatRoomInfo);
    }

    public UserChatRoomInfo update(UserChatRoomInfo userChatRoomInfo) {
        UserChatRoomInfo target = dao.findById(userChatRoomInfo.getUserChatRoomInfoSeq()).orElse(null);
        if(target!=null) {
            return dao.save(userChatRoomInfo);
        }
        return null;
    }

    public UserChatRoomInfo delete(int code) {
        UserChatRoomInfo data = dao.findById(code).orElse(null);
        dao.delete(data);
        return data;
    }
}
