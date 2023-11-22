package com.example.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blog.model.users.Users;
import com.example.blog.model.users.UsersDto;
import com.example.blog.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = userRepository.findByUsername(username);
	    if (users == null) {
	    	throw new UsernameNotFoundException("User not found");
	    }
	    return new UserPrincipal(users);
	}
	
	public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
	}
	
	@Transactional
	public void save(UsersDto userDto) {
		// UserDtoからUserへの変換
		Users users = new Users();
		
		users.setUsername(userDto.getUsername());
		users.setAge(userDto.getAge());
		users.setEmail(userDto.getEmail());
		users.setPassword(passwordEncoder.encode(userDto.getPassword())); // パスワードをハッシュ化してセット
		users.setAuth(userDto.getAuth());
		users.setCreatedAt(userDto.getCreatedAt());
		users.setUpdatedAt(userDto.getUpdatedAt());
		users.setDeletedAt(userDto.getDeletedAt());
		
		// DB保存
		userRepository.save(users);
    }
}
