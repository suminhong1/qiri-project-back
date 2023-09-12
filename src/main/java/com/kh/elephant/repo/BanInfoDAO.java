package com.kh.elephant.repo;

import com.kh.elephant.domain.BanInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanInfoDAO extends JpaRepository<BanInfo, Integer> {
}
