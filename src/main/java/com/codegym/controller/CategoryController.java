package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.BlogService;
import com.codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/category/list")
    public ModelAndView categories() {
        Iterable<Category> categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/category/list");
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/category/create")
    public ModelAndView createCategoryForm() {
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/category/create")
    public ModelAndView createCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/category/update")
    public ModelAndView updateCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/update");
        modelAndView.addObject("category", category);
        return modelAndView;
    }

    @GetMapping("/category/update/{id}")
    public ModelAndView updateCategoryForm(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            ModelAndView modelAndView = new ModelAndView("/category/update");
            modelAndView.addObject("category", category);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error");
            return modelAndView;
        }
    }
    @GetMapping("/category/delete/{id}")
    public ModelAndView deleteForm(@PathVariable Long id){
        Category category = categoryService.findById(id);
        if(category != null) {
            ModelAndView modelAndView = new ModelAndView("/category/delete");
            modelAndView.addObject("category", category);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/category/delete")
    public String deleteProvince(@ModelAttribute Category category){
        categoryService.remove(category.getId());
        return "redirect:list";
    }
    @GetMapping("/category/view/{id}")
    public ModelAndView viewCategory(@PathVariable Long id){
        Category category = categoryService.findById(id);
        Iterable<Blog> blogs = blogService.findAllByCategory(category);
        ModelAndView modelAndView = new ModelAndView("/category/view");
        modelAndView.addObject("category",category);
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }


}
