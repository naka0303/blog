package com.example.blog.controller;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserForm implements Serializable {
	
	/**
	 * ユーザー名
	 * 必須、10文字以内
	 */
	@NotBlank
	@Size(max=10)
	private String username;
	
	/**
	 * メールアドレス
	 * 必須、64文字以内
	 */
	@NotBlank
	@Email
	@Size(max=64)
	private String email;
	
	/**
	 * 年齢
	 */
	private Integer age;
}
