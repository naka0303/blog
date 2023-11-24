package com.example.blog.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.blog.model.BlogsUsersDto;
import com.example.blog.repository.BlogsRepository;
import com.example.blog.service.BlogsService;
import com.example.blog.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class IndexController {
	
	private HttpSession session;
	
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	BlogsRepository blogsRepository;
	
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
		
		List<BlogsUsersDto> allBlog = blogsRepository.findAllJoinedUser();
		
		LinkedHashMap<Integer, List<String>> allBlogDict = new LinkedHashMap<Integer, List<String>>();
		for (BlogsUsersDto blog : allBlog) {
			
			// byte型->base64型
			String thumbnailBase64 = "";
			if (blog.getThumbnail() != null) {
				thumbnailBase64 = Base64.getEncoder().encodeToString(blog.getThumbnail());
			}
			
			List<String> blogVal = new ArrayList<String>();
			blogVal.add(blog.getTitle());
			blogVal.add(blog.getUsername());
			blogVal.add("data:image/png;base64," + thumbnailBase64);
			
			allBlogDict.put(blog.getBlogId(), blogVal);
		}
		
		model.addAttribute("allBlogDict", allBlogDict);
		return "index";
	}
}
