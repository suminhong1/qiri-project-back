package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
public class CategoryType {
    @Id
    @Column(name = "ct_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ctSequence")
    @SequenceGenerator(name = "ctSequence", sequenceName = "SEQ_CATEGORY_TYPE", allocationSize = 1)
    private int ctSeq;

    @Column(name = "ct_name")
    private String ctName;
}
