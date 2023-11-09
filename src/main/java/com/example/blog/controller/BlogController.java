package com.example.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.blog.model.Blog;
import com.example.blog.model.BlogDto;
import com.example.blog.model.User;
import com.example.blog.service.BlogService;
import com.example.blog.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BlogController {
	
	private HttpSession session;
	
	@Value("${title.name}")
	private String titleName;
	
	@Value("${app.name}")
	private String appName;
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	UserService userServive;
	
	public BlogController(HttpSession session) {
		this.session = session;
	}
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("titleName", titleName);
		model.addAttribute("appName", appName);
	    
		return "index";
	}
	
	@GetMapping("/blog_list")
	public String blogList(Model model) {
		// ブログ情報全取得
		List<Blog> blogList = blogService.findAll();
		
		model.addAttribute("titleName", titleName);
	    model.addAttribute("appName", appName);
		model.addAttribute("blogList", blogList);
		
		return "/blog/blogList";
	}
	
	@GetMapping("/blog_detail/{id}")
	public String blogDetail(@PathVariable Long id, Model model) {
		// ブログ情報詳細取得
		Blog blogDetail = blogService.findDetail(id);
		
		// ブログタイトルのみ取得
		String title = blogDetail.getTitle();
		// 投稿内容のみ取得
		String content = blogDetail.getContent();
		
		model.addAttribute("titleName", titleName);
	    model.addAttribute("appName", appName);
	    model.addAttribute("title", title);
	    model.addAttribute("content", content);
		
		return "/blog/blogDetail";
	}
	
	@GetMapping("/input")
	public String input(@ModelAttribute BlogDto blogDto, Model model) {
		model.addAttribute("titleName", titleName);
	    model.addAttribute("appName", appName);
	    model.addAttribute("blogDto", blogDto);
	    
		return "/blog/input";
	}
	
	@PostMapping("/confirm")
	public String confirm(@Validated @ModelAttribute BlogDto blogDto, BindingResult result, Model model) {		
		model.addAttribute("titleName", titleName);
	    model.addAttribute("appName", appName);
	    
	    if (result.hasErrors()) {
			return "/blog/input";
		}
	    
	    // ログインユーザーを取得
	    String username = SecurityContextHolder.getContext().getAuthentication().getName();
	    User loginUser = userServive.findByUsername(username);
	    Integer user_id = loginUser.getUserId();
	    blogDto.setUserId(user_id);
	    
	    String title = blogDto.getTitle();
	    String content = blogDto.getContent();
	    
	    session.setAttribute("title", title);
	    session.setAttribute("content", content);
	    session.setAttribute("user_id", user_id.toString());
		
		return "/blog/confirm";
	}
	
	@PostMapping("complete")
	public String complete(@Validated @ModelAttribute BlogDto blogDto, BindingResult result, Model model) {
		model.addAttribute("titleName", titleName);
	    model.addAttribute("appName", appName);
	    
	    String title = (String) session.getAttribute("title");
	    String content = (String) session.getAttribute("content");
	    String user_id = (String) session.getAttribute("user_id");
	    blogDto.setTitle(title);
	    blogDto.setContent(content);
	    blogDto.setUserId(Integer.parseInt(user_id));
	    
	    // ブログ登録
	    blogService.insert(blogDto);
	    
	    return "/blog/complete";
	}
}
