package com.oracle.oBootHello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.oBootHello.domain.Emp;

@Controller
public class HelloController {
    @RequestMapping("hello")
	public String  hello(Model model) {
		System.out.println("HelloController hello start...");
		
		model.addAttribute("data", "boot start");
		return "hello";		
	}
    // @ResponseBody 를 사용하면 뷰 리졸버( viewResolver )를 사용하지 않음
    @GetMapping("ajaxString")
    @ResponseBody
    public String ajaxString(@RequestParam("ajaxName") String aName) {
		System.out.println("HelloController ajaxString aName->"+aName);
    	return aName;
    }
	
	
    @GetMapping("ajaxEmp")
    @ResponseBody
    public Emp ajaxEmp(@RequestParam("empno") String empno , @RequestParam("ename") String ename )	{
		System.out.println("HelloController ajaxEmp empno->"+empno);
		Emp emp = new Emp();
    	emp.setEmpno(empno);
    	emp.setEname(ename);
    	return emp;
    }
}
