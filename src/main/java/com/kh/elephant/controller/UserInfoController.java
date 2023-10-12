package com.kh.elephant.controller;

import com.kh.elephant.domain.UserInfo;
import com.kh.elephant.domain.UserInfoDTO;
import com.kh.elephant.security.TokenProvider;
import com.kh.elephant.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<UserInfo>> showAllUser(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.showAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 유저 상세 조회 http://localhost:8080/qiri/userInfo/1 <--id
    @GetMapping("/userInfo/{id}")
    public ResponseEntity<UserInfo> showUser(@PathVariable String id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.show(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 유저 수정 http://localhost:8080/qiri/userInfo/1 <--id
    @PutMapping("/userInfo")
    public ResponseEntity<UserInfo> updateUser(@RequestBody UserInfo user){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.update(user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 유저 삭제 http://localhost:8080/qiri/userInfo/1 <--id
    @DeleteMapping("/userInfo/{id}")
    public ResponseEntity<UserInfo> deleteUser(@PathVariable String id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 회원가입
    @PostMapping("/userInfo/signup")
    public ResponseEntity register(@RequestBody UserInfoDTO dto) {
       UserInfo user = UserInfo.builder()
               .userId(dto.getId())
               .userPwd(passwordEncoder.encode(dto.getPwd()))
               .userName(dto.getNickname())
               .age(dto.getAge())
               .gender(dto.getGender())
               .phone(dto.getPhone())
               .email(dto.getEmail())
               .profileImg(dto.getProfileImg())
               .statusMessage(dto.getStatusMessage())
               .hasPartner(dto.getHasPartner())
               .bloodType(dto.getBloodType())
               .mbti(dto.getMbti())
               .birthday(dto.getBirthday())
               .build();

       UserInfo registerUser = userService.create(user);
       UserInfoDTO responseDTO = dto.builder()
               .id(registerUser.getUserId())
               .nickname(registerUser.getUserNickname())
               .build();

        return ResponseEntity.ok().body(responseDTO);
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
                    .place(userInfo.getPlace())
                    .token(token)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
