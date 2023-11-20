package com.example.blog.repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.blog.model.BlogUserDto;
import com.example.blog.model.blog.Blog;


public interface BlogRepository extends JpaRepository<Blog, Long> {
	
	/**
	 * ブログ情報全取得
	 * @return 
	 */
	@Query(value = "SELECT blogs.blog_id, blogs.title, users.username, blogs.created_at " +
					"FROM blogs " +
					"INNER JOIN users ON (blogs.user_id = users.user_id) " +
					"ORDER BY blogs.blog_id ASC", nativeQuery = true)
	public List<Object[]> findAllJoinedUserRaw();
	
	default List<BlogUserDto> findAllJoinedUser() {
		return findAllJoinedUserRaw()
				.stream()
				.map(BlogUserDto::new)
				.collect(Collectors.toList());
	}
	
	/**
	 * ブログ情報全取得(ユーザーごと)
	 * @param userId
	 * @return
	 */
	@Query(value = "SELECT * FROM blogs " +
					"WHERE user_id = :userId ORDER BY blog_id ASC", nativeQuery = true)
	public List<Blog> findByUser(Integer userId);
	
	/**
	 * ブログ編集
	 * @param title
	 * @param content
	 * @param updatedAt
	 * @param blogId
	 */
	@Modifying
	@Query(value = "UPDATE Blog b SET b.title = :title, b.content = :content, b.updated_at = :updatedAt WHERE b.id = :blogId")
	public void edit(String title, String content, Date updatedAt, Integer blogId);
}
