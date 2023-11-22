package com.example.blog.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommentsUsersDto {
 	
 	// 投稿内容
 	private String content;
 	
 	// ユーザー名
 	private String username;
 	
 	public CommentsUsersDto(Object[] objects) {
		this(
				(String)objects[0],
				(String)objects[1]
				);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
