package cn.ccc.demo.service.impl;

import java.util.List;

import cn.ccc.domain.PageBean;
import cn.ccc.domain.Student;

public interface DemoJdbcService {
	public List<Student> queryAllStudents(PageBean pageBean);
	
	public int queryStudentCount();

	public List<Student> queryStudents(Student student);

	public Boolean addStudents(Student student);

	public Boolean delStudent(Student student);
}
