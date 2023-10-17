package com.kh.elephant.controller;

import com.kh.elephant.domain.SignUpDTO;
import com.kh.elephant.domain.UserInfo;
import com.kh.elephant.domain.UserInfoDTO;
import com.kh.elephant.security.TokenProvider;
import com.kh.elephant.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/qiri/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class UserInfoController {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserInfoService userService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 유저 전체 조회 http://localhost:8080/qiri/userInfo
    @GetMapping("/userInfo")
    public ResponseEntity<List<UserInfo>> showAllUser() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 유저 상세 조회 http://localhost:8080/qiri/userInfo/1 <--id
    @GetMapping("/userInfo/{id}")
    public ResponseEntity<UserInfo> showUser(@PathVariable String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 유저 수정 http://localhost:8080/qiri/userInfo/1 <--id
    @PutMapping("/userInfo")
    public ResponseEntity<UserInfo> updateUser(@RequestBody UserInfo user) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.update(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 유저 삭제 http://localhost:8080/qiri/userInfo/1 <--id
    @DeleteMapping("/userInfo/{id}")
    public ResponseEntity<UserInfo> deleteUser(@PathVariable String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 회원가입
    @PostMapping("/userInfo/signup")
    public ResponseEntity<UserInfoDTO> register(@RequestBody SignUpDTO dto) {
        UserInfo user = UserInfo.builder()
                .userId(dto.getUserInfoDTO().getId())
                .userPwd(passwordEncoder.encode(dto.getUserInfoDTO().getPwd()))
                .userName(dto.getUserInfoDTO().getName())
                .userNickname(dto.getUserInfoDTO().getNickname())
                .age(dto.getUserInfoDTO().getAge())
                .gender(dto.getUserInfoDTO().getGender())
                .phone(dto.getUserInfoDTO().getPhone())
                .email(dto.getUserInfoDTO().getEmail())
            //    .profileImg(profileImageUrl) // 이미지 URL 설정
                .statusMessage(dto.getUserInfoDTO().getStatusMessage())
                .hasPartner(dto.getUserInfoDTO().getHasPartner())
                .bloodType(dto.getUserInfoDTO().getBloodType())
                .mbti(dto.getUserInfoDTO().getMbti())
                .birthday(dto.getUserInfoDTO().getBirthday())
                .placeType(dto.getUserInfoDTO().getPlaceType())
                .isAdmin("N")
                .isDeleted("N")
                .joinDate(new Date())
                .build();

        UserInfo registeredUser = userService.create(user); // 회원 정보 저장

        if (registeredUser != null) {
            UserInfoDTO responseDTO = UserInfoDTO.builder()
                    .id(registeredUser.getUserId())
                    .nickname(registeredUser.getUserNickname())
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 로그인 -> token
    @PostMapping("/userInfo/signin")
    public ResponseEntity authenticate(@RequestBody UserInfoDTO dto) {
        UserInfo userInfo = userService.getByCredentials(dto.getId(), dto.getPwd(), passwordEncoder);
        if (userInfo != null) { // -> 토큰 생성
            String token = tokenProvider.create(userInfo);
            UserInfoDTO responseDTO = UserInfoDTO.builder()
                    .id(userInfo.getUserId())
                    .nickname(userInfo.getUserNickname())
                    .placeType(userInfo.getPlaceType())
                    .token(token)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // 사용자 정보 조회
    @GetMapping("/userInfo/{userId}")
    public ResponseEntity<UserInfo> getUserInfo(@PathVariable String userId) {
        UserInfo user = userService.show(userId);
        if (user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}











