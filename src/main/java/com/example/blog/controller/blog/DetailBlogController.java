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
	
	@GetMapping("/blogDetail/{id}")
	public String blogDetail(@PathVariable Long id, Model model) {
		
		// ブログ情報詳細取得
		Blog blogDetail = blogService.findDetail(id);
		
		// ブログタイトルのみ取得
		String title = blogDetail.getTitle();
		// 投稿内容のみ取得
		String content = blogDetail.getContent();
		
		model.addAttribute("preUri", (String) this.session.getAttribute("preUri"));
		model.addAttribute("title", title);
		model.addAttribute("content", content);
		
		return "/blog/blogDetail";
	}
}
