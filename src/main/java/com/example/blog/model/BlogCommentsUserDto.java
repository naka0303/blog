package com.example.blog.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class BlogCommentsUserDto {
	
	@Column(name="blog_id")
	// ブログID
	private Integer blog_id;
	
	@Column(name="content")
	// ブログコメント
	private String content;
	
	@Column(name="user_id")
	// ユーザーID
	private Integer user_id;
	
	@Column(name="username")
	// ユーザー名
	private String username;
}
