package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MATCHING_USER_INFO")
public class MatchingUserInfo {

    @Id
    @Column(name = "MATCHING_USER_INFO_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "matchingUserInfoSequence")
    @SequenceGenerator(name = "matchingUserInfoSequence", sequenceName = "SEQ_MATCHING_USER_INFO", allocationSize = 1)
    private String matchingUserInfoSeq;

    @ManyToOne
    @JoinColumn(name = "MATCHING_SEQ")
    private Matching matching;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserInfo userInfo;

    @Column(name = "SCORE")
    private Integer score;

    @Column(name = "MATCHING_SUCCESS")
    private String matchingSuccess;

}
