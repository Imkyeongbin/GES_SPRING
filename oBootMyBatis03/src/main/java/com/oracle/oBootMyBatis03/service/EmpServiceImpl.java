package com.oracle.oBootMyBatis03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootMyBatis03.dao.EmpDao;
import com.oracle.oBootMyBatis03.model.Emp;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpDao ed;
	
	@Override
	public List<Emp> listEmp(Emp emp) {
		System.out.println("EmpServiceImpl listEmp emp.getStart()=>"+ emp.getStart());
		System.out.println("EmpServiceImpl listEmp emp.getEnd()=>"+ emp.getEnd());
		List<Emp> empList = null;
		empList = ed.listEmp(emp);
		return empList;
	}

	@Override
	public int total() {
		int totCount = ed.total();
		System.out.println("EmpServiceImpl totCount=>"+ totCount);
		return totCount;
	}

	@Override
	public Emp detail(int empno) {
		System.out.println("EmpServiceImpl detail start...");
		Emp emp = ed.detail(empno);
		return emp;
	}

	@Override
	public int update(Emp emp) {
		System.out.println("EmpServiceImpl update start...");
		int result = ed.update(emp);
		return result;
	}

}
