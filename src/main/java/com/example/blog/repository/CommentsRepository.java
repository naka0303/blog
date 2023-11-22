package com.example.blog.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.blog.model.CommentsUsersDto;
import com.example.blog.model.comments.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
	
	/**
	 * 投稿コメント内容、投稿ユーザー名全取得(ブログIDごと)
	 * @param blogId
	 * @return
	 */
	@Query(value = "SELECT comments.content, users.username "
			+ "FROM comments "
			+ "LEFT JOIN users ON (comments.from_user_id = users.user_id) "
			+ "WHERE comments.blog_id = :blogId", nativeQuery = true)
	public List<Object[]> findByBlogIdRaw(Integer blogId);
	
	default List<CommentsUsersDto> findByBlogId(Integer blogId) {
		return findByBlogIdRaw(blogId)
				.stream()
				.map(CommentsUsersDto::new)
				.collect(Collectors.toList());
	}
}