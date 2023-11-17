package com.example.blog.controller.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.blog.model.blog.Blog;
import com.example.blog.model.blogComments.BlogComments;
import com.example.blog.service.BlogCommentService;
import com.example.blog.service.BlogService;
import com.example.blog.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class DetailBlogController {
	
	private HttpSession session;
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	UserService userServive;
	
	@Autowired
	BlogCommentService blogCommentService;
	
	public DetailBlogController(HttpSession session) {
		this.session = session;
	}
	
	@GetMapping("/blogDetail/{id}")
	public String blogDetail(@PathVariable Long id, Model model) {
		
		// ブログ情報詳細取得
		Blog blogDetail = blogService.findDetail(id);
		
		// 指定ブログIDのコメント情報を取得
		List<BlogComments> allCommentsByBlogId = blogCommentService.findByBlogId(Integer.parseInt(id.toString()));
		
		// :FIXME blog_commentsテーブルとregistered_userテーブルを結合し、"コメント"、"コメント投稿ユーザー名"を表示できるようにする
		// コメント配列格納
		String contents[] = new String[allCommentsByBlogId.size()];
		
		int i = 0;
		for (BlogComments commentsByBlogId : allCommentsByBlogId) {
			String content = commentsByBlogId.getContent();
			contents[i] = content.substring(0, content.length()-1);
			
			i++;
		}
		
		// 値渡し
		model.addAttribute("preUri", (String) this.session.getAttribute("preUri"));
		model.addAttribute("title", blogDetail.getTitle()); // ブログタイトル 
		model.addAttribute("content", blogDetail.getContent()); // 投稿内容
		model.addAttribute("contents", contents); // コメント一覧
		model.addAttribute("id", id);
		
		return "/blog/blogDetail";
	}
}
