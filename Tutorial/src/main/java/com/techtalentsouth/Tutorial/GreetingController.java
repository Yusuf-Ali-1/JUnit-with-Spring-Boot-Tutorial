package com.techtalentsouth.Tutorial;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class GreetingController {

	private final GreetingService service;

	public GreetingController(GreetingService service) {
		this.service = service;
	}
	
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="Player1") String name, @RequestParam(name="status", required=false, defaultValue="Knight") String status, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("status", status);
		return "greeting";
	}

	@RequestMapping("/greeting")
	public @ResponseBody String greeting() {
		return service.greeting();
	}
	
	
	
	
	

}