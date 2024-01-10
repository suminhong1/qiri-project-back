package com.kh.elephant.controller;

import com.kh.elephant.domain.NotificationMessage;
import com.kh.elephant.service.NotificationMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/qiri/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Slf4j
public class NotificationMessageController {

    @Autowired
    NotificationMessageService nmService;

    // 나의 모든 알림목록 가져오기
    @GetMapping("/public/notify/{id}")
    public ResponseEntity<List<NotificationMessage>> findByUserId(@PathVariable String id) {
        try {
            List<NotificationMessage> notificationMessages = nmService.findByUserId(id);

            // 시간을 기준으로 내림차순 정렬
            Collections.sort(notificationMessages, Comparator.comparing(NotificationMessage::getSentTime).reversed());
            return ResponseEntity.status(HttpStatus.OK).body(notificationMessages);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    // 미확인 알림 갯수 가져오기
    @GetMapping("/public/unread_notify/{id}")
    public ResponseEntity<Integer> unreadNotify(@PathVariable String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(nmService.unreadNotify(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 내 알림 전체 삭제
    @DeleteMapping("/public/delete_notify/{id}")
    public ResponseEntity deleteNotify(@PathVariable String id) {
        try{
            nmService.deleteNotify(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 알림 확인 처리
    @PutMapping("/public/check_notify/{id}")
    public ResponseEntity notifyCheck(@PathVariable String id) {
        try {
            nmService.notifyCheck(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
