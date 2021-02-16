package com.oracle.oBootMyBatis03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootMyBatis03.model.Dept;
import com.oracle.oBootMyBatis03.model.SampleVO;
import com.oracle.oBootMyBatis03.service.EmpService;
// *****  Controller + ResposeBody
@RestController
public class EmpRestController {
	@Autowired
	private EmpService es;

	@RequestMapping("/sendVO2")
	public SampleVO sendVO2(int deptno) {
		  System.out.println("@RestController deptno->"+deptno);
		  SampleVO vo = new SampleVO();
		  vo.setFirstName("길동");
		  vo.setLastName("홍");
		  vo.setMno(deptno);
		  return vo;

	}
	@RequestMapping("/sendVO3")
	public List<Dept> sendVO3() {
		System.out.println("@RestController sendVO3  START");
		List<Dept> deptList = es.deptSelect();
		return deptList;

	}
}
