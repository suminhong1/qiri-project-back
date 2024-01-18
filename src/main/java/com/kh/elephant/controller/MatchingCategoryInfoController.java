package com.kh.elephant.controller;

import com.kh.elephant.domain.*;
import com.kh.elephant.service.CategoryService;
import com.kh.elephant.service.MatchingCategoryInfoService;
import com.kh.elephant.service.PostAttachmentsService;
import com.kh.elephant.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/qiri/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class MatchingCategoryInfoController {

    @Autowired
    private MatchingCategoryInfoService service;

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    // 게시글 전체 조회 http://localhost:8080/qiri/post
    @GetMapping("/matchingCategoryInfo")
    public ResponseEntity<List<MatchingCategoryInfo>> showAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.showAll());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // 게시글seq로 게시글카테고리정보가져오기
    @GetMapping("/matchingCategoryInfo/{id}")
    public ResponseEntity<List<MatchingCategoryInfo>> getMatchingCategory(@PathVariable int id){
        log.info("category :: " + id);
        try{ //post_seq를 테이블에서 검색하는 Service를 사용해 Post_SEQ를 찾는 컨트롤러
            if(service.findByPostSEQ(id) == null) {
            return ResponseEntity.status(HttpStatus.OK).body(null); // null일시 null 반환
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(service.findByPostSEQ(id));
            } // id가 일치 하면 검색한 정보의 결과를 반환

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 게시글 추가 http://localhost:8080/qiri/post
    @PostMapping("/matchingCategoryInfo")  // PostMapping 위한 메소드 경로 설정
    public ResponseEntity<List<MatchingCategoryInfo>> insert(@RequestBody MatchingCategoryInfoDTO dto) {


        // MatchingCategoryInfo 객체들을 담을 빈 리스트를 생성
        List<MatchingCategoryInfo> list = new ArrayList<>();

        log.info("matching category list : " + dto.toString());

        // 카테고리를 여러개 선택할수 있으니 for문을 돌려서
        for(int i=0; i<dto.getCategories().size(); i++) {
            // dto에서 카테고리 정보들을 조회하며 MatchingCategoryInfo  객체를 생성 후 추가

            MatchingCategoryInfo info = new MatchingCategoryInfo();

            // MatchingCategoryInfo의 새 인스턴스를 생성

            Post post = new Post();
            // Post의 새 인스턴스를 생성
            post.setPostSEQ(dto.getPostSEQ());
            // 새로 생성한 post에 PostSEQ를 담음
            info.setPost(post); // set을 사용해서 변수명을 info로 설정한 MatchingCategoryInfo에 postSEQ값을 설정

            Category category = new Category();
            // Category의 새 인스턴스를 생성

            category.setCategorySEQ(dto.getCategories().get(i).getCategorySEQ());

            // 새로 생성한 category에 CategorySEQ를 담음
            info.setCategory(category); // set을 사용해서 변수명을 info로 설정한 MatchingCategoryInfo에 categorySEQ값을 설정

            list.add(info);// info를 리스트인 list에 추가 for문 돌린 info 객체가 post와 category와 연결 후 리스트에 추가
        }
    // 결과 반환
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.createAll(list));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

        // 게시글 수정 http://localhost:8080/qiri/post
        @PutMapping("/matchingCategoryInfo")
        public ResponseEntity<List<MatchingCategoryInfo>> update(@RequestBody MatchingCategoryInfoDTO dto) {
//            try {
//                service.deleteByPostSeq(dto.getPostSEQ());
//
//                List<MatchingCategoryInfo> list = new ArrayList<>();
//
//                log.info("matching category list : " + dto.toString());
//
//                for(int i=0; i<dto.getCategories().size(); i++) {
//                    MatchingCategoryInfo matchingCategoryInfo = MatchingCategoryInfo.builder()
//                            .post(postService.show(dto.getPostSEQ()))
//                            .category(categoryService.show(dto.getCategoriesSeq().get(i)))
//                            .build();
//                    log.info("여기 안오지?", matchingCategoryInfo);
//                    list.add(matchingCategoryInfo);
//                    log.info(list.toString());
//
//
//                }
//                service.createAll(list);
//                return ResponseEntity.status(HttpStatus.OK).body(null);
//
//            } catch (Exception e) {
//                log.info("여기까지 오냐?", dto);
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//            }
//        }



//            List<MatchingCategoryInfo> list = new ArrayList<>();
//
//            log.info("matching category list: " + dto.toString());
//
//            // 기존 데이터 삭제
//            service.deleteByPostSeq(dto.getPostSEQ());
//
//            for (int i = 0; i < dto.getCategories().size(); i++) {
//                MatchingCategoryInfo info = new MatchingCategoryInfo();
//
////               postService.show(dto.getPostSEQ());
//                Post post = new Post();
//                post.setPostSEQ(dto.getPostSEQ());
//                info.setPost(post);
//
//                Category category = new Category();
//                category.setCategorySEQ(dto.getCategories().get(i).getCategorySEQ());
//                info.setCategory(category);
//
//                list.add(info);
//            }
//            log.info("카테고리정보"+dto.toString());
//            try {
//                // 새로운 데이터 추가
//                return ResponseEntity.status(HttpStatus.CREATED).body(service.createAll(list));
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//            }
//
//        }

            log.info("카테고리정보"+dto.toString());
            try {
                // 지금 트랜잭션 어노테이션으로 묶어놔서 삭제나 생성 둘중 하나라도 안되면 안됨
//                service.deleteByPostSeq(dto.getPostSEQ());
//                log.info("게시물 삭제 완료");
//
//                // 삭제 후 조회
//                Post deletedPost = postService.show(dto.getPostSEQ());
//                log.info("삭제 후 조회 결과: " + deletedPost);

//                log.info("matching category list : " + dto.toString());

                for(int i=0; i<dto.getCategories().size(); i++) {
                    MatchingCategoryInfo matchingCategoryInfo = MatchingCategoryInfo.builder()
                            .post(postService.show(dto.getPostSEQ()))
                            .category(categoryService.show(dto.getCategoriesSeq().get(i)))
                            .build();
                    log.info("여기 안오지?", matchingCategoryInfo);
                service.create(matchingCategoryInfo);

                }
                return ResponseEntity.status(HttpStatus.OK).body(null);

            } catch (Exception e) {
                log.info("여기까지 오냐?", dto);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }

            // 게시글 삭제 http://localhost:8080/qiri/post/1 <--id
    @DeleteMapping("/matchingCategoryInfo/{id}")
    public ResponseEntity<MatchingCategoryInfo> delete ( @PathVariable int id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/matchingCategoryInfo/deleteAll/{id}")
    public ResponseEntity<MatchingCategoryInfo> deleteAll(@PathVariable int id) {
        try {
            int deletedCount = service.deleteByPostSeq(id);
            if (deletedCount > 0) {
                log.info("매칭 카테고리 정보 삭제 성공 - 삭제된 개수: {}", deletedCount);
                return ResponseEntity.status(HttpStatus.OK).body(null);
            } else {
                log.info("매칭 카테고리 정보가 없습니다.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            log.error("매칭 카테고리 정보 삭제 중 오류 발생", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}