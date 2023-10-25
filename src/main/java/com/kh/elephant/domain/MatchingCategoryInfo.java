package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@Builder
public class MatchingCategoryInfo {

    @Id
    @Column(name = "matching_category_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "matchingCategorySequence")
    @SequenceGenerator(name="matchingCategorySequence", sequenceName = "SEQ_MATCHING_CATEGORY",allocationSize = 1)
    private int matchingCategorySEQ;

    @ManyToOne
    @JoinColumn(name = "post_seq")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "category_seq")
    private Category category;
}
