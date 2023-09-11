package com.kh.elephant.controller;

import com.kh.elephant.domain.MatchingUserInfo;
import com.kh.elephant.service.MatchingUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/matching-user-info")
public class MatchingUserInfoController {

    private MatchingUserInfoService matchingUserInfoService;

    // 매칭 참여 유저 정보 조회
    @GetMapping("/{matchingUserInfoSeq}")
    public ResponseEntity<MatchingUserInfo> getMatchingUserInfo(@PathVariable String matchingUserInfoSeq) {
        MatchingUserInfo matchingUserInfo = matchingUserInfoService.getMatchingUserInfo(Long.valueOf(matchingUserInfoSeq));
        if (matchingUserInfo != null) {
            return ResponseEntity.status(HttpStatus.OK).body(matchingUserInfo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 매칭 참여 유저 정보 생성
    @PostMapping
    public ResponseEntity<MatchingUserInfo> createMatchingUserInfo(@RequestBody MatchingUserInfo matchingUserInfo) {
        MatchingUserInfo createdMatchingUserInfo = matchingUserInfoService.createMatchingUserInfo(matchingUserInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMatchingUserInfo);
    }


}
