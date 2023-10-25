package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Entity
@Builder
public class PostLike {

    @Id
    @Column(name="post_like_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "postLikeSequence")
    @SequenceGenerator(name="postLikeSequence", sequenceName = "SEQ_POST_LIKE",allocationSize = 1)
    private int postLikeSeq;

    @Column(name="pl_date")
    private Date plDate;
    
    @ManyToOne
    @JoinColumn(name="post_seq")
    private Post post;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserInfo userInfo;
}
