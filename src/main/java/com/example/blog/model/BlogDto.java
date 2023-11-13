package com.example.blog.model;

import java.util.Date;

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
	
	// 登録日時
	private String created_at;
	
	// 更新日時
	private Date updated_at;
	
	// 削除日時
	private String deleted_at;
	
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

	public String getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(String created_at) {
		this.created_at = created_at;
	}

	public Date getUpdatedAt() {
		return updated_at;
	}

	public void setUpdatedAt(Date dateNow) {
		this.updated_at = dateNow;
	}

	public String getDeletedAt() {
		return deleted_at;
	}

	public void setDeletedAt(String deleted_at) {
		this.deleted_at = deleted_at;
	}
}
