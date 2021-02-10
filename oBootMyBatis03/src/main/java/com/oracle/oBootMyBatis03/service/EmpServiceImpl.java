package com.oracle.oBootMyBatis03.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootMyBatis03.dao.DeptDao;
import com.oracle.oBootMyBatis03.dao.EmpDao;
import com.oracle.oBootMyBatis03.model.Dept;
import com.oracle.oBootMyBatis03.model.DeptVO;
import com.oracle.oBootMyBatis03.model.Emp;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpDao ed;
	@Autowired
	private DeptDao dd;
	
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

	@Override
	public List<Emp> listManager() {
		List<Emp> managerList = null;
		managerList = ed.listManager();
		return managerList;
	}

	@Override
	public List<Dept> deptSelect() {
		List<Dept> deptList = null;
		deptList = dd.deptSelect();
		return deptList;
	}

	@Override
	public int insert(Emp emp) {
		int result = 0;
		result = ed.insert(emp);
		return result;
	}

	@Override
	public int delete(int empno) {
		return ed.delete(empno);
	}

	@Override
	public void insertDept(DeptVO deptVO) {
		System.out.println("EmpServiceImpl insertDept Start...");
		dd.insertDept(deptVO);
		
	}

	@Override
	public void selListDept(Map<String, Object> map) {
		System.out.println("EmpServiceImpl selListDept Before");	
		dd.selListDept(map);
		
	}

}
