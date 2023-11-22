package com.kh.elephant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {

    private String id;

    private int postSEQ;

    private int commentsSEQ;

    private int chatRoomSEQ;

    private int matchingUserInfoSEQ;

}
