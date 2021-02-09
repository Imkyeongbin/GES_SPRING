package com.oracle.oBootMyBatis03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootMyBatis03.model.Emp;
import com.oracle.oBootMyBatis03.service.EmpService;
import com.oracle.oBootMyBatis03.service.Paging;

@Controller
public class EmpController {
	
	@Autowired
	private EmpService es;
	@RequestMapping(value="list")
	public String list(Emp emp, String currentPage, Model model) {
		System.out.println("EmpController list Start..");
		int total = es.total(); // Emp Count ->19
		System.out.println("EmpController total=>"+total);
		Paging pg = new Paging(total, currentPage);
		emp.setStart(pg.getStart()); 	// 시작시 1
		emp.setEnd(pg.getEnd()); 		// 시작시 10
		List<Emp> listEmp = es.listEmp(emp);
		System.out.println("EmpController listEmp.size()=>"+listEmp.size());
		model.addAttribute("listEmp",listEmp);
		model.addAttribute("pg",pg);
		model.addAttribute("totalCnt", total);
		return "list";
	}
	
	@GetMapping(value="detail")
	public String detail(int empno, Model model) {
		Emp emp = es.detail(empno);
		
		model.addAttribute("emp",emp);
		return "detail";
	}
	
	@GetMapping(value="updateForm")
	public String updateForm(int empno, Model model) {
		Emp emp = es.detail(empno);
		model.addAttribute("emp",emp);
		return "updateForm";
	}
	
	@PostMapping(value="update")
	public String update(Emp emp, Model model) {
		//System.out.println("hiredate = "+emp.getHiredate());
		int k = es.update(emp);
		System.out.println("es.update(emp) CNT -->"+ k);
		model.addAttribute("kkk",k);	// Test Controller간 Data 전달
		model.addAttribute("kk3","Message Test");	// Test Controller간 Data 전달
		return "redirect:list";
		//return "forward:list";	// Controller간 Data 전달시 활용(Model등에 담아서리...)
	}
}
