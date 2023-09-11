package com.kh.elephant.repo;

import com.kh.elephant.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardDAO extends JpaRepository<Board, Integer> {
}
