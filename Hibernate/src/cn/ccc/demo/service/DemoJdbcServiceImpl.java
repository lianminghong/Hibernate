package cn.ccc.demo.service;

import java.util.List;

import cn.ccc.demo.dao.DemoJdbcDao;
import cn.ccc.demo.service.impl.DemoJdbcService;
import cn.ccc.domain.Student;

public class DemoJdbcServiceImpl implements DemoJdbcService{
	DemoJdbcDao dao = new DemoJdbcDao();

	
	
	public List<Student> queryAllStudents(Student student){
		List<Student> list = dao.queryAllStudents(student);
		return list;
	}
	
	public List<Student> queryStudents(Student student){
		List<Student> list = dao.queryStudents(student);
		return list;
	}
	
	public void addStudents(Student student){
		dao.addStudents(student);
	}
	
	public void delStudent(Student student){
		dao.delStudent(student);
	}
}