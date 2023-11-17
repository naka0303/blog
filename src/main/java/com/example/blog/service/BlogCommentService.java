package com.example.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.model.blogComments.BlogComments;
import com.example.blog.model.blogComments.BlogCommentsDto;
import com.example.blog.repository.BlogCommentsRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class BlogCommentService {
	
	@Autowired
	BlogCommentsRepository blogCommentsRepository;
	
	/**
	 * コメント登録
	 */
	public void insert(BlogCommentsDto blogCommentsDto) {
		
		// BlogDtoからBlogへの変換
		BlogComments blogComments = new BlogComments();
		
		// blogCommentsDtoにセットされた値をblogCommentsにセット
		blogComments.setBlogId(blogCommentsDto.getBlogId());
		blogComments.setContent(blogCommentsDto.getContent());
		blogComments.setFromUserId(blogCommentsDto.getFromUserId());
		blogComments.setCreatedAt(blogCommentsDto.getCreatedAt());
		blogComments.setUpdatedAt(blogCommentsDto.getUpdatedAt());
		blogComments.setDeletedAt(blogCommentsDto.getDeletedAt());
		
		// 登録用SQL実行
		blogCommentsRepository.save(blogComments);
	}
	
	/**
	 * コメント一覧取得(ブログIDごと)
	 */
	public List<BlogComments> findByBlogId(Integer blog_id) {
		return blogCommentsRepository.findByBlogId(blog_id);
	}
}
