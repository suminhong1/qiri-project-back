package com.kh.elephant.controller;

import com.kh.elephant.domain.Comments;
import com.kh.elephant.domain.CommentsDTO;
import com.kh.elephant.domain.UserInfo;
import com.kh.elephant.service.CommentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/qiri/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class CommentsController {


    @Autowired
    private CommentsService comments;

//    @GetMapping("/comments")
//    public ResponseEntity<List<Comments>> showAll() {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(comments.showAll());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }
    // 게시물 1개에 따른 댓글 전체 조회 : GET - http://localhost:8080/qiri/public/post/1/comments
    @GetMapping("/public/post/{id}/comments")
    public ResponseEntity<List<CommentsDTO>> commentList(@PathVariable int id) {
        List<Comments> topList = comments.getAllTopLevelComments(id);
        log.info("top : " + topList);

        List<CommentsDTO> response = new ArrayList<>();

        for(Comments item : topList) {
            CommentsDTO dto = new CommentsDTO();
            dto.setPost(item.getPost());
            dto.setCommentsSEQ(item.getCommentsSEQ());
            dto.setCommentDesc(item.getCommentDesc());
            dto.setCommentDate(item.getCommentDate());
            dto.setUserInfo(item.getUserInfo());
            dto.setCommentDelete(item.getCommentDelete());
            List<Comments> result = comments.getRepliesByCommentId(item.getCommentsSEQ(), id);
            dto.setReplies(result);
            response.add(dto);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }


//    @GetMapping("/comments/{id}")
//    public ResponseEntity<Comments> show(@PathVariable int id) {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(comments.show(id));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }

    // 댓글 추가 : POST - http://localhost:8080/qiri/post/comments
    @PostMapping("/post/comments")
    public ResponseEntity<Comments> create(@RequestBody Comments vo, @AuthenticationPrincipal String id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(id);
        vo.setUserInfo(userInfo);
        return ResponseEntity.status(HttpStatus.OK).body(comments.create(vo));
    }

    // 댓글 수정 : PUT - http://localhost:8080/qiri/post/comments
    @PutMapping("/post/comments")
    public ResponseEntity<Comments> update(@RequestBody Comments vo, @AuthenticationPrincipal String id){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(id);
        vo.setUserInfo(userInfo);
        vo.setCommentDate(new Date());
        vo.setSecretComment("N");
        vo.setCommentDelete("N");
        return ResponseEntity.status(HttpStatus.OK).body(comments.update(vo));
    }

    @PutMapping("/post/comments/delete")
    public ResponseEntity<Comments> delete(@RequestBody Comments vo, @AuthenticationPrincipal String id){
        vo.setCommentDesc(vo.getCommentDesc());
        vo.setCommentDate(new Date());
        vo.setCommentDelete("Y");
        vo.setCommentsParentSeq(vo.getCommentsParentSeq());
        vo.setUserInfo(vo.getUserInfo());
        vo.setSecretComment(vo.getSecretComment());
        return ResponseEntity.status(HttpStatus.OK).body(comments.delete(vo));
    }

    // 댓글 삭제 : DELETE - http://localhost:8080/qiri/post/comments/1
//    @DeleteMapping("/post/comments/{id}")
//    public ResponseEntity<Comments> delete(@PathVariable int id) {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(comments.delete(id));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }
    // 댓글 좋아요 추가
    
    // 댓글 좋아요 취소
    
    // 좋아요한 유저 보기

    //
}
