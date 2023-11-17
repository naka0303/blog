package com.example.blog.controller.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.blog.model.blog.BlogDto;
import com.example.blog.model.blog.BlogForm;
import com.example.blog.service.BlogService;
import com.example.blog.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


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
	public String input(
			@ModelAttribute BlogForm blogForm,
			Model model) {
		return "/blog/input";
	}
	
	@PostMapping("/confirm")
	public String confirm(
			@Valid @ModelAttribute BlogForm blogForm,
			BindingResult bindingResult,
			Model model) {
		
		// 入力エラーが発生した場合は、入力画面へ遷移
		if (bindingResult.hasErrors()) {
			return "/blog/input";
		}
		
		session.setAttribute("blogForm", blogForm);
		model.addAttribute("blogForm", blogForm);
		
		return "/blog/confirm";
	}
	
	@PostMapping("complete")
	public String complete(
			Model model) {
		
		BlogForm blogForm = (BlogForm) session.getAttribute("blogForm");
		
		BlogDto blogDto = new BlogDto();
		blogDto.setTitle(blogForm.getTitle());
		blogDto.setContent(blogForm.getContent());
		
		// ブログ登録
		blogService.insert(blogDto);
	    
		return "/blog/complete";
	}
}
