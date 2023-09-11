package com.kh.elephant.service;

import com.kh.elephant.domain.Board;
import com.kh.elephant.repo.BoardDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BoardService {

    @Autowired
    private BoardDAO dao;

    public List<Board> showAll() {
        return dao.findAll();
    }
    public Board show(int code) {
        return dao.findById(code).orElse(null);
    }

    public Board create(Board board){
        return dao.save(board);
    }
    public Board update(Board board){
        return dao.save(board);
    }

    public Board delete(int code) {
        Board data = dao.findById(code).orElse(null);
        dao.delete(data);
        return data;
    }
}
