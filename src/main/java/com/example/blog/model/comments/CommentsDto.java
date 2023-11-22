package com.example.blog.model.comments;

import java.util.Date;


public class CommentsDto {
	
	// コメントID
	private Integer comment_id;
	
	// ブログID
	private Integer blog_id;
	
	// コメント
	private String content;
	
	// コメント投稿ユーザーID
 	private Integer from_user_id;
	
	// 投稿日時
	private Date created_at;
	
	// 更新日時
	private Date updated_at;
	
	// 削除日時
	private Date deleted_at;

	public Integer getCommentId() {
		return comment_id;
	}

	public void setCommentId(Integer comment_id) {
		this.comment_id = comment_id;
	}

	public Integer getBlogId() {
		return blog_id;
	}

	public void setBlogId(Integer blog_id) {
		this.blog_id = blog_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getFromUserId() {
		return from_user_id;
	}

	public void setFromUserId(Integer from_user_id) {
		this.from_user_id = from_user_id;
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