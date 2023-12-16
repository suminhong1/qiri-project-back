package com.kh.elephant.service;

import com.kh.elephant.domain.NotificationMessage;
import com.kh.elephant.repo.NotificationMessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificationMessageService {

    @Autowired
    private NotificationMessageDAO dao;

    public List<NotificationMessage> showAll() {
        return dao.findAll();
    }

    public NotificationMessage show(int code) {
        return dao.findById(code).orElse(null);
    }

    public NotificationMessage create(NotificationMessage notificationMessage) {
        return dao.save(notificationMessage);
    }


    public NotificationMessage update(NotificationMessage notificationMessage) {
        NotificationMessage target = dao.findById(notificationMessage.getNotificationMessageSEQ()).orElse(null);
        if(target!=null) {
            return dao.save(notificationMessage);
        }
        return null;
    }

    public NotificationMessage delete(int code) {
        NotificationMessage data = dao.findById(code).orElse(null);
        dao.delete(data);
        return data;
    }

    @Transactional
    public void deleteByRoomSEQAndUserId(int chatroomSEQ, String userId) {
        dao.deleteByRoomSEQAndUserId(chatroomSEQ, userId);
    }
}
