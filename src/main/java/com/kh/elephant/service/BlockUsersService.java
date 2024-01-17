package com.kh.elephant.service;

import com.kh.elephant.domain.BlockUsers;
import com.kh.elephant.repo.BlockUsersDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockUsersService {

    @Autowired
    private BlockUsersDAO dao;

    //전체 차단 정보
    public List<BlockUsers> showAll() {
        return dao.findAll();
    }

    public BlockUsers show(int code) {
        return dao.findById(code).orElse(null);
    }

    public List<BlockUsers> showBlockUser(String id) {return dao.findByUserId(id);}

    @Transactional
    public void updateBlockUser(String id) {
        dao.updateByUnblock(id);
    }


    public BlockUsers create(BlockUsers blockUsers) {
        return dao.save(blockUsers);
    }


    public BlockUsers update(BlockUsers blockUsers) {
        BlockUsers target = dao.findById(blockUsers.getBlockUserSeq()).orElse(null);
        if(target!=null) {
            return dao.save(blockUsers);
        }
        return null;
    }

    public BlockUsers delete(int code) {
        BlockUsers data = dao.findById(code).orElse(null);
        dao.delete(data);
        return data;
    }
}
