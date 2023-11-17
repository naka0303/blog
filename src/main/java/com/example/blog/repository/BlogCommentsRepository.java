package com.example.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.blog.model.blogComments.BlogComments;

public interface BlogCommentsRepository extends JpaRepository<BlogComments, Long> {
	
	/**
	 * コメント一覧取得(ブログIDごと)
	 */
	@Query(value = "SELECT * FROM blog_comments WHERE blog_id = :blog_id ORDER BY id ASC", nativeQuery = true)
	public List<BlogComments> findByBlogId(Integer blog_id);
}
