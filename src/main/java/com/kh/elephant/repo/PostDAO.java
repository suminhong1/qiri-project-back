package com.kh.elephant.repo;

import com.kh.elephant.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDAO extends JpaRepository<Post, Integer> {
    // 첫번째는 사용할 엔티티 두번째는 primary키의 데이터타입
}
