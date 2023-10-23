package com.kh.elephant.domain;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    // post받을때 혹은 던질때... 유정정보..
    private String token;
    
    // 게시글 작성 관련 필요 데이터

    private String postTitle;

    private String postContent;

    private String postUrl;

    private int placeSeq;

//    private int postThemaSeq;
    private int postView;

    private int boardSeq;

    private int categoryType1;
    private int categoryType2;
    private int categoryType3;
    private int categoryType4;
    private int categoryType5;




}
