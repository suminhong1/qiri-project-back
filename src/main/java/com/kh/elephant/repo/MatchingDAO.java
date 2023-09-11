package com.kh.elephant.repo;

import com.kh.elephant.domain.Matching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchingDAO extends JpaRepository<Matching, String> {
}
