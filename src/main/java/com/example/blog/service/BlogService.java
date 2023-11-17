package com.example.blog.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.blog.model.BlogUserDto;
import com.example.blog.model.blog.Blog;
import com.example.blog.model.blog.BlogDto;
import com.example.blog.model.user.Users;
import com.example.blog.repository.BlogRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BlogService {
	
	@Autowired
	BlogRepository blogRepository;
	
	@Autowired
	UserService userServive;
	
	/** 
	 * URI取得
	 */
	public String getUri() {
		return ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
	}
	
	/**
	 * ブログ登録
	 * @param blogDto
	 */
	public void insert(BlogDto blogDto) {
		
		// BlogDtoからBlogへの変換
		Blog blog = new Blog();
		
		// ログインユーザー情報を取得
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Users loginUser = userServive.findByUsername(username);
		
		blog.setTitle(blogDto.getTitle());
		blog.setContent(blogDto.getContent());
		blog.setUserId(loginUser.getUserId());
		blog.setCreatedAt(new Date());
		blog.setUpdatedAt(null);
		blog.setDeletedAt(null);
		
		blogRepository.save(blog);
	}
	
	/**
	 * ブログ編集
	 */
	public void edit(BlogDto blogDto) {
		
		// BlogDtoからBlogへの変換
		Blog blog = new Blog();
		
		// blog.setBlogId(blogDto.getBlogId());
		blog.setTitle(blogDto.getTitle());
		blog.setContent(blogDto.getContent());
		// blog.setUserId(blogDto.getUserId());
		// blog.setUpdatedAt(blogDto.getUpdatedAt());
		
		blogRepository.edit(blog.getTitle(), blog.getContent(), blog.getUpdatedAt(), blog.getBlogId());
	}
	
	/**
	 * ブログ削除
	 */
	public void delete(Long blogId) {
		blogRepository.deleteById(blogId);
	}
	
	/**
	 * ブログ情報一覧取得
	 */
	public List<BlogUserDto> findAllJoinedUser() {
		return blogRepository.findAllJoinedUser();
	}
	
	/**
	 * ブログ情報一覧取得(ユーザーごと)
	 */
	public List<Blog> findByUser(Integer userId) {
		return blogRepository.findByUser(userId);
	}
	
	/**
	 * ブログ情報取得(IDごと)
	 */
	public Optional<Blog> findByBlogId(Long blogId) {
		return blogRepository.findById(blogId);
	}
	
	/**
	 * ブログ情報詳細取得
	 */
	public Blog findDetail(Long blogId) {
		return blogRepository.findById(blogId).get();
	}
}
