package com.kh.elephant.repo;

import com.kh.elephant.domain.NotificationMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationMessageDAO extends JpaRepository<NotificationMessage, Integer> {
}
