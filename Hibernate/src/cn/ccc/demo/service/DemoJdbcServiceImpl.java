package cn.ccc.demo.service;

import java.util.List;

import cn.ccc.demo.dao.DemoJdbcDao;
import cn.ccc.demo.service.impl.DemoJdbcService;
import cn.ccc.domain.PageBean;
import cn.ccc.domain.Student;

public class DemoJdbcServiceImpl implements DemoJdbcService {
	DemoJdbcDao dao = new DemoJdbcDao();

	public List<Student> queryAllStudents(PageBean pageBean) {
		List<Student> list = dao.queryAllStudents(pageBean);
		return list;
	}

	@Override
	public int queryStudentCount() {
		int total = dao.queryStudentCount();
		return total;
	}

	public List<Student> queryStudents(Student student) {
		List<Student> list = dao.queryStudents(student);
		return list;
	}

	public Boolean addStudents(Student student) {
		return dao.addStudents(student);
	}

	public Boolean delStudent(Student student) {
		return dao.delStudent(student);
	}

}
