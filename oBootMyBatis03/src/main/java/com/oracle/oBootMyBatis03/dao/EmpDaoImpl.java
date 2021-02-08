package com.oracle.oBootMyBatis03.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMyBatis03.model.Emp;

@Repository
public class EmpDaoImpl implements EmpDao {
	
	@Autowired
	private SqlSession session;
	
	@Override
	public List<Emp> listEmp(Emp emp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int total() {
		// TODO Auto-generated method stub
		return 0;
	}

}
