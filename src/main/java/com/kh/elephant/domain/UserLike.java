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
    @SequenceGenerator(name = "likeUpSequence", sequenceName = "LIKE_UP_SEQ", allocationSize = 1)
    private int likeUpSeq;

    @Column(name = "like_up_date")
    private Date likeUpDate;

    @ManyToOne
    @JoinColumn(name = "like_up_user", referencedColumnName = "userId")
    private UserInfo likeUpUser;

    @ManyToOne
    @JoinColumn(name = "like_up_target", referencedColumnName = "userId")
    private UserInfo likeUpTarget;
}
