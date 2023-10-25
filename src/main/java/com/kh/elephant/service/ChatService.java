package com.kh.elephant.service;

import com.kh.elephant.repo.ChatMessageDAO;
import com.kh.elephant.repo.ChatRoomDAO;
import com.kh.elephant.repo.UserChatRoomInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatService {

    @Autowired
    private UserChatRoomInfoDAO userChatRoomInfoDAO;

    @Autowired
    private ChatRoomDAO chatRoomDAO;

    @Autowired
    private ChatMessageDAO chatMessageDAO;

    @Transactional
    public void leaveChatRoom(int chatroomSeq) {
        // 모든 유저의 LEAVE 값이 'Y'인지 확인
        boolean allUsersLeft = userChatRoomInfoDAO.allUsersLeft(chatroomSeq);

        if (allUsersLeft) {
            // 채팅 메세지 테이블에서 해당 채팅방 관련 데이터 삭제
            chatMessageDAO.deleteByRoomSEQ(chatroomSeq);

            // 유저 채팅방 정보 테이블에서 해당 채팅방 정보 삭제
            userChatRoomInfoDAO.deleteByChatroomSeq(chatroomSeq);

        }

        // 채팅방 테이블에서 해당 채팅방 데이터 삭제
        chatRoomDAO.deleteByRoomSEQ(chatroomSeq);


    }
}
