package com.example.blog.controller;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

public class BlogForm implements Serializable {
	
	/**
	 * ブログタイトル
	 * 必須
	 */
	@NotBlank
	private String title;
	
	/**
	 * 投稿内容
	 * 必須
	 */
	@NotBlank
	private String content;
}
