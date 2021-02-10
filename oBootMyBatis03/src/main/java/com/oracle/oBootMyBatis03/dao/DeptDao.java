package com.oracle.oBootMyBatis03.dao;

import java.util.List;
import java.util.Map;

import com.oracle.oBootMyBatis03.model.Dept;
import com.oracle.oBootMyBatis03.model.DeptVO;

public interface DeptDao {
	List<Dept>	deptSelect();
	void		insertDept(DeptVO deptVO);
	void 		selListDept(Map<String, Object> map);	// Procedure Cursor
}
