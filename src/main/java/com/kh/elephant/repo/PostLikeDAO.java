package com.kh.elephant.repo;

import com.kh.elephant.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeDAO extends JpaRepository<PostLike,Integer> {
    // 첫번째는 사용할 엔티티 두번째는 primary키의 데이터타입
}
