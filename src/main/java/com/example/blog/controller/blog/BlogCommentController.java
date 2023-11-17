package com.example.blog.controller.blog;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.blog.model.blogComments.BlogCommentsDto;
import com.example.blog.model.user.Users;
import com.example.blog.service.BlogCommentService;
import com.example.blog.service.UserService;


@Controller
public class BlogCommentController {
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	BlogCommentService blogCommentService;
	
	@Autowired
	UserService userServive;
	
	/**
	 * ブログへのコメントを投稿する
	 * 
	 * @param blogId
	 * @param blogCommentsDto
	 * @param model
	 * @return
	 */
	@PostMapping("/blogComment/{blogId}")
	public String blogComment(@ModelAttribute @PathVariable Long blogId, BlogCommentsDto blogCommentsDto, Model model) {
		
		// ブログIDをblogCommentsDtoにセット
		blogCommentsDto.setBlogId(Integer.parseInt(blogId.toString()));
		
		// ログインユーザーIDをblogCommentsDtoにセット
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Users loginUser = userServive.findByUsername(username);
		Integer userId = loginUser.getUserId();
		blogCommentsDto.setFromUserId(userId);
		
		// 投稿日時、更新日時、削除日時をblogCommentsDtoにセット
		Date dateNow = new Date();
		blogCommentsDto.setCreatedAt(dateNow);
		blogCommentsDto.setUpdatedAt(null);
		blogCommentsDto.setDeletedAt(null);
		
		// 値渡し
		model.addAttribute("blogCommentsDto", blogCommentsDto);
		
		// コメント登録
		blogCommentService.insert(blogCommentsDto);
		
		return "/index";
	}
}
