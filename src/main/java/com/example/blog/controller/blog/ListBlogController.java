package com.example.blog.controller.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.blog.model.BlogUserDto;
import com.example.blog.model.blog.Blog;
import com.example.blog.model.user.Users;
import com.example.blog.service.BlogService;
import com.example.blog.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class ListBlogController {

	private HttpSession session;
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	UserService userServive;
	
	public ListBlogController(HttpSession session) {
		this.session = session;
	}
	
	@GetMapping("/blogList")
	public String blogList(Model model) {
		
		// ブログ情報全取得
		List<BlogUserDto> blogList = blogService.findAllJoinedUser();
		
		// URIをセッション管理
		session.setAttribute("preUri", blogService.getUri());
		
		model.addAttribute("blogList", blogList);
		
		return "/blog/blogList";
	}
	
	@GetMapping("/blogListByUser")
	public String blogListByUser(Model model) {
		
		// ログインユーザーを取得
	    String username = SecurityContextHolder.getContext().getAuthentication().getName();
	    Users loginUser = userServive.findByUsername(username);
	    Integer user_id = loginUser.getUserId();
	    
		// ブログ情報全取得
		List<Blog> blogListByUser = blogService.findByUser(user_id);
		
		// URIをセッション管理
		session.setAttribute("preUri", blogService.getUri());
		
		model.addAttribute("blogListByUser", blogListByUser);
		
		return "/blog/blogListByUser";
	}
}
