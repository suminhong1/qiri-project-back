package com.kh.elephant.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "PLACE_TYPE")
public class PlaceType {

    @Id
    @Column(name = "PLACE_TYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "placeTypeSequence")
    @SequenceGenerator(name = "placeTypeSequence", sequenceName = "SEQ_PLACE_TYPE", allocationSize = 1)

    private String placeTypeSeq;

    @Column(name = "PLACE_TYPE_NAME")
    private String placeTypeName;

}
