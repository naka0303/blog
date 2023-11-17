package com.example.blog.controller.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.blog.model.user.UsersDto;
import com.example.blog.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class RegisterUserController {
	
	private HttpSession session;
	
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	UserService userService;
	
	public RegisterUserController(HttpSession session) {
		this.session = session;
	}
	
	@GetMapping("/signup")
	public String signup(@ModelAttribute UsersDto userDto, Model model) {
		
	    model.addAttribute("userDto", userDto);
	    
		return "/user/signup";
	}
	
	@PostMapping("/userConfirm")
	public String userConfirm(@Validated @ModelAttribute UsersDto userDto, BindingResult result, Model model) {
	    
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
		this.session.setAttribute("auth", "GENERAL");
		
		return "/user/confirm";
	}
	
	@PostMapping("userComplete")
	public String userComplete(@Validated @ModelAttribute UsersDto userDto, BindingResult result, Model model) throws ParseException {
		
		// 現在日時を取得
		Date dateNow = new Date();
		
		// updated_atに登録する仮の日付フォーマットを定義
		String kariDate = "1970-01-01 00:00:00";
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		String username = (String) this.session.getAttribute("username");
		String password = (String) this.session.getAttribute("password");
		String age = (String) this.session.getAttribute("age");
		String email = (String) this.session.getAttribute("email");
		String auth = (String) this.session.getAttribute("auth");
		
		userDto.setUsername(username);
		userDto.setPassword(password);
		userDto.setAge(Integer.parseInt(age));
		userDto.setEmail(email);
		userDto.setAuth(auth);
		userDto.setCreatedAt(dateNow);
		userDto.setUpdatedAt(sdformat.parse(kariDate));
		userDto.setDeletedAt(sdformat.parse(kariDate));
		
		// ブログ登録
		userService.save(userDto);
	    
		return "/user/complete";
	}
}
