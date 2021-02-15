package com.oracle.oBootMyBatis03.service;

import java.util.List;
import java.util.Map;

import com.oracle.oBootMyBatis03.model.Dept;
import com.oracle.oBootMyBatis03.model.DeptVO;
import com.oracle.oBootMyBatis03.model.Emp;
import com.oracle.oBootMyBatis03.model.EmpDept;

public interface EmpService {
	// EmpDao
	List<Emp>		listEmp(Emp emp);
	int				total();
	Emp				detail(int empno);
	int 			update(Emp emp);
	List<Emp>		listManager();
	int				insert(Emp emp);
	int				delete(int empno);
	List<EmpDept> 	listEmpDept();
	List<EmpDept>	listEmp(EmpDept empDept);
	String 			deptName(int deptno);
	
	// DeptDao
	List<Dept>	deptSelect();
	void		insertDept(DeptVO deptVO);
	void		selListDept(Map<String,Object> map);
		
}
