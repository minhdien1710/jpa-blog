package com.codegym.repository;

import com.codegym.model.Blog;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepo extends PagingAndSortingRepository<Blog,Long> {

}
