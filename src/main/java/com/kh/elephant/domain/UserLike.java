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
public class UserLike {

    @Id
    @Column(name = "like_up_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "likeUpSequence")
    @SequenceGenerator(name = "likeUpSequence", sequenceName = "SEQ_USER_LIKE", allocationSize = 1)
    private int likeUpSeq;

    @Column(name = "like_up_date")
    private Date likeUpDate;

    @ManyToOne
    @JoinColumn(name = "like_up_user", referencedColumnName = "user_Id")
    private UserInfo likeUpUser;

    @ManyToOne
    @JoinColumn(name = "like_up_target", referencedColumnName = "user_Id")
    private UserInfo likeUpTarget;
}
