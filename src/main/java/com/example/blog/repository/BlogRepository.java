package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
	
}
