package com.example.blog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "registered_user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
	// ユーザーID
	private Integer user_id;
	
	@Column(name="username")
	// ユーザー名
	private String username;
	
	@Column(name="email")
	// メールアドレス
	private String email;
	
	@Column(name="age")
	// 年齢
	private Integer age;
	
	@Column(name="password")
	// パスワード
	private String password;
	
	@Column(name="auth")
	// 権限
	private String auth;
	
	// 登録日時
	private String created_at;
	
	// 更新日時
	private String updated_at;
	
	// 削除日時
	private String deleted_at;
	
	public Integer getUserId() {
		return user_id;
	}

	public void setUserId(Integer user_id) {
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
	
	public String getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdatedAt() {
		return updated_at;
	}

	public void setUpdatedAt(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getDeletedAt() {
		return deleted_at;
	}

	public void setDeletedAt(String deleted_at) {
		this.deleted_at = deleted_at;
	}
}
