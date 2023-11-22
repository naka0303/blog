package com.example.blog.controller.blogs;

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

import com.example.blog.model.blogs.Blogs;
import com.example.blog.model.blogs.BlogsDto;
import com.example.blog.model.blogs.BlogsForm;
import com.example.blog.service.BlogsService;
import com.example.blog.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class EditBlogController {
	
	private HttpSession session;
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	BlogsService blogsService;
	
	@Autowired
	UserService userServive;
	
	public EditBlogController(HttpSession session) {
		this.session = session;
	}

	@GetMapping("/blogEdit/{blogId}")
	public String blogEdit(
			@PathVariable Long blogId,
			@ModelAttribute BlogsForm blogsForm,
			Model model) {
		
		BlogsForm blogsFormSession = (BlogsForm) this.session.getAttribute("blogFormSession");
		if (blogsFormSession != null) {
			model.addAttribute("titleSession", blogsFormSession.getTitle());
			model.addAttribute("contentSession", blogsFormSession.getContent());
		}
		
		// 編集ボタンが押されたブログ情報を取得
		Optional<Blogs> targetBlog = blogsService.findById(blogId);
		
		this.session.setAttribute("blogId", targetBlog.get().getBlogId());
		model.addAttribute("titleSession", targetBlog.get().getTitle());
		model.addAttribute("contentSession", targetBlog.get().getContent());
		model.addAttribute("blogsForm", blogsForm);
		return "/blog/blogEdit";
	}
	
	@PostMapping("/blogEditConfirm")
	public String blogEditConfirm(
			@Valid @ModelAttribute BlogsForm blogsForm,
			BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors()) {
			return "/blog/blogEdit";
		}
		
		this.session.setAttribute("blogsFormSession", blogsForm);
		model.addAttribute("blogsForm", blogsForm);
		model.addAttribute("blogId", this.session.getAttribute("blogId"));
		return "/blog/blogEditConfirm";
	}
	
	@PostMapping("blogEditComplete")
	public String blogEditComplete(
			@ModelAttribute BlogsForm blogsForm,
			Model model) {
				
		BlogsForm blogsFormSession = (BlogsForm) this.session.getAttribute("blogsFormSession");
		
		BlogsDto blogsDto = new BlogsDto();
		blogsDto.setBlogId((Integer) session.getAttribute("blogId"));
		blogsDto.setTitle(blogsFormSession.getTitle());
		blogsDto.setContent(blogsFormSession.getContent());
	    
		// ブログ編集
		blogsService.edit(blogsDto);
	    
		return "/blog/blogEditComplete";
	}
}
