package com.kh.elephant.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "MATCHING")
public class Matching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATCHING_SEQ")
    private String matchingSeq;

    @Column(name = "POST_SEQ")
    private String postSeq;

    @Column(name = "CHATROOM_SEQ")
    private int chatroomSeq;

    @Column(name = "MATCHING_APPOINTMENT")
    private Date matchingAppointment;

    @Column(name = "MATCHING_RESULT")
    private String matchingResult;

    @Column(name = "MATCHING_DATE")
    private Date matchingDate;

    @Column(name = "UNMATCHING_DATE")
    private Date unmatchingDate;

}
