package cn.ccc.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.ccc.demo.dao.DemoJdbcDao;
import cn.ccc.demo.service.DemoJdbcService;
import cn.ccc.domain.PageBean;
import cn.ccc.domain.Student;

public class DemoJdbcServiceImpl implements DemoJdbcService {
	DemoJdbcDao dao = new DemoJdbcDao();

	/**
	 * ��ѯ����
	 * 
	 * @param student
	 * @return
	 */
	public List<Student> queryAllStudents(PageBean pageBean) {
		List<Student> list = dao.queryAllStudents(pageBean);
		return list;
	}

	/**
	 * ��ѯ�ܼ�¼��(��ҳ)
	 * 
	 * @return
	 */
	@Override
	public int queryStudentCount() {
		int total = dao.queryStudentCount();
		return total;
	}

	/**
	 * ����������ѯ
	 * 
	 * @param student
	 * @return
	 */
	@Override
	public List<Student> queryStudents(Student student) {
		List<Student> list = dao.queryStudents(student);
		return list;
	}

	/**
	 * ���
	 * 
	 * @param student
	 */
	@Override
	public Boolean addStudents(Student student) {
		return dao.addStudents(student);
	}

	/**
	 * ����ɾ��
	 * 
	 * @param student
	 */
	@Override
	public Integer delStudents(String studentIds) {
		int delNum = 0;
		String hql = "delete from Student s where 1=1 and s.studentId in(:ids)";
		Map<String, List<Integer>> map = new HashMap<>();
		List<Integer> list = new ArrayList<>();
		for (Object id : studentIds.split(",")) {
			list.add(Integer.parseInt(id.toString()));
		}
		map.put("ids", list);
		delNum = dao.delStudents(hql, map);

		return delNum;
	}

	/**
	 * ����ɾ��
	 * 
	 * @param student
	 */
	@Override
	public Boolean delStudent(Integer studentId) {
		if (studentId <= 0)
			return false;
		boolean isDel = false;

		String hql = "delete from Student s where 1=1 and s.studentId = :studentId";
		isDel = dao.delStudent(hql, studentId);

		return isDel;
	}

	/**
	 * ִ����Ӳ���ǰ����studentId��֤
	 * 
	 * @param student
	 * @return
	 */
	@Override
	public Boolean queryExistStudentId(Student studnet) {
		return dao.queryExistStudentId(studnet);
	}

	/**
	 * ����
	 * 
	 * @param student
	 * @return
	 */
	@Override
	public Boolean updateStudent(Student student) {
		if (student == null && student.equals(""))
			return false;

		boolean isUpdate = false;
		String hql = "update Student s set s.studentName = :studentName , s.age = :age , s.deptId = :deptId where 1=1 and s.studentId = :studentId";

		isUpdate = dao.updateStudent(hql,student);
		
		return isUpdate;
	}

}
