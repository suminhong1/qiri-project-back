package com.kh.elephant.service;

import com.kh.elephant.domain.Category;
import com.kh.elephant.repo.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDAO dao;

    public List<Category> showAll() {
        return dao.findAll();
    }

    public Category show(int code) {
        return dao.findById(code).orElse(null);
    }

    public Category create(Category category) {
        return dao.save(category);
    }

    public Category update(Category category) {
        Category target = dao.findById(category.getCategorySEQ()).orElse(null);
        if(target!=null) {
            return dao.save(category);
        }
        return null;
    }

    public Category delete(int code) {
        Category data = dao.findById(code).orElse(null);
        dao.delete(data);
        return data;
    }

    public List<Category> findByCTSEQ(int code) {
        return dao.findByCTSEQ(code);
    }
}
