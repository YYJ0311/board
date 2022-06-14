package com.dw.board.controller;

import javax.servlet.http.HttpSession;

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
	
	@GetMapping("/logout")
	public String callLogout(HttpSession httpSession) {
		// 세션 remove
		httpSession.removeAttribute("studentsId");
		httpSession.removeAttribute("studentsName");
		return "login";
	}
}
