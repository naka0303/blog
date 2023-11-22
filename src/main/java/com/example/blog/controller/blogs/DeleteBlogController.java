package com.example.blog.controller.blogs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.blog.model.blogs.Blogs;
import com.example.blog.model.blogs.BlogsDto;
import com.example.blog.model.users.Users;
import com.example.blog.service.BlogsService;
import com.example.blog.service.UserService;


@Controller
public class DeleteBlogController {
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	BlogsService blogsService;
	
	@Autowired
	UserService userServive;
	
	@GetMapping("/blogDelete/{id}")
	public String blogDelete(@PathVariable Long id, BlogsDto blogDto, Model model) {
		
		// ブログ削除
		blogsService.delete(id);
	    
		// ログインユーザーを取得
	    String username = SecurityContextHolder.getContext().getAuthentication().getName();
	    Users loginUser = userServive.findByUsername(username);
	    Integer user_id = loginUser.getUserId();
	    
		// ブログ情報全取得
		List<Blogs> blogListByUser = blogsService.findByUser(user_id);
	 	
	 	model.addAttribute("blogListByUser", blogListByUser);
		
		return "/blog/blogListByUser";
	}
}
