package com.kh.elephant.repo;

import com.kh.elephant.domain.ChatUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatUserInfoDAO extends JpaRepository<ChatUserInfo, Integer> {
}
