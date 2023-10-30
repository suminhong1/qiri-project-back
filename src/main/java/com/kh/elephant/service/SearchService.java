package com.kh.elephant.service;

import com.kh.elephant.domain.Post;
import com.kh.elephant.repo.SearchDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private SearchDAO dao;
    // 키워드에 따른 게시물 여러개 조회
    public List<Post> findByKeyword(String id) { return dao.findByKeyword(id); }
}
