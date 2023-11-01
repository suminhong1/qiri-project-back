package com.kh.elephant.repo;

import com.kh.elephant.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public interface ChatRoomDAO extends JpaRepository<ChatRoom, Integer> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM CHATROOM WHERE CHATROOM_SEQ = :id", nativeQuery = true)
    int deleteByRoomSEQ(@Param("id") int id);

}



