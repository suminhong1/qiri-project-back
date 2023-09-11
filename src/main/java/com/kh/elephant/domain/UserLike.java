package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserLike {

    @Id
    @Column(name = "like_up_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "likeUpSeq")
    @SequenceGenerator(name = "likeUpSeq", sequenceName = "LIKE_UP_SEQ", allocationSize = 1)
    private int likeUpSeq;

    @Column(name = "like_up_date")
    private Date likeUpDate;

    @ManyToOne
    @JoinColumn(name = "like_up_user", referencedColumnName = "userId")
    private UserInfo likeUpUser;  // UserInfo 엔터티와의 관계

    @ManyToOne
    @JoinColumn(name = "like_up_target", referencedColumnName = "userId")
    private UserInfo likeUpTarget;  // UserInfo 엔터티와의 관계
}
