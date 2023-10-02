package com.kh.elephant.service;

import com.kh.elephant.domain.Board;
import com.kh.elephant.repo.BoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardDAO boardDAO;

    public List<Board> showAll() {
        return boardDAO.findAll();
    }
    public Board show(int id) {
        return boardDAO.findById(id).orElse(null);
    }

    public Board create(Board board){
        return boardDAO.save(board);
    }
    public Board update(Board channel) {
        Board target = boardDAO.findById(channel.getBoardSeq()).orElse(null);
        if(target!=null){
            return boardDAO.save(channel);
        }
        return null;
    }

    public Board delete(int id) {
        Board target = boardDAO.findById(id).orElse(null);
        boardDAO.delete(target);
        return target;
    }
}
