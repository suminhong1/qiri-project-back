package com.kh.elephant.service;
import com.kh.elephant.domain.Matching;
import com.kh.elephant.domain.Place;
import com.kh.elephant.repo.MatchingDAO;
import com.kh.elephant.repo.PlaceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchingService {

    @Autowired
    private MatchingDAO dao;

    public List<Matching> showAll() { return dao.findAll(); }

    public Matching show(int code) { return dao.findById(String.valueOf(code)).orElse(null); }

    public Matching create(Matching matching) { return dao.save(matching); }

    public Matching update(Matching matching) { return dao.save(matching); }

    public Matching delete(int code) {

        Matching data = dao.findById(String.valueOf(code)).orElse(null);
        dao.delete(data);
        return data;
    }


}

