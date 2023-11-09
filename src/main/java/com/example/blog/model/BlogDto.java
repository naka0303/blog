package com.example.blog.model;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;


public class BlogDto {
	
	// ブログID
	private Integer id;
	
	// ブログタイトル
	@NotBlank
	@Length(min=1, max=50)
	private String title;
	
	// 投稿内容
	@NotBlank
	@Length(min=1, max=3000)
	private String content;
	
	// ユーザーID(registered_userテーブルのuser_idと同じ)
	private Integer user_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserId() {
		return user_id;
	}

	public void setUserId(Integer user_id) {
		this.user_id = user_id;
	}
}
