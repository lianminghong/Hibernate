package cn.ccc.demo.service.impl;

import java.util.List;

import cn.ccc.domain.Student;

public interface DemoJdbcService {
	public List<Student> queryAllStudents(Student student);
	
	public List<Student> queryStudents(Student student);
	
	public void addStudents(Student student);
	
	public void delStudent(Student student);
}
