package com.kh.elephant.repo;

import com.kh.elephant.domain.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeDAO extends JpaRepository <CommentLike, Integer> {
}
