package com.kh.elephant.controller;

import com.kh.elephant.domain.Post;
import com.kh.elephant.domain.PostAttachments;
import com.kh.elephant.domain.PostDTO;
import com.kh.elephant.service.PostAttachmentsService;
import com.kh.elephant.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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



    // 게시글 전체 조회 http://localhost:8080/qiri/post
    @GetMapping("/postAttachments")
    public ResponseEntity<List<PostAttachments>> showAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 게시글 골라 보기 http://localhost:8080/qiri/post/1 <--id
    @GetMapping("/postAttachments/{id}")
    public ResponseEntity<PostAttachments> show(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.show(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 게시글 첨부 파일  추가 http://localhost:8080/qiri/post
    @PostMapping("/postAttachments")
    public ResponseEntity<List<PostAttachments>> insert(@RequestParam("file") MultipartFile file, @RequestParam("postId") int postId) throws IOException {
        try{

            log.info("나는 파일이얌 : " + file);
            log.info("나는 게시글이얌 : " + postId);

        String uploadPath = "D:\\ClassQ_team4_frontend\\qoqiri\\public\\upload";

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        String attachmentsUrl = "http://loacalhost:8080/qiri/public/" + fileName;

        Path filePath = Paths.get(uploadPath, fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        ResponseEntity.status(HttpStatus.OK).body(attachmentsUrl);

        List<PostAttachments> list = new ArrayList<>();

//        log.info("attachmentsUrl : " + dto.toString());
//
//        for(int i=0; i<dto.getAttachmentList().size(); i++){
//
//            PostAttachments attachments = new PostAttachments();
//            attachments.setAttachmentURL(dto.getAttachmentList().get(i));
//
//            Post post = new Post();
//            post.setPostSEQ(dto.getPostSeq());
//
//            attachments.setPost(post);
//            attachments.setAttachmentURL(attachmentsUrl);
//
//            list.add(attachments);
//        }

            return ResponseEntity.status(HttpStatus.OK).body(service.createAll(list));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

//    @PostMapping("/imageUpload")
//    public ResponseEntity<List<String>>uploadImage(@RequestBody PostDTO dto ,@RequestParam("attachmentUrl") MultipartFile image)throws IOException {
//
//        String uploadPath = "D:\\ClassQ_team4_frontend\\qoqiri\\public\\upload";
//
//        String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
//
//        String attachmentsUrl = "http://loacalhost:8080/qiri/public/" + fileName;
//
//        Path filePath = Paths.get(uploadPath, fileName);
//        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//
////        ResponseEntity.status(HttpStatus.OK).body(attachmentsUrl);
//
////        ResponseEntity.status(HttpStatus.OK).build();
//
//        log.info("attachmentsUrl : " + dto.toString());
//
//        return ResponseEntity.status(HttpStatus.OK).body(attachmentsUrl);
//    }


    // 게시글 수정 http://localhost:8080/qiri/post
    @PutMapping("/postAttachments")
    public ResponseEntity<PostAttachments> update(@RequestBody PostAttachments postAttachments){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.update(postAttachments));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
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
