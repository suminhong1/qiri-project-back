package com.kh.elephant.service;

import com.kh.elephant.domain.CategoryType;
import com.kh.elephant.repo.CategoryTypeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryTypeService {

    @Autowired
    private CategoryTypeDAO dao;

    public List<CategoryType> showAll() {
        return dao.findAll();
    }

    public CategoryType show(int code) {
        return dao.findById(code).orElse(null);
    }

    public CategoryType create(CategoryType categoryType) {
        return dao.save(categoryType);
    }

    public CategoryType update(CategoryType categoryType) {
        CategoryType target = dao.findById(categoryType.getCtSEQ()).orElse(null);
        if(target!=null) {
            return dao.save(categoryType);
        }
        return null;
    }

    public CategoryType delete(int code) {
        CategoryType data = dao.findById(code).orElse(null);
        dao.delete(data);
        return data;
    }
}
