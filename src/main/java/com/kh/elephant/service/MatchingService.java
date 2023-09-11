package com.kh.elephant.service;

import com.kh.elephant.domain.Matching;
import com.kh.elephant.repo.MatchingDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchingService {

    @Autowired
    private MatchingDAO dao;

    public List<Matching> showAll() {
        return dao.findAll();
    }

    public Matching show(String matchingSeq) {
        return dao.findById(matchingSeq).orElse(null);
    }

    public Matching createMatching(Matching matching) {
        return dao.save(matching);
    }

    public Matching updateMatching(String matchingSeq, Matching matching) {
        Matching target = dao.findById(matchingSeq).orElse(null);
        if (target != null) {
            return dao.save(target);
        }
        return null;
    }

    public Matching deleteMatching(Long matchingSeq) {
        Matching target = dao.findById(String.valueOf(matchingSeq)).orElse(null);
        if (target != null) {
            dao.delete(target);
        }
        return target;
    }

    public List<Matching> getAllMatchings() {
        return dao.findAll();
    }
}

