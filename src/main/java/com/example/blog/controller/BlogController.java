package com.example.blog.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
		
		// セッション破棄
		this.session.removeAttribute("title");
		this.session.removeAttribute("content");
		
		return "index";
	}
	
	@GetMapping("/blogList")
	public String blogList(Model model) {
		
		// URI取得
		this.session.setAttribute("uri", blogService.getUri());
		
		// ブログ情報全取得
		List<Blog> blogList = blogService.findAll();
		
		model.addAttribute("titleName", titleName);
	    model.addAttribute("appName", appName);
		model.addAttribute("blogList", blogList);
		
		return "/blog/blogList";
	}
	
	@GetMapping("/blogListByUser")
	public String blogListByUser(Model model) {
		
		// URI取得
		this.session.setAttribute("uri", blogService.getUri());
		
		// ログインユーザーを取得
	    String username = SecurityContextHolder.getContext().getAuthentication().getName();
	    User loginUser = userServive.findByUsername(username);
	    Integer user_id = loginUser.getUserId();
	    
		// ブログ情報全取得
		List<Blog> blogListByUser = blogService.findByUser(user_id);
		
		model.addAttribute("titleName", titleName);
	    model.addAttribute("appName", appName);
		model.addAttribute("blogListByUser", blogListByUser);
		
		return "/blog/blogListByUser";
	}
	
	@GetMapping("/blogEdit/{id}")
	public String blogEdit(@PathVariable Long id, BlogDto blogDto, Model model) {
		
		// 編集ボタンが押されたブログ情報を取得
		Optional<Blog> targetBlog = blogService.findById(id);
		
		// ブログ情報を要素ごとに取得
		String title = targetBlog.get().getTitle();
		String content = targetBlog.get().getContent();
		Integer user_id = targetBlog.get().getUserId();
		
		blogDto.setId(Integer.parseInt(id.toString()));
		blogDto.setTitle(title);
		blogDto.setContent(content);
		blogDto.setUserId(user_id);
		
		session.setAttribute("id", blogDto.getId());
		
		model.addAttribute("titleName", titleName);
		model.addAttribute("appName", appName);
		model.addAttribute("blogDto", blogDto);
		
		return "/blog/blogEdit";
	}
	
	@PostMapping("/blogEditConfirm")
	public String blogEditConfirm(@Validated @ModelAttribute BlogDto blogDto, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return "/blog/blogEdit";
		}
		
		this.session.setAttribute("title", blogDto.getTitle());
		this.session.setAttribute("content", blogDto.getContent());
		
		model.addAttribute("titleName", titleName);
		model.addAttribute("appName", appName);
		model.addAttribute("blogDto", blogDto);
		model.addAttribute("id", session.getAttribute("id"));
		
		return "/blog/blogEditConfirm";
	}
	
	@PostMapping("blogEditComplete")
	public String blogEditComplete(@Validated @ModelAttribute BlogDto blogDto, BindingResult result, Model model) {
		
		// 現在日時を取得
		Date dateNow = new Date();
		
		blogDto.setId((Integer)session.getAttribute("id"));
		blogDto.setTitle((String)session.getAttribute("title"));
		blogDto.setContent((String)session.getAttribute("content"));
		blogDto.setUpdatedAt(dateNow);
		
	    model.addAttribute("titleName", titleName);
	    model.addAttribute("appName", appName);
	    model.addAttribute("blogDto", blogDto);
	    
	    // ブログ登録
	    blogService.edit(blogDto);
	    
	    return "/blog/complete";
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
		model.addAttribute("preUri", (String) this.session.getAttribute("uri"));
		model.addAttribute("title", title);
		model.addAttribute("content", content);
		
		return "/blog/blogDetail";
	}
	
	@GetMapping("/input")
	public String input(@ModelAttribute BlogDto blogDto, Model model) {
		// URI取得
		this.session.setAttribute("uri", blogService.getUri());
		
		String title = (String)session.getAttribute("title");
		String content = (String)session.getAttribute("content");
		blogDto.setTitle(title);
		blogDto.setContent(content);
		
		model.addAttribute("titleName", titleName);
		model.addAttribute("appName", appName);
		model.addAttribute("blogDto", blogDto);
	    
		return "/blog/input";
	}
	
	@PostMapping("/confirm")
	public String confirm(@Validated @ModelAttribute BlogDto blogDto, BindingResult result, Model model) {		
		
		if (result.hasErrors()) {
			return "/blog/input";
		}
			
		// ログインユーザーを取得
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User loginUser = userServive.findByUsername(username);
		Integer user_id = loginUser.getUserId();
		blogDto.setUserId(user_id);
		session.setAttribute("user_id", user_id.toString());
		
		String title = blogDto.getTitle();
		String content = blogDto.getContent();
		session.setAttribute("title", title);
		session.setAttribute("content", content);
		
		model.addAttribute("titleName", titleName);
		model.addAttribute("appName", appName);
		model.addAttribute("blogDto", blogDto);
		
		return "/blog/confirm";
	}
	
	@PostMapping("complete")
	public String complete(@Validated @ModelAttribute BlogDto blogDto, BindingResult result, Model model) {
		
		// 現在日時を取得
		Date dateNow = new Date();
		
		String title = (String) session.getAttribute("title");
		String content = (String) session.getAttribute("content");
		String user_id = (String) session.getAttribute("user_id");
		
		blogDto.setTitle(title);
		blogDto.setContent(content);
		blogDto.setUserId(Integer.parseInt(user_id));
		blogDto.setUpdatedAt(dateNow);
	    
	    model.addAttribute("titleName", titleName);
	    model.addAttribute("appName", appName);
	    model.addAttribute("blogDto", blogDto);
	    
	    // ブログ登録
	    blogService.insert(blogDto);
	    
	    return "/blog/complete";
	}
}
