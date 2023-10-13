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
    private String token; // 토큰
    private String id; // 아이디
    private String pwd; // 비밀번호
    private String name; // 이름  없음
    private String nickname; // 닉네임
    private Place place; // 지역
    private Integer age; // 나이
    private String gender; // 성별
    private String phone; // 휴대전화번호
    private String email; // 이메일
    private String profileImg; // 프로필사진 없음
    private String statusMessage; // 상태메시지 없음
    private String hasPartner; // 애인여부 없음
    private String bloodType; // 혈액형
    private String mbti; // mbti
    private Date birthday; // 생일
}
