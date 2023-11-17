package com.example.blog.controller.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.blog.model.blog.Blog;
import com.example.blog.model.blog.BlogDto;
import com.example.blog.model.user.Users;
import com.example.blog.service.BlogService;
import com.example.blog.service.UserService;


@Controller
public class DeleteBlogController {
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	UserService userServive;
	
	@GetMapping("/blogDelete/{blogId}")
	public String blogDelete(@PathVariable Long blogId, BlogDto blogDto, Model model) {
		
		// ブログ削除
		blogService.delete(blogId);
	    
		// ログインユーザーを取得
	    String username = SecurityContextHolder.getContext().getAuthentication().getName();
	    Users loginUser = userServive.findByUsername(username);
	    Integer userId = loginUser.getUserId();
	    
		// ブログ情報全取得
		List<Blog> blogListByUser = blogService.findByUser(userId);
	 	
	 	model.addAttribute("blogListByUser", blogListByUser);
		
		return "/blog/blogListByUser";
	}
}
