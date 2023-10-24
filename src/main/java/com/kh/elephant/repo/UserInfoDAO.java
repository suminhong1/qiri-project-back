package com.kh.elephant.repo;

import com.kh.elephant.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoDAO extends JpaRepository<UserInfo,String> {

}

