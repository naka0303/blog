package com.example.blog.controller.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.blog.model.comments.CommentsDto;
import com.example.blog.model.comments.CommentsForm;
import com.example.blog.service.CommentsService;
import com.example.blog.service.UserService;


@Controller
public class PostController {
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	CommentsService blogsCommentsService;
	
	@Autowired
	UserService userServive;
	
	/**
	 * コメント投稿
	 * 
	 * @param blogId
	 * @param blogCommentsDto
	 * @param model
	 * @return
	 */
	@PostMapping("/blogComment/{blogId}")
	public String blogComment(
			@ModelAttribute @PathVariable Long blogId,
			CommentsForm commentsForm,
			Model model) {
		
		// 投稿内容取得
		CommentsDto commentsDto = new CommentsDto();
		commentsDto.setContent(commentsForm.getContent());
		
		model.addAttribute("commentsForm", commentsForm);
		
		// コメント登録
		blogsCommentsService.insert(blogId, commentsDto);
		
		return "/index";
	}
}