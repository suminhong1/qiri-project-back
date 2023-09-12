package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert
public class BanInfo {

    @Id
    @Column(name = "ban_info_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "banInfoSequence")
    @SequenceGenerator(name = "banInfoSequence", sequenceName = "SEQ_BAN_INFO", allocationSize = 1)
    private int banInfoSeq;

    @ManyToOne
    @JoinColumn(name = "ban_id")
    private UserInfo userInfo;

    @Column(name = "ban_start")
    private Date banStart;
    @Column(name = "ban_end")
    private Date banEnd;
    @Column(name = "ban_reason")
    private String banReason;
}
