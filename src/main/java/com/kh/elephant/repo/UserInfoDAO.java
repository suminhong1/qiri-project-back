package com.kh.elephant.repo;

import com.kh.elephant.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserInfoDAO extends JpaRepository<UserInfo,String> {

    @Query(value = "SELECT * FROM USER_INFO WHERE EMAIL = :email", nativeQuery = true)
    UserInfo findByEmail(String email);
}
