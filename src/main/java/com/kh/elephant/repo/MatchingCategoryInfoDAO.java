package com.kh.elephant.repo;

import com.kh.elephant.domain.MatchingCategoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchingCategoryInfoDAO extends JpaRepository<MatchingCategoryInfo, Integer> {
}
