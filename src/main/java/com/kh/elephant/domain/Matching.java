package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MATCHING")
public class Matching {
    @Id
    @Column(name = "MATCHING_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "matchingSequence")
    @SequenceGenerator(name = "matchingSequence", sequenceName = "SEQ_MATCHING", allocationSize = 1)
    private int matchingSeq;

    @ManyToOne
    @JoinColumn(name = "POST_SEQ")
    private Post post;

    @Column(name = "MATCHING_RESULT")
    private String matchingResult;


}
