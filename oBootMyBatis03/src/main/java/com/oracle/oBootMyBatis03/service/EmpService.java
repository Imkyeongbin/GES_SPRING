package com.oracle.oBootMyBatis03.service;

import java.util.List;
import java.util.Map;

import com.oracle.oBootMyBatis03.model.Dept;
import com.oracle.oBootMyBatis03.model.DeptVO;
import com.oracle.oBootMyBatis03.model.Emp;
import com.oracle.oBootMyBatis03.model.EmpDept;
import com.oracle.oBootMyBatis03.model.Member1;

public interface EmpService {
	// EmpDao
    List<Emp>     listEmp(Emp emp);
    int           total();
    Emp 	      detail(int empno);
	int 	      update(Emp emp);
	List<Emp>     listManager();
	int           insert(Emp emp);
	int     	  delete(int empno);
	List<EmpDept> listEmpDept();
	List<EmpDept> listEmp(EmpDept empDept);
	String        deptName(int deptno);
	int           memCount(String id); 
	List<Member1> listMem(Member1 member1);
	
	// DeptDao
	List<Dept>    deptSelect();
	void          insertDept(DeptVO deptVO); 
	void          selListDept(Map<String,Object> map); 



}
