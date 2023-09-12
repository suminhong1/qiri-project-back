package com.kh.elephant.service;

import com.kh.elephant.domain.ChatInfo;
import com.kh.elephant.repo.ChatInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatInfoService {

    @Autowired
    private ChatInfoDAO dao;

    public List<ChatInfo> showAll() {
        return dao.findAll();
    }

    public ChatInfo show(int code) {
        return dao.findById(code).orElse(null);
    }

    public ChatInfo create(ChatInfo chatInfo) {
        return dao.save(chatInfo);
    }

    public ChatInfo update(ChatInfo chatInfo) {
        return  dao.save(chatInfo);
    }

    public ChatInfo delete(int code) {
        ChatInfo date = dao.findById(code).orElse(null);
        dao.delete(date);
        return date;
    }
}
