package com.oracle.oBootMyBatis03.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.oBootMyBatis03.model.Dept;
import com.oracle.oBootMyBatis03.model.DeptVO;
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
	
	@GetMapping(value="writeForm")
	public String writeForm(Model model) {
		// emp 관리자
		List<Emp> list = es.listManager();
		System.out.println("EmpController writeForm list.size()->"+list.size());
		model.addAttribute("empMngList",list);	// emp Manager List
		List<Dept> deptList = es.deptSelect();
		model.addAttribute("deptList", deptList); // dept
		return "writeForm";
	}
	
	@PostMapping(value="write")
	public String write(Emp emp, Model model) {
		System.out.println("emp.getHiredate->"+emp.getHiredate());
		int result = es.insert(emp);
		if(result > 0) return "redirect:list";
		else {
			model.addAttribute("msg","입력 실패 확인해 보세요");
			return "forward:writeForm";
		}
	}
	
	@RequestMapping(value="confirm")
	public String confirm(int empno, Model model) {
		System.out.println("EmpController confirm Start..");
		Emp emp = es.detail(empno);
		if(emp!= null) {
			model.addAttribute("msg","중복된 사번입니다");
			return "forward:writeForm";
		}else {
			model.addAttribute("msg","사용 가능한 사번입니다");
			return "forward:writeForm";
		}
	}
	
	@GetMapping(value="delete")
	public String delete(int empno, Model model) {
		int k = es.delete(empno);
		return "redirect:list";
	}
	
	//Procedure Test 입력화면
	@RequestMapping(value = "writeDeptIn", method = RequestMethod.GET)
	public String writeDeptIn(Model model) {
		System.out.println("writeDeptIn Start..");
		return "writeDept3";
	}
	
	
	@PostMapping(value="writeDept")
	public String writeDept(DeptVO deptVO, Model model) {
		es.insertDept(deptVO);	// Procedure Call
		if(deptVO == null) {
			System.out.println("deptVO NULL");
		}else {
			System.out.println("deptVO.getOdeptno()->"+deptVO.getOdeptno());
			System.out.println("deptVO.getOdname()->"+deptVO.getOdname());
			System.out.println("deptVO.getOloc()->"+deptVO.getOloc());
			model.addAttribute("msg", "정상 입력 되었습니다 ^^");
			model.addAttribute("dept", deptVO);
		}
		return "writeDept3";
	}
	
	// Procedure Cursor Test 입력화면
	@RequestMapping(value="writeDeptCursor", method = RequestMethod.GET)
	public String writeDeptCursor(Model model) {
		System.out.println("EmpController writeDeptCursor Start...");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sDeptno", 10);
		map.put("eDeptno", 50);
		System.out.println("EmpController writeDeptCursor selListDept Before");
		es.selListDept(map);
		System.out.println("EmpController writeDeptCursor selListDept After");
		List<Dept> deptList = (List<Dept>) map.get("dept");
		System.out.println("deptList.get(0).getDname()->"+deptList.get(0).getDname());
		System.out.println("deptList.get(0).getLoc()->"+deptList.get(0).getLoc());
		System.out.println("deptList Size->"+deptList.size());
		model.addAttribute("deptList",deptList);
		return "writeDeptCursor";
	}
}
