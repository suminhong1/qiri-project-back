package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "PLACE_TYPE")
public class PlaceType {

    @Id
    @Column(name = "PLACE_TYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "placeTypeSequence")
    @SequenceGenerator(name = "placeTypeSequence", sequenceName = "SEQ_PLACE_TYPE", allocationSize = 1)
    private int placeTypeSEQ;

    @Column(name = "PLACE_TYPE_NAME")
    private String placeTypeName;

//    // 생성자
//    public PlaceType(String placeTypeName) {
//        this.placeTypeName = placeTypeName;
//    }

}
