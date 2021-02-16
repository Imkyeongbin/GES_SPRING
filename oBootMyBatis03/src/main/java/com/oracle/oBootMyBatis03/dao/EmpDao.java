package com.oracle.oBootMyBatis03.dao;

import java.util.List;

import com.oracle.oBootMyBatis03.model.Emp;
import com.oracle.oBootMyBatis03.model.EmpDept;


public interface EmpDao {
	List<Emp>     listEmp(Emp emp);
	int           total(); 
	Emp 	      detail(int empno);
	int           update(Emp emp);   
	List<Emp>     listManager();
	int           insert(Emp emp);
	int           delete(int empno);
	List<EmpDept> listEmpDept();
	List<EmpDept> listEmp(EmpDept empDept);
	String	      deptName(int deptNo);


}
