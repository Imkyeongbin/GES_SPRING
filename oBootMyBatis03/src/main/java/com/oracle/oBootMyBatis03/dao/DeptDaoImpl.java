package com.oracle.oBootMyBatis03.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMyBatis03.model.Dept;
import com.oracle.oBootMyBatis03.model.DeptVO;

@Repository
public class DeptDaoImpl implements DeptDao {
	@Autowired
	private  SqlSession  session;

	@Override
	public List<Dept> deptSelect() {
		// TODO Auto-generated method stub
		return session.selectList("TKselectDept");
	}

	@Override
	public void insertDept(DeptVO deptVO) {
		System.out.println("DeptDaoImpl insertDept Start... ");
		session.selectOne("ProcDept", deptVO);
	}

	@Override
	public void selListDept(Map<String, Object> map) {
	    System.out.println("DeptDaoImpl selListDept ProcDeptList  Before");
		 session.selectOne("ProcDeptList",map);
		
	}

}
