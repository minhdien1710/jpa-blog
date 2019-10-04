package com.codegym.repository;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepo extends PagingAndSortingRepository<Blog,Long> {

    Iterable<Blog> findAllByCategory(Category category);

}
