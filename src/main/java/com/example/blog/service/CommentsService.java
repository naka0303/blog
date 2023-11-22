package com.example.blog.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.blog.model.CommentsUsersDto;
import com.example.blog.model.comments.Comments;
import com.example.blog.model.comments.CommentsDto;
import com.example.blog.model.users.Users;
import com.example.blog.repository.CommentsRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class CommentsService {
	
	@Autowired
	CommentsRepository commentsRepository;
	
	@Autowired
	UserService userService;
	
	/**
	 * コメント登録
	 */
	public void insert(Long blogId, CommentsDto commentsDto) {
		
		// CommentsDtoからCommentsへの変換
		Comments comments = new Comments();
		
		// ログインユーザーIDをCommentsDtoにセット
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Users loginUser = userService.findByUsername(username);
		
		// CommentsDtoにセットされた値をCommentsにセット
		comments.setBlogId(Integer.parseInt(blogId.toString()));
		comments.setContent(commentsDto.getContent());
		comments.setFromUserId(loginUser.getUserId());
		comments.setCreatedAt(new Date());
		comments.setUpdatedAt(null);
		comments.setDeletedAt(null);
		
		// 登録用SQL実行
		commentsRepository.save(comments);
	}
	
	/**
	 * 投稿コメント情報(投稿内容、投稿ユーザー名)取得
	 * @return
	 */
	public List<CommentsUsersDto> findByBlogId(Integer blogId) {
		return commentsRepository.findByBlogId(blogId);
	}
}