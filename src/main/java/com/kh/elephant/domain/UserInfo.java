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
    private Place place;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "profile_img")
    private String profileImg;

    @Column(name = "status_message")
    private String statusMessage;

    @Column(name = "HAS_PARTNER")
    private String hasPartner;

    @Column(name = "blood_type")
    private String bloodType;

    @Column(name = "mbti")
    private String mbti;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "JOIN_DATE")
    private Date joinDate;

    @Column(name = "POPULARITY")
    private int popularity;

    @Column(name = "rating")
    private int rating;

    @Column(name = "IS_ADMIN")
    private String isAdmin;

    @Column(name = "IS_DELETED")
    private String isDeleted;
}
