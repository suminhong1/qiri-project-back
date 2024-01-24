package com.kh.elephant.controller;

import com.kh.elephant.domain.PostAttachments;
import com.kh.elephant.service.PostAttachmentsService;
import com.kh.elephant.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

//    @Value("${youtube.upload.path")
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

    // 게시글에 따른 첨부파일 조회
    // 게시글seq로 참부파일정보 가져오기
    @GetMapping("/postAttachments/{id}")
    public ResponseEntity<List<PostAttachments>> findByPostSeq(@PathVariable int id) {
        log.info("첨부 파일 :: " + id);
        try{
            if(service.findByPostSEQ(id)==null){
                return ResponseEntity.status(HttpStatus.OK).body(null);
            }else{
                return ResponseEntity.status(HttpStatus.OK).body(service.findByPostSEQ(id));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 게시글 첨부 파일  추가 http://localhost:8080/qiri/post
    @PostMapping("/postAttachments")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam(required = false)List<MultipartFile> files, @RequestParam int postId) throws IOException {

        try{
            List<String> ImageList = new ArrayList<>();

            log.info("files : " + files); // 얘는 얘로 반복문!

            log.info("나는 파일이얌 : " + files);
            log.info("나는 게시글이얌 : " + postId);

            if (files.isEmpty()) {
                //  클라이언트가 사진을 첨부하지 않았다면 아무 동작을 하지 않음
                return ResponseEntity.status(HttpStatus.OK).body(ImageList);
            }

            for (MultipartFile file : files) { // 첨부파일이 여러개 일수 있으니 for문 사용
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename(); // 파일 랜덤 이름 부여랑 원래 이름
                String uploadPath = "C:\\ClassQ_team4_frontend\\qoqiri\\public\\upload"; // 저장 경로

                InputStream inputStream = file.getInputStream(); // 파일 데이터를 읽기 위해 필요함
                Path filePath = Paths.get(uploadPath,fileName); //Paths.get를 사용하여 파일 경로를 생성
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING); // 파일 중복으로 올라올 시 덮어쓰기

                log.info(fileName);
                String imageUrl =  fileName;

                ImageList.add(imageUrl); // list에 추가

                // PostAttachments를 빌더를 이용해 for문 안에 있는 imageUrl을 attachmentURL 컬럼에 저장 하고
                PostAttachments postAttachments = PostAttachments.builder()
                        .post(postService.show(postId)) // @Autowired 불러온 postService를 이용해 show로 postID(시퀀스 임)을 찾아서
                        .attachmentURL(imageUrl)
                        .build();
                log.info("첨부 파일 정보" + postAttachments);
                service.create(postAttachments); // 서비스 create(save)를 사용 해서 db에 저장
            }
            return ResponseEntity.status(HttpStatus.OK).body(null);
            
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 게시글 수정 http://localhost:8080/qiri/post
    @PutMapping("/postAttachments")
    public ResponseEntity<List<String>> updateFiles(@RequestParam(required = false) List<MultipartFile> files, @RequestParam int postId) throws IOException {
        try {
            List<String> updatedImageList = new ArrayList<>();

            log.info("files : " + files); // 디버깅을 위해 파일 로그

            // 파일이 제공되었는지 확인
            if (files == null || files.isEmpty()) {
                // 업데이트할 파일이 없음
                return ResponseEntity.status(HttpStatus.OK).body(updatedImageList);
            }

            // 첨부 파일 재생성 전 기존 첨부파일 삭제
            service.deleteByPostSeq(postId);
            
            // 새 파일을 반복하고 첨부 파일 업데이트 또는 추가
            for (MultipartFile file : files) {
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                String uploadPath = "C:\\ClassQ_team4_frontend\\qoqiri\\public\\upload";

                InputStream inputStream = file.getInputStream();
                Path filePath = Paths.get(uploadPath, fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

                String imageUrl = fileName;

                updatedImageList.add(imageUrl);

                PostAttachments postAttachments = PostAttachments.builder()
                        .post(postService.show(postId))
                        .attachmentURL(imageUrl)
                        .build();
                log.info("첨부 파일 정보" + postAttachments);

                service.create(postAttachments);
            }

            return ResponseEntity.status(HttpStatus.OK).body(updatedImageList);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    // 게시글 삭제 http://localhost:8080/qiri/post/1 <--id

//    @DeleteMapping("/postAttachments/{id}")
//    public ResponseEntity<PostAttachments> deleteFiles(@PathVariable int id){
//        try{
//            int delete = service.deleteByPostSeq(id);
//            if(delete>0){
//                log.info("첨부 파일 삭제 성공 - 삭제된 수: ",delete);
//                return ResponseEntity.status(HttpStatus.OK).body(null);
//            }else {
//                log.info("첨부 파일 정보가 없습니다.");
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//            }
//        }catch (Exception e){
//            log.error("첨부 파일 정보 삭제 중 오류 발생",e);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }
//}
@DeleteMapping("/postAttachments/deleteAll/{id}")
public ResponseEntity<PostAttachments> deleteFiles(@PathVariable int id){
    try{
        int delete = service.deleteByPostSeq(id);
        if(delete>0){
            log.info("첨부 파일 삭제 성공 - 삭제된 수: {} ",delete);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }else {
            log.info("첨부 파일 정보가 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }catch (Exception e){
        log.error("첨부 파일 정보 삭제 중 오류 발생",e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
}
