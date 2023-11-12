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
public class ChatDTO {

    private String id;

    private String applicantId;

    private String nickname;

    private int chatRoomSEQ;

    private String message;

    private int postSEQ;

    private String joinMessageSent;

}
