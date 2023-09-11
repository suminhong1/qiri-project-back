package com.kh.elephant.service;

import com.kh.elephant.domain.PostThema;
import com.kh.elephant.repo.PostThemaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostThemaService {
    @Autowired
    private PostThemaDAO dao;

    public List<PostThema> showAll() {
        return dao.findAll();
    }
    public PostThema show(int code) {
        return dao.findById(code).orElse(null);
    }

    public PostThema create(PostThema postThema){
        return dao.save(postThema);
    }
    public PostThema update(PostThema postThema){
        return dao.save(postThema);
    }

    public PostThema delete(int code) {
        PostThema data = dao.findById(code).orElse(null);
        dao.delete(data);
        return data;
    }
}
