package com.example.blog.controller.blog;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.blog.model.blog.Blog;
import com.example.blog.model.blog.BlogDto;
import com.example.blog.service.BlogService;
import com.example.blog.service.UserService;

import jakarta.servlet.http.HttpSession;


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
		
	    model.addAttribute("blogDto", blogDto);
	    
	    // ブログ登録
	    blogService.edit(blogDto);
	    
	    return "/blog/complete";
	}
}
