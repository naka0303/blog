package com.example.blog.model.blogs;

import java.util.Date;


public class BlogsDto {
	
	// ブログID
	private Integer blog_id;
	
	// ブログタイトル
	private String title;
	
	// 投稿内容
	private String content;
	
	// ユーザーID(registered_userテーブルのuser_idと同じ)
	private Integer user_id;
	
	// 登録日時
	private Date created_at;
	
	// 更新日時
	private Date updated_at;
	
	// 削除日時
	private Date deleted_at;
	
	public Integer getBlogId() {
		return blog_id;
	}

	public void setBlogId(Integer blog_id) {
		this.blog_id = blog_id;
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

	public Date getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdatedAt() {
		return updated_at;
	}

	public void setUpdatedAt(Date dateNow) {
		this.updated_at = dateNow;
	}

	public Date getDeletedAt() {
		return deleted_at;
	}

	public void setDeletedAt(Date deleted_at) {
		this.deleted_at = deleted_at;
	}
}
