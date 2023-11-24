package com.example.blog.controller.blogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.blog.model.blogs.BlogsDto;
import com.example.blog.model.blogs.BlogsForm;
import com.example.blog.service.BlogsService;
import com.example.blog.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class PostBlogController {
	
	private HttpSession session;
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	BlogsService blogsService;
	
	@Autowired
	UserService userServive;
	
	public PostBlogController(HttpSession session) {
		this.session = session;
	}
	
	@GetMapping("/input")
	public String input(
			@ModelAttribute BlogsForm blogsForm,
			Model model) {
		
		BlogsForm blogsFormSession = (BlogsForm) this.session.getAttribute("blogsFormSession");
		if (blogsFormSession != null) {
			model.addAttribute("titleSession", blogsFormSession.getTitle());
			model.addAttribute("contentSession", blogsFormSession.getContent());
		}
		
		model.addAttribute("blogsForm", blogsForm);
		return "/blog/input";
	}
	
	@PostMapping("/confirm")
	public String confirm(
			@Valid @ModelAttribute BlogsForm blogsForm,
			BindingResult bindingResult,
			Model model) {
		
		// 入力エラーが発生した場合は、入力画面へ遷移
		if (bindingResult.hasErrors()) {
			return "/blog/input";
		}
		
		// サムネイル画像をbyte型に変換
		byte[] thumbnailBytes = blogsService.getImageBytes(blogsForm.getThumbnail());
		
		this.session.setAttribute("thumbnailBytes", thumbnailBytes);
		this.session.setAttribute("blogsFormSession", blogsForm);
		model.addAttribute("blogsForm", blogsForm);
		return "/blog/confirm";
	}
	
	@PostMapping("complete")
	public String complete(
			@ModelAttribute BlogsForm blogsForm,
			Model model) {
		
		BlogsForm blogsFormSession = (BlogsForm) this.session.getAttribute("blogsFormSession");
		
		BlogsDto blogsDto = new BlogsDto();
		blogsDto.setTitle(blogsFormSession.getTitle());
		blogsDto.setContent(blogsFormSession.getContent());
		blogsDto.setThumbnail((byte[]) this.session.getAttribute("thumbnailBytes"));
		
		// ブログ登録
		blogsService.insert(blogsDto);
		return "/blog/complete";
	}
}
