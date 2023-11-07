package com.kh.elephant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MatchingCategoryInfoDTO {

    private int postSEQ; // post시퀀스 값을 int로 받아서 저장하기 위한 필드 변수

    private List<Category> categories; // 선택한 카테고리를 List로 받기 위한 필드 변수
}
