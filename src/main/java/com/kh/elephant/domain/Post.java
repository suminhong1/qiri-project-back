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

    @Column(name="post_attachment")
    private String postAttachment;

    @Column(name="post_url")
    private String postUrl;

    @Column(name="post_notice")
    private String postNotice;

    @Column(name="matching_accept")
    private String matchingAccept;

    @Column(name="post_delete")
    private String postDelete;

    @Column(name="post_user_id")
    private String postUserId;


    @ManyToOne
    @JoinColumn(name="category_seq")
    private Category category;

    @ManyToOne
    @JoinColumn(name="place_seq")
    private Place place;

    @ManyToOne
    @JoinColumn(name="post_thema_seq")
    private PostLike postThema;

    @ManyToOne
    @JoinColumn(name="board_seq")
    private Board board;

    @ManyToOne
    @JoinColumn(name="matching_seq")
    private Matching matching;

}
