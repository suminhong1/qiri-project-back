package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserPlaceInfo{
    @Id
    @Column(name = "user_place_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "userPlaceSequence")
    @SequenceGenerator(name = "userPlaceSequence", sequenceName = "SEQ_USER_PLACE_INFO", allocationSize = 1)
    private int userPlaceSeq;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserInfo userInfo;

    @ManyToOne
    @JoinColumn(name = "place_seq")
    private Place place;
}
