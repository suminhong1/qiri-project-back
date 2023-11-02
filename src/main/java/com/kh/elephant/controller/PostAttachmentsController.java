package com.kh.elephant.controller;


import com.kh.elephant.domain.Post;
import com.kh.elephant.domain.PostAttachments;
import com.kh.elephant.domain.PostDTO;
import com.kh.elephant.service.PostAttachmentsService;
import com.kh.elephant.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/qiri/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class PostAttachmentsController {

    @Autowired
    private PostAttachmentsService service;
    @Autowired
    private PostService postService;
    private String url;


    // 게시글 전체 조회 http://localhost:8080/qiri/post
    @GetMapping("/postAttachments")
    public ResponseEntity<List<PostAttachments>> showAll() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    // 게시글 골라 보기 http://localhost:8080/qiri/post/1 <--id
//    @GetMapping("/postAttachments/{id}")
//    public ResponseEntity<PostAttachments> show(@PathVariable int id){
//        try{
//            return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }
    // 게시글에 따른 첨부파일 조회
    // 게시글seq로 참부파일정보 가져오기
    @GetMapping("/postAttachments/{id}")
    public ResponseEntity<List<PostAttachments>> findByPostSeq(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByPostSeq(id));
    }

    // 게시글 첨부 파일  추가 http://localhost:8080/qiri/post
    @PostMapping("/postAttachments")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam List<MultipartFile> files, @RequestParam int postId) throws IOException {

        try{
            List<String> ImageList = new ArrayList<>();

            log.info("files : " + files); // 얘는 얘로 반복문!

            log.info("나는 파일이얌 : " + files);
            log.info("나는 게시글이얌 : " + postId);

        for (MultipartFile file : files) {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename(); // 파일 랜덤 이름 부여랑 원래 이름
            String uploadPath = "D:\\ClassQ_team4_frontend\\qoqiri\\public\\upload"; // 저장 경로

            InputStream inputStream = file.getInputStream(); // 파일 데이터를 읽기 위해 필요함
            Path filePath = Paths.get(uploadPath,fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING); // 파일 중복으로 올라올 시 덮어쓰기

            log.info(fileName);
            String imageUrl = "http://localhost:8080/qiri/public/upload/" + fileName;

            ImageList.add(imageUrl);

            // PostAttachments를 빌더를 이용해 for문 안에 있는 imageUrl을 attachmentURL 컬럼에 저장 하고
            PostAttachments postAttachments = PostAttachments.builder()
                    .post(postService.show(postId)) // @Autowired 불러온 postService를 이용해 show로 postID(시퀀스 임)을 찾아서
                    .attachmentURL(imageUrl)
                    .build();
            log.info("첨부 파일 정보" + postAttachments);
            service.create(postAttachments); // 서비스 create(save)를 사용 해서 db에 저장
        }
            return ResponseEntity.status(HttpStatus.OK).body(null);

//        }
//        log.info(fileName);
            // log.info("게시물 아이디 : " + postId);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 게시글 수정 http://localhost:8080/qiri/post
//    @PutMapping("/postAttachments")
//    public ResponseEntity<List<String>> updateFiles(@RequestParam List<MultipartFile> files, @RequestParam int postId) throws IOException{
//        try{
//            return ResponseEntity.status(HttpStatus.OK).body(service.updateAll(postAttachments));
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }
    // 게시글 삭제 http://localhost:8080/qiri/post/1 <--id
    @DeleteMapping("/postAttachments/{id}")
    public ResponseEntity<PostAttachments> delete(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
