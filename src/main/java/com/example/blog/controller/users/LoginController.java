package com.example.blog.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.blog.model.users.UsersDto;


@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login(@ModelAttribute UsersDto usersDto, Model model) {

	    model.addAttribute("usersDto", usersDto);
	    
		return "/user/login";
	}
}
