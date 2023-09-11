package com.kh.elephant.repo;

import com.kh.elephant.domain.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikeDAO extends JpaRepository<UserLike, Integer> {

}
