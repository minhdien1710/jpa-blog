package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.BlogService;
import com.codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }
    @GetMapping("/blog/list")
    public ModelAndView list(Pageable pageable){
        Page<Blog> blogs = blogService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("blogs",blogs);
        return modelAndView;
    }
    @GetMapping("/blog/create")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog",new Blog());
        return modelAndView;
    }
    @PostMapping("/blog/save")
    public ModelAndView save(@ModelAttribute Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }
    @GetMapping("/blog/view/{id}")
    public ModelAndView view(@PathVariable Long id){
        Blog blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/blog/view");
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }
    @GetMapping("/blog/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id){
        Blog blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/blog/update");
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }
    @PostMapping("/blog/update")
    public ModelAndView update(@ModelAttribute Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/blog/update");
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }
    @GetMapping("/blog/delete/{id}")
    public ModelAndView deleteForm(@PathVariable Long id){
        Blog blog = blogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/blog/delete");
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }
    @PostMapping("/blog/delete")
    public String delete(@ModelAttribute Blog blog){
        blogService.remove(blog.getId());
        return "redirect:list";
    }
}
