package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.model.users.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	Users findByUsername(String username);
}
