package com.kh.elephant.service;


import com.kh.elephant.domain.MatchingCategoryInfo;
import com.kh.elephant.repo.MatchingCategoryInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchingCategoryInfoService {

    @Autowired
    private MatchingCategoryInfoDAO dao;

    public List<MatchingCategoryInfo> showAll() { return dao.findAll(); }

    public MatchingCategoryInfo show(int code) { return dao.findById(code).orElse(null); }

    public MatchingCategoryInfo create(MatchingCategoryInfo matchingCategoryInfo) { return dao.save(matchingCategoryInfo); }

    public MatchingCategoryInfo update(MatchingCategoryInfo matchingCategoryInfo) { return dao.save(matchingCategoryInfo); }

    public MatchingCategoryInfo delete(int code) {

        MatchingCategoryInfo data = dao.findById(code).orElse(null);
        dao.delete(data);
        return data;
    }
}
