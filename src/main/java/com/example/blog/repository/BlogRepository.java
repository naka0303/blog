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
	 * ブログ情報一覧取得
	 * @return 
	 */
	@Query(value = "SELECT posted_blog.id, posted_blog.title, registered_user.username, posted_blog.created_at " +
					"FROM posted_blog " +
					"INNER JOIN registered_user ON (posted_blog.user_id = registered_user.user_id) " +
					"ORDER BY posted_blog.id ASC", nativeQuery = true)
	public List<Object[]> findAllJoinedUserRaw();
	
	default List<BlogUserDto> findAllJoinedUser() {
		return findAllJoinedUserRaw()
				.stream()
				.map(BlogUserDto::new)
				.collect(Collectors.toList());
	}
	
	/**
	 * ブログ情報一覧取得(ユーザーごと)
	 */
	@Query(value = "SELECT * FROM posted_blog WHERE user_id = :user_id ORDER BY id ASC", nativeQuery = true)
	public List<Blog> findByUser(Integer user_id);
	
	/**
	 * ブログ編集
	 */
	@Modifying
	@Query(value = "UPDATE Blog b SET b.title = :title, b.content = :content, b.updated_at = :updated_at WHERE b.id = :id")
	public void edit(String title, String content, Date updated_at, Integer id);
}
