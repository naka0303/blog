package com.example.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.blog.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {
	
	/**
	 * ブログ情報一覧取得(ユーザーごと)
	 */
	@Query(value = "SELECT * FROM posted_blog WHERE user_id = :user_id", nativeQuery = true)
	public List<Blog> findByUser(Integer user_id);
}
