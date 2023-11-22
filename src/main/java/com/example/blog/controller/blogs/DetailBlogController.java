package com.example.blog.controller.blogs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.blog.model.CommentsUsersDto;
import com.example.blog.model.blogs.Blogs;
import com.example.blog.service.BlogsService;
import com.example.blog.service.CommentsService;
import com.example.blog.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class DetailBlogController {
	
	private HttpSession session;
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	BlogsService blogsService;
	
	@Autowired
	UserService userServive;
	
	@Autowired
	CommentsService blogsCommentsService;
	
	public DetailBlogController(HttpSession session) {
		this.session = session;
	}
	
	@GetMapping("/blogDetail/{blogId}")
	public String blogDetail(
			@PathVariable Long blogId,
			Model model) {
		
		// ブログ詳細情報取得
		Blogs blogDetail = blogsService.findDetail(blogId);
		
		// 指定ブログIDのコメント情報を取得
		List<CommentsUsersDto> allCommentByBlogId = blogsCommentsService.findByBlogId(Integer.parseInt(blogId.toString()));
		String commentContents[] = new String[allCommentByBlogId.size()];
		String commentUsername[] = new String[allCommentByBlogId.size()];
		
		int i = 0;
		for (CommentsUsersDto comment : allCommentByBlogId) {
			commentContents[i] = comment.getContent();
			commentUsername[i] = comment.getUsername();
			
			i++;
		}
		
		model.addAttribute("preUri", (String) this.session.getAttribute("preUri"));
		model.addAttribute("title", blogDetail.getTitle());
		model.addAttribute("content", blogDetail.getContent());
		model.addAttribute("commentContents", commentContents);
		model.addAttribute("commentUsername", commentUsername);
		
		return "/blog/blogDetail";
	}
}
