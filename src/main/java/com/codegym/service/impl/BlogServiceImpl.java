package com.codegym.service.impl;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.repository.BlogRepo;
import com.codegym.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BlogServiceImpl implements BlogService {
    @Autowired
    public BlogRepo blogRepo;
    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepo.findAll(pageable);
    }

    @Override
    public Blog findById(Long id) {
        return blogRepo.findOne(id);
    }

    @Override
    public void save(Blog blog) {
        blogRepo.save(blog);
    }

    @Override
    public void remove(Long id) {
        blogRepo.delete(id);
    }

    @Override
    public Iterable<Blog> findAllByCategory(Category category) {
        return blogRepo.findAllByCategory(category);
    }
}
