package com.example.blog.model.comments;

import jakarta.validation.constraints.Max;


public class CommentsForm {
	
	// コメント
	@Max(value = 200)
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
