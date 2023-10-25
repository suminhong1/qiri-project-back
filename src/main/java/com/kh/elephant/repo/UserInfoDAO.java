package com.kh.elephant.repo;

import com.kh.elephant.domain.UserChatRoomInfo;
import com.kh.elephant.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserInfoDAO extends JpaRepository<UserInfo,String> {

    @Query(value = "SELECT * FROM USER_INFO WHERE USER_NICKNAME = :id", nativeQuery = true)
    UserInfo findByNickname(@Param("id") String id);
}
