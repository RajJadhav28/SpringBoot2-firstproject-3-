package com.example.starter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.starter.models.Blog;
import com.example.starter.services.BlogServices;


@RestController
@RequestMapping("/blogs")
public class BlogController {
	@Autowired
	BlogServices blogServices;
	
	@GetMapping("")
	public ResponseEntity getallBlogs() {
		return new ResponseEntity(this.blogServices.getBlogs(),HttpStatus.OK);
		
	}
	@GetMapping("{id}")
	public ResponseEntity getBlogById(@PathVariable("id") Long id) {
		Blog blogexist=this.blogServices.getById(id);
		
		if(blogexist==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"blog not found");
		}
		return new ResponseEntity(blogexist,HttpStatus.OK);
	}
	@PostMapping("")
	public ResponseEntity createBlog(@RequestBody Blog blog) {
		this.blogServices.createBlog(blog);
		return new ResponseEntity("blog created successfully",HttpStatus.CREATED);
	}
	@PutMapping("{id}")
	public ResponseEntity updateBlog(@PathVariable("id") Long id, @RequestBody Blog blog) {
		Blog blogexist=this.blogServices.getById(id);
		
		if(blogexist==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with specified id not found");
			
		}
		this.blogServices.updateBlog(id, blog);
		return new ResponseEntity("Blog updated successfully",HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	public ResponseEntity deleteBlog(@PathVariable("id") Long id) {
		Blog blogexist=this.blogServices.getById(id);
		if(blogexist==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with specified id"+id+" not found");
		}
		this.blogServices.deleteBlog(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
