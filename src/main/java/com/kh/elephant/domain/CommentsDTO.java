package com.kh.elephant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentsDTO {
    private Post post;
    private int commentsSeq;
    private Integer CommentsParentSeq;
    private UserInfo userInfo;
    private String commentDesc;
    @Builder.Default
    private List<Comments> replies = new ArrayList<>();
}
