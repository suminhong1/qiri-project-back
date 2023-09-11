package com.kh.elephant.controller;

import com.kh.elephant.domain.Matching;
import com.kh.elephant.service.MatchingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/matching")
public class MatchingController {
    @Autowired
    private MatchingService matchingService;


    // 매칭 조회
    @GetMapping("/{matchingSeq}")
    public ResponseEntity<Matching> getMatching(@PathVariable String matchingSeq) {
        Matching matching = matchingService.show(matchingSeq);
        if (matching != null) {
            return ResponseEntity.status(HttpStatus.OK).body(matching);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 모든 매칭 조회
    @GetMapping("/all")
    public ResponseEntity<List<Matching>> getAllMatchings() {
        List<Matching> matchings = matchingService.getAllMatchings();
        return ResponseEntity.status(HttpStatus.OK).body(matchings);
    }

    // 매칭 생성
    @PostMapping
    public ResponseEntity<Matching> createMatching(@RequestBody Matching matching) {
        Matching createdMatching = matchingService.createMatching(matching);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMatching);
    }

    // 매칭 업데이트
    @PutMapping("/{matchingSeq}")
    public ResponseEntity<Matching> updateMatching(@PathVariable String matchingSeq, @RequestBody Matching updatedMatching) {
        Matching result = matchingService.updateMatching(matchingSeq, updatedMatching);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 매칭 삭제
    @DeleteMapping("/{matchingSeq}")
    public ResponseEntity<Matching> deleteMatching(@PathVariable Long matchingSeq) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(matchingService.deleteMatching(matchingSeq));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}





