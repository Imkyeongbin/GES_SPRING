package com.oracle.oBootMyBatis03.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMyBatis03.model.Emp;
import com.oracle.oBootMyBatis03.model.EmpDept;

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

	@Override
	public List<Emp> listManager() {
		List<Emp> listEmpManager = null;
		try {
			listEmpManager = session.selectList("selectManager");
		}catch (Exception e) {
			System.out.println("EmpDaoImpl listManager Exception->"+ e.getMessage());
		}

		return listEmpManager;
	}

	@Override
	public int insert(Emp emp) {
		int result = 0;
		try {
			result = session.insert("insert",emp);
		}catch (Exception e) {
			System.out.println("EmpDaoImpl insert Exception->"+ e.getMessage());
		}
		return result;
	}

	@Override
	public int delete(int empno) {
		int result = 0;
		try {
			result = session.delete("delete",empno);
		}catch (Exception e) {
			System.out.println("EmpDaoImpl delete Exception->"+ e.getMessage());
		}
		return result;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		List<EmpDept> empDept = null;
		try {
			empDept = session.selectList("TKlistEmpDept");
			System.out.println("EmpDaoImpl listEmpDept empDept.size()->"+empDept.size());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listEmpDept Exception->"+ e.getMessage());
		}
		return empDept;
	}

	@Override
	public List<EmpDept> listEmp(EmpDept empDept) {
		
		return session.selectList("TKlistEmpDept", empDept);
	}

	@Override
	public String deptName(int deptNo) {
		return session.selectOne("TKdeptName",deptNo);
	}
	
}
