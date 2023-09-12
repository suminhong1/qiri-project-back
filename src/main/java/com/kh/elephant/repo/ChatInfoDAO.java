package com.kh.elephant.repo;

import com.kh.elephant.domain.ChatInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatInfoDAO extends JpaRepository<ChatInfo,Integer> {
}
