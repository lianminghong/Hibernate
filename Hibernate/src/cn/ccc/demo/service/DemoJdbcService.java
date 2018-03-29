package cn.ccc.demo.service;

import java.util.List;

import cn.ccc.domain.PageBean;
import cn.ccc.domain.Student;

public interface DemoJdbcService {

	/**
	 * 查询所有
	 * 
	 * @param student
	 * @return
	 */
	public List<Student> queryAllStudents(PageBean pageBean);

	/**
	 * 查询总记录数(分页)
	 * 
	 * @return
	 */
	public int queryStudentCount();

	/**
	 * 根据条件查询
	 * 
	 * @param student
	 * @return
	 */
	public List<Student> queryStudents(Student student);

	/**
	 * 添加
	 * 
	 * @param student
	 */
	public Boolean addStudents(Student student);

	/**
	 * 批量删除
	 * 
	 * @param student
	 */
	public Integer delStudents(String studentIds);

	/**
	 * 单条删除
	 * 
	 * @param studentId
	 */
	public Boolean delStudent(Integer studentId);

	/**
	 * 执行添加操作前进行studentId验证
	 * 
	 * @param student
	 * @return
	 */
	public Boolean queryExistStudentId(Student studnet);
	
	/**
	 * 更新
	 * 
	 * @param student
	 * @return
	 */
	public Boolean updateStudent(Student student);
}
