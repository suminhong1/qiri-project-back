package com.kh.elephant.repo;

import com.kh.elephant.domain.UserChatRoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserChatRoomInfoDAO extends JpaRepository<UserChatRoomInfo, Integer> {

    @Query(value = "SELECT * FROM USER_CHATROOM_INFO WHERE USER_ID = :id", nativeQuery = true)
    List<UserChatRoomInfo> findByUserId(@Param("id") String id);

    @Query(value = "UPDATE USER_CHATROOM_INFO SET LEAVE = 'Y' WHERE USER_ID = :id AND CHATROOM_SEQ = :code", nativeQuery = true)
    int updateLeaveStatus(@Param("id") String id, @Param("code") int code);

    @Query(value = "SELECT COUNT(*) FROM USER_CHATROOM_INFO WHERE CHATROOM_SEQ = :chatroom_seq AND LEAVE = 'N'", nativeQuery = true)
    boolean allUsersLeft(@Param("chatroom_seq") int chatroomSeq);

    @Modifying
    @Query(value = "DELETE FROM USER_CHATROOM_INFO WHERE CHATROOM_SEQ = :chatroom_seq", nativeQuery = true)
    void deleteByChatroomSeq(@Param("chatroom_seq") int chatroomSeq);
}
