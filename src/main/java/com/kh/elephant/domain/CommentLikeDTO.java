package com.kh.elephant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentLikeDTO {
    private int clSEQ;
    private Date clDate;
    private Comments comments;
    private UserInfo userInfo;
}
