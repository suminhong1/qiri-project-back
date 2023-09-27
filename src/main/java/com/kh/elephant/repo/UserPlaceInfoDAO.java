package com.kh.elephant.repo;

import com.kh.elephant.domain.UserPlaceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPlaceInfoDAO extends JpaRepository<UserPlaceInfo, Integer> {
}
