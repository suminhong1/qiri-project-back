package com.kh.elephant.repo;

import com.kh.elephant.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostLikeDAO extends JpaRepository<PostLike,Integer> {
    // 첫번째는 사용할 엔티티 두번째는 primary키의 데이터타입

    // 좋아요 추가
    @Modifying
    @Query(value = "UPDATE post SET post_like_seq =(post_like_seq + 1) WHERE post_seq = :postCode ", nativeQuery = true)
    int increasePostlike(int postCode);

    // 좋아요 중복 방지
    @Query(value="SELECT * FROM post_like  WHERE user_info = :id  AND post_like_seq = :likeCode", nativeQuery = true)
    PostLike postLikeByUserAndPost(String id, int likeCode);

    @Query(value = "UPDATE post SET post_like_seq =(post_like_seq - 1) WHERE post_seq = :postCode ",nativeQuery = true)
    int decreasePostlike(int postCode);
}
