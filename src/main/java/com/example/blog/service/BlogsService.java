package com.example.blog.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.blog.model.BlogsUsersDto;
import com.example.blog.model.blogs.Blogs;
import com.example.blog.model.blogs.BlogsDto;
import com.example.blog.model.users.Users;
import com.example.blog.repository.BlogsRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BlogsService {
	
	@Autowired
	BlogsRepository blogsRepository;
	
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
	 * @param blogsDto
	 */
	public void insert(BlogsDto blogsDto) {
		
		// BlogDtoからBlogへの変換
		Blogs blogs = new Blogs();
		
		// ログインユーザー情報を取得
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Users loginUser = userService.findByUsername(username);
		
		blogs.setTitle(blogsDto.getTitle());
		blogs.setContent(blogsDto.getContent());
		blogs.setThumbnail(blogsDto.getThumbnail());
		blogs.setUserId(loginUser.getUserId());
		blogs.setCreatedAt(new Date());
		blogs.setUpdatedAt(null);
		blogs.setDeletedAt(null);
		
		// DB登録
		blogsRepository.save(blogs);
	}
	
	/**
	 * ブログ編集
	 * @param blogsDto
	 */
	public void edit(BlogsDto blogsDto) {
		
		// BlogDtoからBlogへの変換
		Blogs blogs = new Blogs();
		
		blogs.setBlogId(blogsDto.getBlogId());
		blogs.setTitle(blogsDto.getTitle());
		blogs.setContent(blogsDto.getContent());
		blogs.setUpdatedAt(new Date());
		
		blogsRepository.edit(blogs.getTitle(), blogs.getContent(), blogs.getUpdatedAt(), blogs.getBlogId());
	}
	
	/**
	 * ブログ削除
	 */
	public void delete(Long id) {
		blogsRepository.deleteById(id);
	}
	
	/**
	 * ブログ情報全取得
	 * @return
	 */
	public List<BlogsUsersDto> findAllJoinedUser() {
		return blogsRepository.findAllJoinedUser();
	}
	
	/**
	 * ブログ情報全取得(ユーザーごと)
	 * @param userId
	 * @return
	 */
	public List<Blogs> findByUser(Integer userId) {
		return blogsRepository.findByUser(userId);
	}
	
	/**
	 * ブログ情報取得(IDごと)
	 * @param id
	 * @return
	 */
	public Optional<Blogs> findById(Long id) {
		return blogsRepository.findById(id);
	}
	
	/**
	 * ブログ情報詳細取得
	 * @param blogId
	 * @return
	 */
	public Blogs findDetail(Long blogId) {
		return blogsRepository.findById(blogId).get();
	}
	
	public byte[] getImageBytes(MultipartFile multipartFile) {
		
		try {
			byte[] bytes = multipartFile.getBytes();
			
			return bytes;
		} catch (IOException e) {
			e.printStackTrace();
			
			return null;
		}
	}
}
