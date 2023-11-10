package com.example.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.blog.model.Blog;
import com.example.blog.model.BlogDto;
import com.example.blog.repository.BlogRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BlogService {
	
	@Autowired
	BlogRepository repository;
	
	/** 
	 * URI取得
	 */
	public String getUri() {
		return ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
	}
	
	/**
	 * ブログ登録
	 */
	public void insert(BlogDto blogDto) {
		// BlogDtoからBlogへの変換
        Blog blog = new Blog();
        
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());       
        blog.setUserId(blogDto.getUserId());
		
		repository.save(blog);
	}
	
	/**
	 * ブログ情報一覧取得
	 */
	public List<Blog> findAll() {
		return repository.findAll();
	}
	
	/**
	 * ブログ情報一覧取得(ユーザーごと)
	 */
	public List<Blog> findByUser(Integer user_id) {
		return repository.findByUser(user_id);
	}
	
	/**
	 * ブログ情報詳細取得
	 */
	public Blog findDetail(Long id) {
		return repository.findById(id).get();
	}
}
