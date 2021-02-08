package com.oracle.oBootMyBatis03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootMyBatis03.dao.EmpDao;
import com.oracle.oBootMyBatis03.model.Emp;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpDao ed;
	
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
