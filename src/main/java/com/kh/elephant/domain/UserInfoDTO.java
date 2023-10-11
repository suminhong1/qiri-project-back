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
public class UserInfoDTO {
    private String token;
    private String id;
    private String pwd;
    private String nickname;
    private Place place;
    private Integer age;
    private String gender;
    private String phone;
    private String email;
    private String profileImg;
    private String statusMessage;
    private String hasPartner;
    private String bloodType;
    private String mbti;
    private Date birthday;
}
