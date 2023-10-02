package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PLACE")
public class Place {

    @Id
    @Column(name = "PLACE_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "placeSequence")
    @SequenceGenerator(name = "placeSequence", sequenceName = "SEQ_PLACE", allocationSize = 1)
    private String placeSeq;

    @Column(name = "PLACE_NAME")
    private String placeName;

    @ManyToOne
    @JoinColumn(name = "PLACE_TYPE_SEQ")
    private PlaceType placeType;

}
