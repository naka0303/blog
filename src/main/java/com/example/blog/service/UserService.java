package com.example.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blog.model.User;
import com.example.blog.model.UserDto;
import com.example.blog.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override // UserDetailsServiceインターフェースのメソッドを上書きします
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username); // ユーザー名でユーザーを検索します
	    if (user == null) {
	    	throw new UsernameNotFoundException("User not found"); // ユーザーが見つからない場合、例外をスローします
	    }
	    return new UserPrincipal(user); // ユーザーが見つかった場合、UserPrincipalを作成し返します
	}
	
	public User findByUsername(String username) {
        return repository.findByUsername(username); // ユーザー名でユーザーを検索し返します
	}
	
	@Transactional // トランザクションを開始します。メソッドが終了したらトランザクションがコミットされます。
    public void save(UserDto userDto) {
		// UserDtoからUserへの変換
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());
        // パスワードをハッシュ化してから保存
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAuth(userDto.getAuth());

        // データベースへの保存
        repository.save(user); // UserRepositoryを使ってユーザーをデータベースに保存します
    }
}
