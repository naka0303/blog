package com.example.blog.model.users;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


public class UsersDto {
	
	// ユーザーID
	private Integer user_id;
	
	// ユーザー名
	@NotBlank
	@Length(min=1, max=10)
	private String username;
	
	// 年齢
	@Positive
	private Integer age;
	
	// メールアドレス
	@NotBlank
	@Length(min=10, max=64)
	private String email;
	
	// パスワード
	@NotBlank
	@Length(min=6, max=20)
	private String password;
	
	// 権限
	private String auth;
	
	// 登録日時
	private Date created_at;
	
	// 更新日時
	private Date updated_at;
	
	// 削除日時
	private Date deleted_at;
	
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAuth() {
		return auth;
	}
	
	public void setAuth(String auth) {
		this.auth = auth;
	}
	
	public Date getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdatedAt() {
		return updated_at;
	}

	public void setUpdatedAt(Date updated_at) {
		this.updated_at = updated_at;
	}

	public Date getDeletedAt() {
		return deleted_at;
	}

	public void setDeletedAt(Date deleted_at) {
		this.deleted_at = deleted_at;
	}
}
