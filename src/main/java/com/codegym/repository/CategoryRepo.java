package com.codegym.repository;

import com.codegym.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepo extends PagingAndSortingRepository<Category,Long> {
}
