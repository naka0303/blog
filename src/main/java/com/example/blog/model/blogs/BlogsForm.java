package com.example.blog.model.blogs;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;


public class BlogsForm {

	// ブログタイトル
	@NotBlank
	@Length(min=1, max=50)
	private String title;

	// 投稿内容
	@NotBlank
	@Length(min=1, max=3000)
	private String content;

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
}