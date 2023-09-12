package com.kh.elephant.service;

import com.kh.elephant.domain.ChatUserInfo;
import com.kh.elephant.repo.ChatUserInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatUserInfoService {

    @Autowired
    private ChatUserInfoDAO dao;

    public List<ChatUserInfo> showAll() {
        return dao.findAll();
    }

    public ChatUserInfo show(int code) {
        return dao.findById(code).orElse(null);
    }

    public ChatUserInfo create(ChatUserInfo chatUserInfo) {
        return dao.save(chatUserInfo);
    }

    public ChatUserInfo update(ChatUserInfo chatUserInfo) {
        return  dao.save(chatUserInfo);
    }

    public ChatUserInfo delete(int code) {
        ChatUserInfo date = dao.findById(code).orElse(null);
        dao.delete(date);
        return date;
    }
}
