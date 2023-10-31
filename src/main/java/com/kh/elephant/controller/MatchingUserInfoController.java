package com.kh.elephant.controller;

import com.kh.elephant.domain.*;
import com.kh.elephant.security.TokenProvider;
import com.kh.elephant.service.MatchingUserInfoService;
import com.kh.elephant.service.PostService;
import com.kh.elephant.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/qiri/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Slf4j
public class MatchingUserInfoController {

    @Autowired
    private MatchingUserInfoService muiService;
    @Autowired
    private UserInfoService uiService;
    @Autowired
    private PostService postService;
    @Autowired
    private TokenProvider tokenProvider;


    @GetMapping("/matchingUserInfo")
    public ResponseEntity<List<MatchingUserInfo>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(muiService.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/matchingUserInfo/{id}")
    public ResponseEntity<MatchingUserInfo> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(muiService.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //매칭 신청자 정보 생성
    @PostMapping("/MatchingUserInfo")
    public ResponseEntity<MatchingUserInfo> create(@RequestBody MatchingUserInfoDTO dto) {
        try {
            MatchingUserInfo matchingUserInfo = MatchingUserInfo.builder()
                    .userInfo(uiService.show(dto.getId()))
                    .post(postService.show(dto.getPostSEQ()))
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(muiService.create(matchingUserInfo));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 매칭유저 DB 저장
    @PostMapping("/ApplyUserInfo")
    public ResponseEntity<?> applyCreate(@RequestBody MatchingUserInfoDTO dto) {
        log.info("넘어는옴?");

        // 토큰에서 사용자 ID 추출
        String userId = tokenProvider.validateAndGetUserId(dto.getToken());
        UserInfo userinfo = uiService.show(userId);
        Post post = postService.show(dto.getPostSEQ());

        log.info("포스트"+post);
        log.info("아이디"+userinfo);

        // 기존에 동일한 정보가 있는지 확인
        Optional<MatchingUserInfo> existingData = muiService.findByUserIdAndPostSEQ(userId, dto.getPostSEQ());
        if (existingData.isPresent()) {
            // 이미 존재하는 정보가 있다면, 해당 정보를 알리는 응답 반환
            return ResponseEntity.status(HttpStatus.CONFLICT).body("중복.");
        }

        MatchingUserInfo matchingUserInfo = MatchingUserInfo.builder()
                .post(post)
                .userInfo(userinfo)
                .matchingAccept(dto.getMatchingAccept())
                .build();

        return ResponseEntity.ok().body(muiService.create(matchingUserInfo));
    }

    // 매칭유저 리스트보기
    @GetMapping("/getApplyList/{postSEQ}")
    public ResponseEntity<List<MatchingUserInfoDTO>> getApplyList(@PathVariable int postSEQ) {
        try {
            List<MatchingUserInfo> list = muiService.findByPostSEQ(postSEQ);
            // DTO 변환 로직
            List<MatchingUserInfoDTO> result = new ArrayList<>();
            for (MatchingUserInfo item : list) {
                MatchingUserInfoDTO dto = new MatchingUserInfoDTO();
                dto.setId(item.getUserInfo().getUserId());
                dto.setPostSEQ(postSEQ);
                // 다른 필드들도 세팅
                result.add(dto);
            }
            log.info("저장된것 보기"+result);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }





    @PutMapping("/MatchingUserInfo")
    public ResponseEntity<MatchingUserInfo> update(@RequestBody MatchingUserInfo matchingUserInfo){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(muiService.update(matchingUserInfo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/MatchingUserInfo/{id}")
    public ResponseEntity<MatchingUserInfo> delete(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(muiService.delete(Integer.parseInt(id)));
    }

}