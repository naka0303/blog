package com.example.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.blog.model.BlogUserDto;
import com.example.blog.model.blog.Blog;
import com.example.blog.model.blog.BlogDto;
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
		blog.setCreatedAt(blogDto.getCreatedAt());
		blog.setUpdatedAt(blogDto.getUpdatedAt());
		blog.setDeletedAt(blogDto.getDeletedAt());
		
		repository.save(blog);
	}
	
	/**
	 * ブログ編集
	 */
	public void edit(BlogDto blogDto) {
		// BlogDtoからBlogへの変換
		Blog blog = new Blog();
		
		blog.setId(blogDto.getId());
		blog.setTitle(blogDto.getTitle());
		blog.setContent(blogDto.getContent());
		blog.setUserId(blogDto.getUserId());
		blog.setUpdatedAt(blogDto.getUpdatedAt());
		
		repository.edit(blog.getTitle(), blog.getContent(), blog.getUpdatedAt(), blog.getId());
	}
	
	/**
	 * ブログ削除
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	/**
	 * ブログ情報一覧取得
	 */
	public List<BlogUserDto> findAllJoinedUser() {
		return repository.findAllJoinedUser();
	}
	
	/**
	 * ブログ情報一覧取得(ユーザーごと)
	 */
	public List<Blog> findByUser(Integer user_id) {
		return repository.findByUser(user_id);
	}
	
	/**
	 * ブログ情報取得(IDごと)
	 */
	public Optional<Blog> findById(Long id) {
		return repository.findById(id);
	}
	
	/**
	 * ブログ情報詳細取得
	 */
	public Blog findDetail(Long id) {
		return repository.findById(id).get();
	}
}
