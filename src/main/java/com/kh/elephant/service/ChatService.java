package com.kh.elephant.service;

import com.kh.elephant.repo.ChatMessageDAO;
import com.kh.elephant.repo.ChatRoomDAO;
import com.kh.elephant.repo.UserChatRoomInfoDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;


@Slf4j
@Service
public class ChatService {

    @Autowired
    private UserChatRoomInfoDAO userChatRoomInfoDAO;

    @Autowired
    private ChatRoomDAO chatRoomDAO;

    @Autowired
    private ChatMessageDAO chatMessageDAO;



    @Transactional
    public void leaveChatRoom(int chatroomSeq) throws Exception {

        // 모든 유저의 LEAVE 값이 'Y'인지 확인(SELECT 쿼리문)
        int result = userChatRoomInfoDAO.allUsersLeft(chatroomSeq);
        if (result == 0) {
            deleteChatMessages(chatroomSeq);
            deleteUserChatRoomInfo(chatroomSeq);
            deleteChatRoom(chatroomSeq);
        }
    }

    @Transactional
    public void deleteChatMessages(int chatroomSeq) {
        chatMessageDAO.deleteByRoomSEQ(chatroomSeq);
    }

    @Transactional
    public void deleteUserChatRoomInfo(int chatroomSeq) {
        userChatRoomInfoDAO.deleteByChatroomSeq(chatroomSeq);
    }

    @Transactional
    public void deleteChatRoom(int chatroomSeq) {
        chatRoomDAO.deleteByRoomSEQ(chatroomSeq);

    }
}
