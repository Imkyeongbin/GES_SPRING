package com.oracle.oBootMyBatis03.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMyBatis03.model.Emp;
import com.oracle.oBootMyBatis03.model.EmpDept;

@Repository
public class EmpDaoImpl implements EmpDao {
	// Mybatis 세션 
	@Autowired
	private SqlSession session;
	
	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> listEmp = null;
		try {
			listEmp = session.selectList("listAll", emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listEmp e.getMessage()->"+e.getMessage());
		}
		System.out.println("EmpDaoImpl listEmp listEmp.size()=>" + listEmp.size());
		return listEmp;
	}

	@Override
	public int total() {
		// One Row
		int totCount = session.selectOne("total");
		System.out.println("EmpDaoImpl totCount=>" + totCount);

		return totCount;
	}

	@Override
	public Emp detail(int empno) {
		System.out.println("EmpDaoImpl detail start..");
		
		Emp emp = new Emp();
		try {
			//                       mapper ID   ,   Parameter
			emp = session.selectOne("tkEmpSelOne", empno);
			System.out.println("EmpDaoImpl detail getEname->"+emp.getEname());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl detail Exception->"+e.getMessage());
		}
	return emp;
	}

	@Override
	public int update(Emp emp) {
		System.out.println("EmpDaoImpl update start..");
		int kkk = 0;
		try {
			// update/Delete 반환 행  Count , Insert -> 1
			kkk = session.update("TKempUpdate",emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl update Exception->"+e.getMessage());
		}
		
		
		return kkk;
	}

	@Override
	public List<Emp> listManager() {
		List<Emp> listEmpManager = null;
		try {
			listEmpManager = session.selectList("selectManager");
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listManager Exception->"+e.getMessage());
		}
		
		
		return listEmpManager;
	}

	@Override
	public int insert(Emp emp) {
		// TODO Auto-generated method stub
		return session.insert("insert",emp);
	}

	@Override
	public int delete(int empno) {
		System.out.println("EmpDaoImpl update start..");
		int kkk = 0;
		try {
			// update/Delete 반환 행  Count , Insert -> 1
			kkk  = session.delete("delete",empno);
			System.out.println("EmpDaoImpl delete kkk->"+kkk);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl delete Exception->"+e.getMessage());
		}

		// TODO Auto-generated method stub
		return kkk;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		System.out.println("EmpServiceImpl listEmpDept Start...");
		List<EmpDept> empDept = null;
		try {
			empDept  = session.selectList("TKlistEmpDept");
			System.out.println("EmpDaoImpl listEmpDept empDept.size()->"+empDept.size());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl delete Exception->"+e.getMessage());
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
