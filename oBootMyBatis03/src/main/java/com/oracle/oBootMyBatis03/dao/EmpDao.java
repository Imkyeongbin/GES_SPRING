package com.oracle.oBootMyBatis03.dao;

import java.util.List;

import com.oracle.oBootMyBatis03.model.Emp;

public interface EmpDao {

	List<Emp>	listEmp(Emp emp);
	int			total();
	Emp			detail(int empno);
	int			update(Emp emp);
	
}
