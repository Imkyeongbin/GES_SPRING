package com.oracle.oBootMyBatis03.service;

import java.util.List;

import com.oracle.oBootMyBatis03.model.Emp;

public interface EmpService {
	List<Emp>	listEmp(Emp emp);
	int			total();
	Emp			detail(int empno);
	int 		update(Emp emp);
}
