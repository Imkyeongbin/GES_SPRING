package com.oracle.omvc043;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {
	private static final Logger logger = LoggerFactory.getLogger(RedirectController.class);
	
	@RequestMapping("/studentConfirm")
	public String studentRedirect(HttpServletRequest httpServletRequest, Model model) {
		String id = httpServletRequest.getParameter("id");
		String pw = "1234";
		
		model.addAttribute("id", id);
		model.addAttribute("pw",pw);
		
		if(id.equals("abc")) {
			return "redirect:studentOk";
		}
		return "redirect:studentErr";
		
	}
	
	@RequestMapping("/studentOk")
	public String studentOk(HttpServletRequest httpServletRequest, Model model) {
		String id = httpServletRequest.getParameter("id");
		String password = httpServletRequest.getParameter("pw");
		logger.info("password-> {}.", password);
		model.addAttribute("id",id);
		model.addAttribute("password", password);
		return "student/studentOk";
	}
	
	@RequestMapping("/studentErr")
	public String studentErr(Model model) {
		logger.info("studentErr Start...");
		return "student/studentErr";
	}
}
