package com.kh.elephant.domain;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    // post받을때 혹은 던질때... 유저 정보..
    private String token;

    // 게시글 작성 관련 필요 데이터

    private String postTitle;

    private String postContent;

    private int postView;

    private int placeSeq;

    private int boardSeq;
    
    private List<Integer> categoryList;

    private List<String> attachmentList;

    // 게시글 좋아요 관련 필요 데이터

    private int postSeq;

    private String matched;

    private String postNotice;


}
