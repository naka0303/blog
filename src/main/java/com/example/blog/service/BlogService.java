package com.example.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.model.Blog;
import com.example.blog.repository.BlogRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BlogService {
	
	@Autowired
	BlogRepository repository;
	
	/**
	 * ブログ情報一覧取得
	 */
	public List<Blog> findAll() {
		return repository.findAll();
	}
}
