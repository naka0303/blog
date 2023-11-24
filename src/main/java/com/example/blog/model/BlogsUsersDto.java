package com.example.blog.model;

import java.time.Instant;
import java.util.Date;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BlogsUsersDto {
	
	@Column(name="blog_id")
	// ブログID
	private Integer blog_id;
	
	@Column(name="title")
	// ブログタイトル
	private String title;
	
	@Column(name="username")
	// ユーザー名
	private String username;
	
	@Column(name="thumbnail")
	// サムネイル画像
	private byte[] thumbnail;
	
	@Column(name="created_at")
	// 登録日時
	private Date created_at;

	public BlogsUsersDto(Object[] objects) {
		this(
				(Integer)objects[0],
				(String)objects[1],
				(String)objects[2],
				(byte[])objects[3],
				Date.from((Instant)objects[4])
				);
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public byte[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Date getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(Date created_at) {
		this.created_at = created_at;
	}
}
