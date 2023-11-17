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
	UserService userService;
	
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
		
		// ログインユーザー情報を取得
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Users loginUser = userService.findByUsername(username);
		
		blog.setTitle(blogDto.getTitle());
		blog.setContent(blogDto.getContent());
		blog.setUserId(loginUser.getUserId());
		blog.setCreatedAt(new Date());
		blog.setUpdatedAt(null);
		blog.setDeletedAt(null);
		
		// DB登録
		blogRepository.save(blog);
	}
	
	/**
	 * ブログ編集
	 */
	public void edit(BlogDto blogDto) {
		// BlogDtoからBlogへの変換
		Blog blog = new Blog();
		
		blog.setBlogId(blogDto.getId());
		blog.setTitle(blogDto.getTitle());
		blog.setContent(blogDto.getContent());
		blog.setUserId(blogDto.getUserId());
		blog.setUpdatedAt(blogDto.getUpdatedAt());
		
		blogRepository.edit(blog.getTitle(), blog.getContent(), blog.getUpdatedAt(), blog.getBlogId());
	}
	
	/**
	 * ブログ削除
	 */
	public void delete(Long id) {
		blogRepository.deleteById(id);
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
	public List<Blog> findByUser(Integer user_id) {
		return blogRepository.findByUser(user_id);
	}
	
	/**
	 * ブログ情報取得(IDごと)
	 */
	public Optional<Blog> findById(Long id) {
		return blogRepository.findById(id);
	}
	
	/**
	 * ブログ情報詳細取得
	 */
	public Blog findDetail(Long id) {
		return blogRepository.findById(id).get();
	}
}
