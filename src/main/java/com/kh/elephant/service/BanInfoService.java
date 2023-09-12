package com.kh.elephant.service;

import com.kh.elephant.domain.BanInfo;
import com.kh.elephant.repo.BanInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BanInfoService {

    @Autowired
    private BanInfoDAO dao;

    public List<BanInfo> showAll() {
        return dao.findAll();
    }

    public BanInfo show(int code) {
        return dao.findById(code).orElse(null);
    }

    public BanInfo create(BanInfo banInfo) {
        return dao.save(banInfo);
    }

    public BanInfo update(BanInfo banInfo) {
        BanInfo target = dao.findById(banInfo.getBanInfoSeq()).orElse(null);
        if(target!=null) {
            return dao.save(banInfo);
        }
        return null;
    }

    public BanInfo delete(int code) {
        BanInfo data = dao.findById(code).orElse(null);
        dao.delete(data);
        return data;
    }
}
