package com.example.starter.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.starter.models.Blog;

@Service
public class BlogServices {
	HashMap<Long, Blog> blogs= new HashMap();
	AtomicLong id=new AtomicLong();
	
	public Collection<Blog> getBlogs(){
		return this.blogs.values();
	}
	public Blog getById(Long id) {
		return this.blogs.get(id);
	}
	
	public void createBlog(Blog blog) {
		blog.setId(id.incrementAndGet());
		this.blogs.put(blog.getId(), blog);
	}
	public void updateBlog(Long id, Blog blog) {
		blog.setId(id);
		this.blogs.put(blog.getId(), blog);
	}
	public void deleteBlog(Long id) {
		this.blogs.remove(id);
	}

}
