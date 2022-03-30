package com.example.demo;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
	
	@RequestMapping("web")
	public String testWeb(Map<String, String> map) {
		map.put("info", "This is a web");
		
		return "index";
	}
	
	@GetMapping("/register")
	public String form(Model model) {
		User user = new User();
		model.addAttribute("user", user);
        
		return "register_form";
	}
	
	@PostMapping("/register")
	public String add(@ModelAttribute User user, Model model) {
		//System.out.println(user);
		model.addAttribute("user", user);
		
		return "register_success";
	}
}
