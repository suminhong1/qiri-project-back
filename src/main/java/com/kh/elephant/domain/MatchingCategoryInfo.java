package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@DynamicInsert
@Builder
public class MatchingCategoryInfo {

    @Id // 기본키
    @Column(name = "matching_category_seq") // 기본키의 컬럼명

    // 시퀀스 matchingCategorySequence이름으로 식별 후 자동 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "matchingCategorySequence")

    // 생성기의 이름 @GeneratedValue를 참조할때 사용함 그리고 DB에서 사용할 이름을 설정 시퀀스가 생성될때 1씩 증가하도록 설정
    @SequenceGenerator(name="matchingCategorySequence", sequenceName = "SEQ_MATCHING_CATEGORY_INFO",allocationSize = 1)

    private int matchingCategorySEQ; // matchingCategorySEQ를 Primary Key로 선언

    @ManyToOne // 일대다(1:N) 관계 설정
    @JoinColumn(name = "post_seq") // 외래키 컬럼명
    private Post post; // JoinColumn으로 Post 객체를 필드에 선언

    @ManyToOne // 일대다(1:N) 관계 설정
    @JoinColumn(name = "category_seq") // 외래키 컬럼명
    private Category category; // JoinColumn으로 Category 객체를 필드에 선언
}
