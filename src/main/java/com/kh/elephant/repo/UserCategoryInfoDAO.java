package com.kh.elephant.repo;

import com.kh.elephant.domain.ChatUserInfo;
import com.kh.elephant.domain.UserCategoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCategoryInfoDAO extends JpaRepository<UserCategoryInfo, Integer> {
}
