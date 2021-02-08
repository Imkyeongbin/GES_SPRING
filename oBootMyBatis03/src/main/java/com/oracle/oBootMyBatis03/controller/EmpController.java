package com.oracle.oBootMyBatis03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.oracle.oBootMyBatis03.service.EmpService;

@Controller
public class EmpController {
	@Autowired
	private EmpService es;
}
