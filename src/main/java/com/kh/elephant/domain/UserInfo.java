package com.kh.elephant.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
public class UserInfo {

    @Id
    @Column(name="user_id")
    private String userId;

    @Column(name="user_pwd")
    private String userPwd;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "age")
    private int age;

    @Column(name="gender")
    private String gender;

    @Column(name = "place")
    private String place;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "profile_img")
    private String profileImg;

    @Column(name = "status_message")
    private String statusMessage;

    @Column(name = "lover")
    private String lover;

    @Column(name = "blood_type")
    private String bloodType;

    @Column(name = "mbti")
    private String mbti;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "user_subscription")
    private Date userSubscription;

    @Column(name = "user_likes")
    private int userLikes;

    @Column(name = "user_rating")
    private int userRating;

    @Column(name = "user_admin")
    private String userAdmin;

    @Column(name = "withdrawal")
    private String withdrawal;
}
