package com.kh.elephant.service;

import com.kh.elephant.domain.Comments;
import com.kh.elephant.repo.CommentsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {


    @Autowired
    private CommentsDAO dao;

    public List<Comments> showAll() {
        return dao.findAll();
    }

    public Comments show(int code) {
        return dao.findById(code).orElse(null);
    }

    public Comments create(Comments comments) {
        return dao.save(comments);
    }

    public Comments update(Comments comments) {
        return  dao.save(comments);
    }

    public Comments delete(int code) {
        Comments date = dao.findById(code).orElse(null);
        dao.delete(date);
        return date;
    }
}
