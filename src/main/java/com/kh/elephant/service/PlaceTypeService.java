package com.kh.elephant.service;

import com.kh.elephant.domain.Place;
import com.kh.elephant.domain.PlaceType;
import com.kh.elephant.repo.PlaceDAO;
import com.kh.elephant.repo.PlaceTypeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceTypeService {

    @Autowired
    private PlaceTypeDAO dao;

    public List<PlaceType> showAll() { return dao.findAll(); }

    public PlaceType show(int code) { return dao.findById(String.valueOf(code)).orElse(null); }

    public PlaceType create(PlaceType placetype) { return dao.save(placetype); }

    public PlaceType update(PlaceType placetype) { return dao.save(placetype); }

    public PlaceType delete(int code) {

        PlaceType data = dao.findById(String.valueOf(code)).orElse(null);
        dao.delete(data);
        return data;
    }
}
