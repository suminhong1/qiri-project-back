package com.kh.elephant.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "MATCHING_USER_INFO")
public class MatchingUserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATCHING_USER_INFO_SEQ")
    private String matchingUserInfoSeq;

    @Column(name = "MATCHING_SEQ")
    private String matchingSeq;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "SCORE")
    private Integer score;

    @Column(name = "MATCHING_SUCCESS")
    private String matchingSuccess;

}
