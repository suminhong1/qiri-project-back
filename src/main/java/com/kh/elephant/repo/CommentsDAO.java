package com.kh.elephant.repo;

import com.kh.elephant.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsDAO extends JpaRepository<Comments, Integer> {
}
