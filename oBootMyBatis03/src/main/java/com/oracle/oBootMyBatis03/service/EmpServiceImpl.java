package com.oracle.oBootMyBatis03.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootMyBatis03.dao.DeptDao;
import com.oracle.oBootMyBatis03.dao.EmpDao;
import com.oracle.oBootMyBatis03.dao.Member1Dao;
import com.oracle.oBootMyBatis03.model.Dept;
import com.oracle.oBootMyBatis03.model.DeptVO;
import com.oracle.oBootMyBatis03.model.Emp;
import com.oracle.oBootMyBatis03.model.EmpDept;
import com.oracle.oBootMyBatis03.model.Member1;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private   EmpDao  ed;
	@Autowired
	private   DeptDao  dd;
	@Autowired
	private Member1Dao md;

	@Override
	public List<Emp> listEmp(Emp emp) {
		System.out.println("EmpServiceImpl listEmp emp.getStart()=>" + emp.getStart());
		System.out.println("EmpServiceImpl listEmp emp.getEnd()=>" + emp.getEnd());
		List<Emp> empList = null;
		empList = ed.listEmp(emp);
		return empList;
	}

	@Override
	public int total() {
		int totCount = ed.total();
		System.out.println("EmpServiceImpl totCount=>" + totCount);
		return totCount;
	}

	@Override
	public Emp detail(int empno) {
		System.out.println("EmpServiceImpl detail ...");
//		Emp emp = new Emp();
//		emp =  ed.detail(empno);
		return ed.detail(empno);
	}

	@Override
	public int update(Emp emp) {
		// TODO Auto-generated method stub
		return ed.update(emp);
	}

	@Override
	public List<Emp> listManager() {
		return ed.listManager();
	}

	@Override
	public List<Dept> deptSelect() {
		return dd.deptSelect();
	}

	@Override
	public int insert(Emp emp) {
		// TODO Auto-generated method stub
		return ed.insert(emp);
	}

	@Override
	public int delete(int empno) {
		// TODO Auto-generated method stub
		return ed.delete(empno);
	}

	@Override
	public void insertDept(DeptVO deptVO) {
		System.out.println("EmpServiceImpl insertDept Start...");
	    dd.insertDept(deptVO);
	}

	@Override
	public void selListDept(Map<String, Object> map) {
	    System.out.println("EmpServiceImpl selListDept selListDept Before");
		dd.selListDept(map);
	}

	@Override
	public List<EmpDept> listEmpDept() {
		System.out.println("EmpServiceImpl listEmpDept Start...");
		return ed.listEmpDept();
	}

	@Override
	public List<EmpDept> listEmp(EmpDept empDept) {
		return ed.listEmp(empDept);
	}

	@Override
	public String deptName(int deptno) {
		return ed.deptName(deptno);
	}

	// Member1 -> InterCeptor
	@Override
	public int memCount(String id) {
		// TODO Auto-generated method stub
		System.out.println("EmpServiceImpl memCount id ->"+id);
	    return md.memCount(id); 
	}

	@Override
	public List<Member1> listMem(Member1 member1) {
		System.out.println("EmpServiceImpl listMem ");
		return md.listMem(member1);
	}

}
