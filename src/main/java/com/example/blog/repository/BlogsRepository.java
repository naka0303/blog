package com.example.blog.repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.blog.model.BlogsUsersDto;
import com.example.blog.model.blogs.Blogs;


public interface BlogsRepository extends JpaRepository<Blogs, Long> {
	
	/**
	 * ブログ情報全取得
	 * @return 
	 */
	@Query(value = "SELECT blogs.blog_id, blogs.title, users.username, blogs.created_at " +
					"FROM blogs " +
					"INNER JOIN users ON (blogs.user_id = users.user_id) " +
					"ORDER BY blogs.blog_id ASC", nativeQuery = true)
	public List<Object[]> findAllJoinedUserRaw();
	
	default List<BlogsUsersDto> findAllJoinedUser() {
		return findAllJoinedUserRaw()
				.stream()
				.map(BlogsUsersDto::new)
				.collect(Collectors.toList());
	}
	
	/**
	 * ブログ情報全取得(ユーザーごと)
	 * @param userId
	 * @return
	 */
	@Query(value = "SELECT * FROM blogs " +
					"WHERE user_id = :userId ORDER BY blog_id ASC", nativeQuery = true)
	public List<Blogs> findByUser(Integer userId);
	
	/**
	 * ブログ編集
	 * @param title
	 * @param content
	 * @param updatedAt
	 * @param blogId
	 */
	@Modifying
	@Query(value = "UPDATE Blogs b SET b.title = :title, b.content = :content, b.updated_at = :updatedAt WHERE b.id = :blogId")
	public void edit(String title, String content, Date updatedAt, Integer blogId);
}
