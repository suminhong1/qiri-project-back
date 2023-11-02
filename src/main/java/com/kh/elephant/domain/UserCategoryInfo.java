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
public class UserCategoryInfo {
    @Id
    @Column(name = "user_category_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "userCategorySequence")
    @SequenceGenerator(name = "userCategorySequence", sequenceName = "SEQ_USER_CATEGORY_INFO", allocationSize = 1)
    private int userCategorySeq;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserInfo userInfo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_seq")
    private Category category;
}
