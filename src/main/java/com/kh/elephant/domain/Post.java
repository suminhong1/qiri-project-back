package com.kh.elephant.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "post")
public class Post {

    @Id
    @Column(name="post_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "postSequence")
    @SequenceGenerator(name="postSequence", sequenceName = "SEQ_POST",allocationSize = 1, initialValue = 1)
    private int postSEQ;

    @Column(name ="post_title")
    private String postTitle;

    @Column(name="post_content")
    private String postContent;

    @Column(name="post_date")
    private Date postDate;

    @Column(name="post_view", columnDefinition = "integer default 0",nullable = false)
    private int postView;

    @Column(name="post_url")
    private String postUrl;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserInfo userInfo;

    @ManyToOne
    @JoinColumn(name = "place_seq")
    private Place place;

    @ManyToOne
    @JoinColumn(name="post_thema_seq")
    private PostThema postThema;

    @ManyToOne
    @JoinColumn(name="board_seq")
    private Board board;

    @Column(name = "matched")
    private String matched;

    @Column(name="post_notice")
    private String postNotice;

    @Column(name = "post_delete")
    private String postDelete;

    // 5개 추가.. 우리가 고르는 카테고리 타입 5개
    // private int type1; .....



}
