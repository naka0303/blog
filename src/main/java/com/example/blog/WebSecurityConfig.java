package com.example.blog;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
    private DataSource dataSource;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorizeRequests ->
				authorizeRequests
					.requestMatchers("/css/**", "/login", "/signup", "user_confirm", "user_complete").permitAll()
					.anyRequest().authenticated()
			)
			.formLogin(formLogin -> 
				formLogin
					.loginPage("/login")
					.permitAll()
			)
			.logout(logout ->
				logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login")
			);
			
        return http.build();
	}
	
	@Bean // このメソッドの返り値をSpringのBeanとして登録します
    public PasswordEncoder passwordEncoder() { // パスワードエンコーダー（パスワードのハッシュ化）を提供するメソッド
        return new BCryptPasswordEncoder(); // パスワードをBCrypt方式でハッシュ化するエンコーダーを返します
    }
}
