package com.kh.elephant.service;

import com.kh.elephant.domain.Place;
import com.kh.elephant.repo.PlaceDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PlaceService {

    @Autowired
    private PlaceDAO dao;

    public List<Place> showAll() { return dao.findAll(); }

    public Place show(int code) { return dao.findById(String.valueOf(code)).orElse(null); }

    public Place create(Place place) { return dao.save(place); }

    public Place update(Place place) { return dao.save(place); }

    public Place delete(int code) {

        Place data = dao.findById(String.valueOf(code)).orElse(null);
        dao.delete(data);
        return data;
    }
}
