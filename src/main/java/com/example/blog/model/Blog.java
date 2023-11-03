package com.example.blog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
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
}
