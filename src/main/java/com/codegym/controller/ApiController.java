package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.BlogService;
import com.codegym.service.CategoryService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class ApiController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
    @ModelAttribute("categories")
    public ResponseEntity<Iterable<Category>> categories(){
        Iterable<Category> categories = categoryService.findAll();
        return new ResponseEntity<Iterable<Category>>(categories, HttpStatus.OK);
    }
    @GetMapping(value = "list")
    public ResponseEntity<Page<Blog>> blogList(Pageable pageable){
        Page<Blog> blogList = blogService.findAll(pageable);
        if(blogList == null){
            return new ResponseEntity<Page<Blog>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Blog>>(blogList,HttpStatus.OK);
    }
    @GetMapping("/view/{id}")
    public ResponseEntity<Blog> viewBlog(@PathVariable("id") Long id){
        System.out.println("Fetching blog with id " + id);
        Blog blog = blogService.findById(id);
        if(blog == null){
            System.out.println("blog with id " + id + " not found");
            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Blog>(blog,HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Void> save(@RequestBody Blog blog, UriComponentsBuilder componentsBuilder){
        System.out.println("Creating blog " + blog.getName());
        blogService.save(blog);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(componentsBuilder.path("/blog/view/{id}").buildAndExpand(blog.getId()).toUri());
        return new ResponseEntity<Void>(httpHeaders,HttpStatus.CREATED);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Blog> update(@PathVariable("id") Long id,@RequestBody Blog blog){
        System.out.println("update blog " + id);
        Blog currentBlog = blogService.findById(id);
        if(currentBlog == null){
            System.out.println("blog with id " + id + " not found");
            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
        }
        currentBlog.setId(blog.getId());
        currentBlog.setName(blog.getName());
        currentBlog.setCategory(blog.getCategory());
        currentBlog.setContext(blog.getContext());
        blogService.save(currentBlog);
        return new ResponseEntity<Blog>(currentBlog,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Blog> delete(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting blog with id " + id);
        Blog blog = blogService.findById(id);
        if (blog == null) {
            System.out.println("Unable to delete. blog with id " + id + " not found");
            return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
        }
        blogService.remove(id);
        return new ResponseEntity<Blog>(HttpStatus.NO_CONTENT);
    }
}
