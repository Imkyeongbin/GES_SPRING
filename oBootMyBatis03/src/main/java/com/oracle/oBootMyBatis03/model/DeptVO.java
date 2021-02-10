package com.oracle.oBootMyBatis03.model;

public class DeptVO {
	private int 	deptno;
	private String 	dname;
	private String 	loc;
	private int 	odeptno;
	private String 	odname;
	private String 	oloc;
	
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public int getOdeptno() {
		return odeptno;
	}
	public void setOdeptno(int odeptno) {
		this.odeptno = odeptno;
	}
	public String getOdname() {
		return odname;
	}
	public void setOdname(String odname) {
		this.odname = odname;
	}
	public String getOloc() {
		return oloc;
	}
	public void setOloc(String oloc) {
		this.oloc = oloc;
	}
}
