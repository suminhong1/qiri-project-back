package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class Comments {

    @Id
    @Column(name = "COMMENTS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commentSequence")
    @SequenceGenerator(name = "commentSequence", sequenceName = "SEQ_COMMENTS", allocationSize = 1)
    private int commentsSeq;

    @JoinColumn(name = "POST_SEQ")
    @ManyToOne
    private int postSeq;

    @Column(name = "COMMENTS_PARENT_SEQ")
    private int commentsParentSeq;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private String userId;

    @Column(name = "COMMENTS_DESC")
    private String commentDescription;

    @Column(name = "COMMENTS_DATE")
    private Date commentDate;

    @Column(name = "SECRET_COMMENTS")
    private String secretComment;

    @Column(name = "COMMENTS_DELETE")
    private String commentDelete;
}
