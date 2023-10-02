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
public class Category {

    @Id
    @Column(name = "category_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "categorySequence")
    @SequenceGenerator(name = "categorySequence", sequenceName = "SEQ_CATEGORY", allocationSize = 1)
    private int categorySeq;

    @Column(name = "category_name")
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "ct_seq")
    private CategoryType categoryType;

}
