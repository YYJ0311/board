package com.dw.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/join")
	public String callJoinpage() {
		return "join";
	}
	
	@GetMapping("/login")
	public String callLoginpage() {
		return "login";
	}
}