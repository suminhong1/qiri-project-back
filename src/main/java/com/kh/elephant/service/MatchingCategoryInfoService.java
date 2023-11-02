package com.kh.elephant.service;


import com.kh.elephant.domain.MatchingCategoryInfo;
import com.kh.elephant.repo.CategoryDAO;
import com.kh.elephant.repo.MatchingCategoryInfoDAO;
import com.kh.elephant.repo.PostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MatchingCategoryInfoService {

    @Autowired
    private MatchingCategoryInfoDAO dao;

    @Autowired
    CategoryDAO Categorydao;

    @Autowired
    PostDAO Postdao;

    public List<MatchingCategoryInfo> showAll() { return dao.findAll(); }

    public MatchingCategoryInfo show(int code) { return dao.findById(code).orElse(null); }

    @Transactional
    public MatchingCategoryInfo create(MatchingCategoryInfo matchingCategoryInfo) {

        return dao.save(matchingCategoryInfo); }

    public List<MatchingCategoryInfo> createAll(List<MatchingCategoryInfo> matchingCategoryInfoList){
        return dao.saveAll(matchingCategoryInfoList);
    }

    public MatchingCategoryInfo update(MatchingCategoryInfo matchingCategoryInfo) { return dao.save(matchingCategoryInfo); }

    public List<MatchingCategoryInfo> updateAll(List<MatchingCategoryInfo> matchingCategoryInfoList){
        return dao.saveAll(matchingCategoryInfoList);
    }
    public MatchingCategoryInfo delete(int code) {

        MatchingCategoryInfo data = dao.findById(code).orElse(null);
        dao.delete(data);
        return data;
    }

    public List<MatchingCategoryInfo> findByCTSEQ(int code) {
        return dao.findByCTSEQ(code);
    }
    public List<MatchingCategoryInfo> findByPostSEQ(int id){ return dao.findByPostSeq(id);}
}
