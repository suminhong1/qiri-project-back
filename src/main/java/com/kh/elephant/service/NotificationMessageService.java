package com.kh.elephant.service;

import com.kh.elephant.domain.NotificationMessage;
import com.kh.elephant.repo.NotificationMessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationMessageService {

    @Autowired
    private NotificationMessageDAO dao;

    //전체 차단 정보
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
}
