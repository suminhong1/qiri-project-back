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
    private int postSEQ; // 시퀀스로 자동 생성되는 post의 시퀀스값

    @Column(name ="post_title")
    private String postTitle; // 게시물 제목

    @Column(name="post_content")
    private String postContent; // 게시물 내용

    @Column(name="post_date")
    private Date postDate; // 게시 날짜

    @Column(name="post_view", columnDefinition = "integer default 0",nullable = false)
    private int postView; // 조회수

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserInfo userInfo; // 유저 정보

    @ManyToOne
    @JoinColumn(name = "place_seq")
    private Place place; // JoinColumn으로 연결된 Place

    @ManyToOne
    @JoinColumn(name="board_seq")
    private Board board; // JoinColumn으로 연결된 Board

    @Column(name = "post_delete")
    private String postDelete; // Delete 컨트롤러로 삭제가 아니라 db에 자료는 남기고 클라이언트에서 안보이게만 하기 위한 필드 변수

    @Column(name = "matched")
    private String matched; // 매치가 성사 됐는지 안됐는지

}
