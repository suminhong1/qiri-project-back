package com.kh.elephant.service;

import com.kh.elephant.domain.Place;
import com.kh.elephant.repo.PlaceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    @Autowired
    private PlaceDAO dao;

    public List<Place> showAll() {
        return dao.findAll();
    }

    public Place show(int id) {
        return dao.findById(id).orElse(null);
    }

    public Place create(Place place) {
        return dao.save(place);
    }

    public Place update(Place place) {
        Place target = dao.findById(place.getPlaceSEQ()).orElse(null);
        if(target!=null) {
            return dao.save(place);
        }
        return null;
    }

    public Place delete(int code) {
        Place data = dao.findById(code).orElse(null);
        dao.delete(data);
        return data;
    }
    public List<Place> findByPostSEQ(int id){return dao.findByPostSeq(id);}
}

