package com.example.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.blog.model.Blog;
import com.example.blog.service.BlogService;

@Controller
public class BlogController {
	
	@Value("${title.name}")
	private String titleName;
	
	@Value("${app.name}")
	private String appName;
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	BlogService service;
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("titleName", titleName);
	    model.addAttribute("appName", appName);
	    
		return "index";
	}
	
	@GetMapping("/blog_list")
	public String blogList(Model model) {
		// ブログ情報全取得
		List<Blog> blogList = service.findAll();
		
		model.addAttribute("titleName", titleName);
	    model.addAttribute("appName", appName);
		model.addAttribute("blogList", blogList);
		
		return "blogList";
	}
	
	@GetMapping("/blog_detail/{id}")
	public String blogDetail(@PathVariable Long id, Model model) {
		// ブログ情報詳細取得
		Blog blogDetail = service.findDetail(id);
		
		// ブログタイトルのみ取得
		String title = blogDetail.getTitle();
		// 投稿内容のみ取得
		String content = blogDetail.getContent();
		
		model.addAttribute("titleName", titleName);
	    model.addAttribute("appName", appName);
	    model.addAttribute("title", title);
	    model.addAttribute("content", content);
		
		return "blogDetail";
	}
	
	@GetMapping("/input")
	public String input(@ModelAttribute Blog blog, Model model) {
		model.addAttribute("titleName", titleName);
	    model.addAttribute("appName", appName);
	    
		return "input";
	}
	
	@PostMapping("/confirm")
	public String confirm(@Validated @ModelAttribute Blog blog, BindingResult result, Model model) {		
		model.addAttribute("titleName", titleName);
	    model.addAttribute("appName", appName);
	    
		if (result.hasErrors()) {
			return "input";
		}
		
		// ブログ登録
		service.insert(blog);
		
		return "confirm";
	}
	
	@PostMapping("complete")
	public String complete(@Validated @ModelAttribute Blog blog, BindingResult result, Model model) {
		model.addAttribute("titleName", titleName);
	    model.addAttribute("appName", appName);
	    
	    return "complete";
	}
}
