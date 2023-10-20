package com.kh.elephant.repo;

import com.kh.elephant.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChatMessageDAO extends JpaRepository<ChatMessage, Integer> {

    // 어느 채팅방에 보내는 메세지인지 찾기
    @Query(value="SELECT * FROM CHATMESSAGE WHERE CHATROOM_SEQ = :code", nativeQuery = true)
    ChatMessage findByRoomCode(int code);
}
