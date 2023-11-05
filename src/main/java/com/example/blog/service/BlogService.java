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
	 * ブログ登録
	 */
	public void insert(Blog blog) {
		repository.save(blog);
	}
	
	/**
	 * ブログ情報一覧取得
	 */
	public List<Blog> findAll() {
		return repository.findAll();
	}
	
	/**
	 * ブログ情報詳細取得
	 */
	public Blog findDetail(Long id) {
		return repository.findById(id).get();
	}
}
