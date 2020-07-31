package com.techtalentsouth.Tutorial;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String Home() {
		return "index";
	}

	@RequestMapping("/")
	public @ResponseBody String greeting() {
		return "This is a tester page for using JUnit tests";
	}

}
