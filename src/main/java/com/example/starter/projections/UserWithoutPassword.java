package com.example.starter.projections;

import java.time.Instant;
import java.util.List;

import com.example.starter.models.Blog;

public interface UserWithoutPassword {
	Long getId();
	String getName();
	String getEmail();
	Instant getCreatedAt();
	Instant getUpdatedAt();
	AddressWithoutUser getAddress();
	List<Blog> getBlogs();

}
