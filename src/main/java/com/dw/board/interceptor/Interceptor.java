package com.dw.board.interceptor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dw.board.service.LogsService;
import com.dw.board.vo.LogsVO;

@Component // Component : 내가 만든 class를 spring한테 제어해달라는 의미 <--> 스프링이 만들고 관리하는 건 @Bean
public class Interceptor implements HandlerInterceptor{
	
	@Autowired
	LogsService logsService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//	컨트롤러에 도착하기 전에 가로채는 함수(preHandle)
			throws Exception {
		String url = request.getRequestURI();
		String ip = request.getHeader("X-forwarded-For");
		String httpMethod = request.getMethod();
		if(ip == null) ip = request.getRemoteAddr();
		
		System.out.println("접속한 IP : "+ip);
		System.out.println("요청받은 URL : "+url);
		System.out.println("HTTP Method : "+httpMethod);
		
		SimpleDateFormat formatter =
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA); // 한국 시간으로 강제로 맞춤
		String time = formatter.format(Calendar.getInstance().getTime());
		
		LogsVO vo = new LogsVO(); // VO는 bean 등록을 안 함
		vo.setUrl(url);
		vo.setIp(ip);
		vo.setHttpMethod(httpMethod);
		vo.setLatitude("36.3286904");
		vo.setLongitude("127.4229992");
		vo.setCreateAt(time);
		logsService.setLogs(vo);
		
		System.out.println("time : "+time);
		
		// 세션 체크
		HttpSession session = request.getSession();
		if(session.getAttribute("studentsId") != null) {
			int studentsId = (int) session.getAttribute("studentsId");
			String studentsName = (String) session.getAttribute("studentsName");
			System.out.println("세션에서 가져온 id : "+studentsId);
			System.out.println("세션에서 가져온 name : "+studentsName);
		}
		if(session.getAttribute("studentsId") == null) {
			response.sendRedirect("/login"); // 세션에 값이 없으면 /login 경로로 redirect
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
