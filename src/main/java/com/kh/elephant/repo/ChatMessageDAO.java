package com.kh.elephant.repo;

import com.kh.elephant.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChatMessageDAO extends JpaRepository<ChatMessage, Integer> {

}
