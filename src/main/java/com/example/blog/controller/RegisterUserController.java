package com.example.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.blog.model.UserDto;
import com.example.blog.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class RegisterUserController {
	
	private HttpSession session;
	
	@Value("${title.name}")
	private String titleName;
	
	@Value("${app.name}")
	private String appName;
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	UserService service;
	
	@Autowired
	public RegisterUserController(HttpSession session) {
		this.session = session;
	}
	
	@GetMapping("/signup")
	public String signup(@ModelAttribute UserDto userDto, Model model) {
		model.addAttribute("titleName", titleName);
	    model.addAttribute("appName", appName);
	    model.addAttribute("userDto", userDto);
	    
		return "/user/signup";
	}
	
	@PostMapping("/user_confirm")
	public String user_confirm(@Validated @ModelAttribute UserDto userDto, BindingResult result, Model model) {		
		model.addAttribute("titleName", titleName);
	    model.addAttribute("appName", appName);
	    
	    if (result.hasErrors()) {
	    	return "/user/signup";
	    }
	    
	    String username = userDto.getUsername();
	    String password = userDto.getPassword();
	    Integer age = userDto.getAge();
	    String email = userDto.getEmail();
	    this.session.setAttribute("username", username);
	    this.session.setAttribute("password", password);
	    this.session.setAttribute("age", age.toString());
	    this.session.setAttribute("email", email);
		
		return "/user/confirm";
	}
	
	@PostMapping("user_complete")
	public String user_complete(@Validated @ModelAttribute UserDto userDto, BindingResult result, Model model) {
		model.addAttribute("titleName", titleName);
	    model.addAttribute("appName", appName);
	    
	    String username = (String) this.session.getAttribute("username");
	    String password = (String) this.session.getAttribute("password");
	    String age = (String) this.session.getAttribute("age");
	    String email = (String) this.session.getAttribute("email");
	    userDto.setUsername(username);
	    userDto.setPassword(password);
	    userDto.setAge(Integer.parseInt(age));
	    userDto.setEmail(email);
	    
	    // ブログ登録
	    service.save(userDto);
	    
	    return "/user/complete";
	}
}
