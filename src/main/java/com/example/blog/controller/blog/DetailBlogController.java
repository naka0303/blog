package com.example.blog.controller.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.blog.model.blog.Blog;
import com.example.blog.service.BlogService;
import com.example.blog.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class DetailBlogController {
	
	private HttpSession session;
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	UserService userServive;
	
	public DetailBlogController(HttpSession session) {
		this.session = session;
	}
	
	@GetMapping("/blogDetail/{blogId}")
	public String blogDetail(
			@PathVariable Long blogId,
			Model model) {
		
		// ブログ詳細情報取得
		Blog blogDetail = blogService.findDetail(blogId);
		
		// 値渡し
		model.addAttribute("preUri", (String) this.session.getAttribute("preUri"));
		model.addAttribute("title", blogDetail.getTitle());
		model.addAttribute("content", blogDetail.getContent());
		
		return "/blog/blogDetail";
	}
}
