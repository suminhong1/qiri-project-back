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
    @ManyToMany
    private int postSeq;

    @Column(name = "COMMENTS_PARENT_SEQ")
    private int commentsParentSeq;

    @Column(name = "USER_ID", length = 200)
    private String userId;

    @Column(name = "COMMENTS_DESC", length = 1000, nullable = false)
    private String commentDescription;

    @Column(name = "COMMENTS_DATE", nullable = false)
    private Date commentDate;

    @Column(name = "SECRET_COMMENTS", length = 1, columnDefinition = "VARCHAR2(1) DEFAULT 'N'", nullable = false)
    private String secretComment;

    @Column(name = "COMMENTS_DELETE", length = 1, columnDefinition = "VARCHAR2(1) DEFAULT 'N'", nullable = false)
    private String commentDelete;
}
