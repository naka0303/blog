package com.example.blog.controller.blogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.blog.model.users.Users;
import com.example.blog.service.BlogsService;
import com.example.blog.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class ListBlogController {

	private HttpSession session;
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	BlogsService blogsService;
	
	@Autowired
	UserService userServive;
	
	public ListBlogController(HttpSession session) {
		this.session = session;
	}
	
	@GetMapping("/blogList")
	public String blogList(
			Model model) {
		
		// URIをセッション管理
		session.setAttribute("preUri", blogsService.getUri());
		
		// 値渡し
		model.addAttribute("blogList", blogsService.findAllJoinedUser()); // 全ブログ情報
		
		return "/blog/blogList";
	}
	
	@GetMapping("/blogListByUser")
	public String blogListByUser(
			Model model) {
		
		// ログインユーザーを取得
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Users loginUser = userServive.findByUsername(username);
		Integer userId = loginUser.getUserId();
		
		// URIをセッション管理
		session.setAttribute("preUri", blogsService.getUri());
		
		model.addAttribute("blogListByUser", blogsService.findByUser(userId)); // 指定ユーザーの全ブログ情報取得
		
		return "/blog/blogListByUser";
	}
}
