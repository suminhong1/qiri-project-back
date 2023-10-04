package com.kh.elephant.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
public class Post {

    @Id
    @Column(name="post_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "postSequence")
    @SequenceGenerator(name="postSequence", sequenceName = "SEQ_POST",allocationSize = 1)
    private int postSEQ;

    @Column(name ="post_title")
    private String postTitle;

    @Column(name="post_content")
    private String postContent;

    @Column(name="post_date")
    private Date postDate;

    @Column(name="post_view")
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

    @ManyToOne
    @JoinColumn(name="matching_seq")
    private Matching matching;

    @Column(name="post_notice")
    private String postNotice;

    @Column(name = "post_delete")
    private String postDelete;

}
