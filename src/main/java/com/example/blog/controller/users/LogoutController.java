package com.example.blog.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class LogoutController {
	
	@GetMapping("/logout")
	public String logout(@ModelAttribute Model model) {
	    
		return "/user/login";
	}
}
