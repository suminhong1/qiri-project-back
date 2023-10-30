package com.kh.elephant.controller;

import com.kh.elephant.domain.Post;
import com.kh.elephant.domain.SearchDTO;
import com.kh.elephant.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/qiri/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class SearchController {

    @Autowired
    private SearchService search;

    // 키워드에 따른 게시물 전체 조회
    @GetMapping("public/search/{id}")
    public ResponseEntity<List<SearchDTO>> searchPost(@PathVariable String id) {

        List<Post> findByKeyword = search.findByKeyword(id);
        List<SearchDTO> response = new ArrayList<>();

        for(Post item : findByKeyword) {
            SearchDTO dto = new SearchDTO();
            dto.setPostSEQ(item.getPostSEQ());
            dto.setPostTitle(item.getPostTitle());
            dto.setUserInfo(item.getUserInfo());
            dto.setPlace(item.getPlace());
            response.add(dto);
        }

        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
