package com.kh.elephant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
    private String token; // 토큰
    private String id; // 아이디
    private String pwd; // 비밀번호
    private String name; // 이름
    private String nickname; // 닉네임
    private PlaceType placeType; // 지역
    private Integer age; // 나이
    private String gender; // 성별
    private String phone; // 휴대전화번호
    private String email; // 이메일
    private String statusMessage; // 상태메시지
    private String hasPartner; // 애인여부
    private String bloodType; // 혈액형
    private String mbti; // mbti
    private Date birthday; // 생일
 //   private String profileImg;

    public UserInfo toUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(this.id); // 아이디
        userInfo.setUserPwd(this.pwd); // 비밀번호
        userInfo.setUserName(this.name); // 이름
        userInfo.setUserNickname(this.nickname); // 닉네임
        userInfo.setPlaceType(this.placeType); // 지역
        userInfo.setAge(this.age); // 나이
        userInfo.setGender(this.gender); // 성별
        userInfo.setPhone(this.phone); // 휴대전화번호
        userInfo.setEmail(this.email); // 이메일
        userInfo.setStatusMessage(this.statusMessage); // 상태메시지
        userInfo.setHasPartner(this.hasPartner); // 애인여부
        userInfo.setBloodType(this.bloodType); // 혈액형
        userInfo.setMbti(this.mbti); // mbti
        userInfo.setBirthday(this.birthday); // 생일
   //     userInfo.setProfileImg(this.profileImg);

        return userInfo;
    }
}
