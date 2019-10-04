package com.codegym.service.impl;

import com.codegym.model.Category;
import com.codegym.repository.CategoryRepo;
import com.codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepo.findOne(id);
    }

    @Override
    public void save(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public void remove(Long id) {
        categoryRepo.delete(id);

    }
}
