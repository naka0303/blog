package com.example.blog.controller.blog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.blog.model.blog.BlogDto;
import com.example.blog.model.user.User;
import com.example.blog.service.BlogService;
import com.example.blog.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class PostBlogController {
	
	private HttpSession session;
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	UserService userServive;
	
	public PostBlogController(HttpSession session) {
		this.session = session;
	}
	
	@GetMapping("/input")
	public String input(@ModelAttribute BlogDto blogDto, Model model) {
		
		String title = (String)session.getAttribute("title");
		String content = (String)session.getAttribute("content");
		
		blogDto.setTitle(title);
		blogDto.setContent(content);
		
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
		
		String title = blogDto.getTitle();
		String content = blogDto.getContent();
		
		session.setAttribute("user_id", user_id.toString());
		session.setAttribute("title", title);
		session.setAttribute("content", content);
		
		model.addAttribute("blogDto", blogDto);
		
		return "/blog/confirm";
	}
	
	@PostMapping("complete")
	public String complete(@Validated @ModelAttribute BlogDto blogDto, BindingResult result, Model model) throws ParseException {
		// 現在日時を取得
		Date dateNow = new Date();
		
		// updated_atに登録する仮の日付フォーマットを定義
		String kariDate = "1970-01-01 00:00:00";
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		String title = (String) session.getAttribute("title");
		String content = (String) session.getAttribute("content");
		String user_id = (String) session.getAttribute("user_id");
		
		blogDto.setTitle(title);
		blogDto.setContent(content);
		blogDto.setUserId(Integer.parseInt(user_id));
		blogDto.setCreatedAt(dateNow);
		blogDto.setUpdatedAt(sdformat.parse(kariDate));
		blogDto.setDeletedAt(sdformat.parse(kariDate));
	
		model.addAttribute("blogDto", blogDto);
		
		// ブログ登録
		blogService.insert(blogDto);
	    
		return "/blog/complete";
	}
}
