package cn.ccc.demo.service;

import java.util.List;

import cn.ccc.domain.PageBean;
import cn.ccc.domain.Student;

public interface DemoJdbcService {

	/**
	 * ��ѯ����
	 * 
	 * @param student
	 * @return
	 */
	public List<Student> queryAllStudents(PageBean pageBean);

	/**
	 * ��ѯ�ܼ�¼��(��ҳ)
	 * 
	 * @return
	 */
	public int queryStudentCount();

	/**
	 * ����������ѯ
	 * 
	 * @param student
	 * @return
	 */
	public List<Student> queryStudents(Student student);

	/**
	 * ���
	 * 
	 * @param student
	 */
	public Boolean addStudents(Student student);

	/**
	 * ����ɾ��
	 * 
	 * @param student
	 */
	public Integer delStudents(String studentIds);

	/**
	 * ����ɾ��
	 * 
	 * @param studentId
	 */
	public Boolean delStudent(Integer studentId);

	/**
	 * ִ����Ӳ���ǰ����studentId��֤
	 * 
	 * @param student
	 * @return
	 */
	public Boolean queryExistStudentId(Student studnet);
	
	/**
	 * ����
	 * 
	 * @param student
	 * @return
	 */
	public Boolean updateStudent(Student student);
}
