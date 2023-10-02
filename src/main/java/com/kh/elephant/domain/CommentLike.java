package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class CommentLike {

    @Id
    @Column(name="cl_seq")
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator = "clSeq")
    @SequenceGenerator(name = "clSeq", sequenceName = "CL_SEQ", allocationSize = 1)
    private int clSeq;

    @Column(name = "cl_date")
    private Date clDate;

    @Column(name = "comment_seq")
    private int commentSeq;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserInfo userInfo;
}
