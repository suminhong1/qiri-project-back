package com.kh.elephant.service;

import com.kh.elephant.domain.Post;
import com.kh.elephant.repo.SearchDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private SearchDAO searchDAO;
    // 키워드에 따른 게시물 여러개 조회
    public List<Post> searchByKeyword(String keyword) { return searchDAO.searchByKeyword(keyword); }
}
