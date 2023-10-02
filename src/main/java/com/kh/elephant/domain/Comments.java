package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comments {

    @Id
    @Column(name = "COMMENTS_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "commentSequence")
    @SequenceGenerator(name = "commentSequence", sequenceName = "SEQ_COMMENTS", allocationSize = 1)
    private int commentsSeq;


    @ManyToOne
    @JoinColumn(name = "POST_SEQ")
    private Post post;

    @Column(name = "COMMENTS_PARENT_SEQ")
    private int commentsParentSeq;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserInfo userInfo;

    @Column(name = "COMMENTS_DESC")
    private String commentDesc;

    @Column(name = "COMMENTS_DATE")
    private Date commentDate;

    @Column(name = "SECRET_COMMENTS")
    private String secretComment;

    @Column(name = "COMMENTS_DELETE")
    private String commentDelete;
}
