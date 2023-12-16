package com.kh.elephant.repo;

import com.kh.elephant.domain.NotificationMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface NotificationMessageDAO extends JpaRepository<NotificationMessage, Integer> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM NOTIFICATION_MESSAGE WHERE CHATROOM_SEQ = :roomSEQ AND USER_ID = :id", nativeQuery = true)
    int deleteByRoomSEQAndUserId(@Param("roomSEQ") int roomSEQ, @Param("id") String id);
}
