package com.kh.elephant.repo;

import com.kh.elephant.domain.Board;
import com.kh.elephant.domain.MatchingUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchingUserInfoDAO extends JpaRepository<MatchingUserInfo, Integer> {

}
