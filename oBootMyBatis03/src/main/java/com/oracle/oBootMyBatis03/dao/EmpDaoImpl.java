package com.oracle.oBootMyBatis03.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMyBatis03.model.Emp;

@Repository
public class EmpDaoImpl implements EmpDao {
	//Mybatis 세션
	@Autowired
	private SqlSession session;
	
	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> listEmp = null;
		try {
			listEmp = session.selectList("listAll", emp);
			
		}catch(Exception e) {
			System.out.println("EmpDaoImpl listEmp e.getMessage()->"+ e.getMessage());
		}
		System.out.println("EmpDaoImpl listEmp listEmp.size()=>"+listEmp.size());
		return listEmp;
	}

	@Override
	public int total() {
		// One Row
		int totCount = session.selectOne("total");
		System.out.println("EmpDaoImpl totCount=>"+totCount);
		
		return totCount;
	}

	@Override
	public Emp detail(int empno) {
		Emp emp = null;
		try {
			//						mapper ID	, Parameter
			emp = session.selectOne("kbEmpSelOne", empno);
			System.out.println("EmpDaoImpl detail getEname->"+ emp.getEname());
		}catch (Exception e) {
			System.out.println("EmpDaoImpl detail e.getMessage()->"+ e.getMessage());
		}
		return emp;
	}

	@Override
	public int update(Emp emp) {
		int kkk = 0;
		try {
			kkk  = session.update("TKempUpdate",emp);
		}catch (Exception e) {
			System.out.println("EmpDaoImpl update e.getMessage()->"+ e.getMessage());
		}
		return kkk;
	}

}
