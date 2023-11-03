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
@Table(name = "registered_user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
	// ユーザーID
	private Integer user_id;
	
	@Column(name="name")
	// ユーザー名
	private String name;
	
	@Column(name="email")
	// メールアドレス
	private String email;
	
	@Column(name="age")
	// 年齢
	private Integer age;
}
