package com.example.blog.controller.blog;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.blog.model.blog.Blog;
import com.example.blog.model.blog.BlogDto;
import com.example.blog.model.blog.BlogForm;
import com.example.blog.service.BlogService;
import com.example.blog.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class EditBlogController {
	
	private HttpSession session;
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	UserService userServive;
	
	public EditBlogController(HttpSession session) {
		this.session = session;
	}

	@GetMapping("/blogEdit/{blogId}")
	public String blogEdit(
			@PathVariable Long blogId,
			@ModelAttribute BlogForm blogForm,
			Model model) {
		
		BlogForm blogFormSession = (BlogForm) this.session.getAttribute("blogFormSession");
		if (blogFormSession != null) {
			model.addAttribute("titleSession", blogFormSession.getTitle());
			model.addAttribute("contentSession", blogFormSession.getContent());
		}
		
		// 編集ボタンが押されたブログ情報を取得
		Optional<Blog> targetBlog = blogService.findById(blogId);
		
		this.session.setAttribute("blogId", targetBlog.get().getBlogId());
		model.addAttribute("titleSession", targetBlog.get().getTitle());
		model.addAttribute("contentSession", targetBlog.get().getContent());
		model.addAttribute("blogForm", blogForm);
		return "/blog/blogEdit";
	}
	
	@PostMapping("/blogEditConfirm")
	public String blogEditConfirm(
			@Valid @ModelAttribute BlogForm blogForm,
			BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors()) {
			return "/blog/blogEdit";
		}
		
		this.session.setAttribute("blogFormSession", blogForm);
		model.addAttribute("blogForm", blogForm);
		model.addAttribute("blogId", this.session.getAttribute("blogId"));
		return "/blog/blogEditConfirm";
	}
	
	@PostMapping("blogEditComplete")
	public String blogEditComplete(
			@ModelAttribute BlogForm blogForm,
			Model model) {
				
		BlogForm blogFormSession = (BlogForm) this.session.getAttribute("blogFormSession");
		
		BlogDto blogDto = new BlogDto();
		blogDto.setBlogId((Integer) session.getAttribute("blogId"));
		blogDto.setTitle(blogFormSession.getTitle());
		blogDto.setContent(blogFormSession.getContent());
	    
		// ブログ編集
		blogService.edit(blogDto);
	    
		return "/blog/blogEditComplete";
	}
}
