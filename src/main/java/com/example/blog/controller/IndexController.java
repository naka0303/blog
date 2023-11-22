package com.example.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.blog.service.BlogsService;
import com.example.blog.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class IndexController {
	
	private HttpSession session;
	
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	BlogsService blogsService;
	
	@Autowired
	UserService userServive;
	
	public IndexController(HttpSession session) {
		this.session = session;
	}
	
	@GetMapping("/index")
	public String index(
			Model model) {
		
		// セッション破棄
		this.session.removeAttribute("blogsFormSession");
		
		// URIをセッション管理
		session.setAttribute("preUri", blogsService.getUri());
				
		model.addAttribute("blogList", blogsService.findAllJoinedUser());
		
		return "index";
	}
}
