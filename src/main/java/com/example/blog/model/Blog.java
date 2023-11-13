package com.example.blog.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "posted_blog")
public class Blog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	// ブログID
	private Integer id;
	
	@Column(name="title")
	// ブログタイトル
	private String title;
	
	@Column(name="content")
	// 投稿内容
	private String content;
	
	@Column(name="user_id")
	// ユーザーID(registered_userテーブルのuser_idと同じ)
	private Integer user_id;
	
	@Column(name="created_at")
	// 登録日時
	private String created_at;

	@Column(name="updated_at")
	// 更新日時
	private Date updated_at;

	@Column(name="deleted_at")
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

	public void setUpdatedAt(Date updated_at) {
		this.updated_at = updated_at;
	}

	public String getDeletedAt() {
		return deleted_at;
	}

	public void setDeletedAt(String deleted_at) {
		this.deleted_at = deleted_at;
	}
}
