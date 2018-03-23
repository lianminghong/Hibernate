package cn.ccc.demo.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import cn.ccc.domain.PageBean;
import cn.ccc.domain.Student;
import cn.hncu.hib.HibernateSessionFactory;

public class DemoJdbcDao {
	Session session;
	/**
	 * 查询所有
	 * 
	 * @param student
	 * @return
	 */
	public List<Student> queryAllStudents(PageBean pageBean) {
		session = HibernateSessionFactory.getSession();
		String hql = "from Student ";

		List<Student> list = null;
		try {
			Query query = session.createQuery(hql);
			query.setFirstResult((pageBean.getPage() - 1) * pageBean.getRows());
			query.setMaxResults(pageBean.getRows());
			list = query.list();
		} catch (Exception e) {
			System.out.println("查询全部异常，无法查询/n"+e.getMessage());
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return list;
	}

	
	/**
	 * 查询总记录数
	 * @return
	 */
	public int queryStudentCount(){
		session = HibernateSessionFactory.getSession();
		String hql = "select count(*) from Student s";
		int count = 0;
		try {
			Query query = session.createQuery(hql);
			count = Integer.parseInt(query.list().get(0).toString());
		} catch (Exception e) {
			System.out.println("查询全部异常，无法查询/n"+e.getMessage());
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return count;
	}
	
	/**
	 * 根据条件查询
	 * 
	 * @param student
	 * @return
	 */
	public List<Student> queryStudents(Student student) {
		boolean f1 = false, f2 = false, f3 = false;
		session = HibernateSessionFactory.getSession();
		String hql = "from Student s where 1=1";
		if (student.getStudentId() != null && student.getStudentId().trim().length() > 0) {
			hql += "s.studentId =:studentId";
			f1 = true;
		}
		if (student.getStudentName() != null && student.getStudentName().trim().length() > 0) {
			hql += "s.studentName =:studentName";
			f2 = true;
		}
		if (student.getDeptId() != null && student.getDeptId().trim().length() > 0) {
			hql += "s.deptId =:deptId";
			f3 = true;
		}

		Query query = session.createQuery(hql);
		if (f1) {
			query.setParameter("studentId", student.getStudentId().trim());
		}
		if (f2) {
			query.setParameter("studentName", "%" + student.getStudentName().trim() + "%");
		}
		if (f3) {
			query.setParameter("deptId", student.getDeptId().trim());
		}

		List<Student> list = query.list();
		return list;
	}

	/**
	 * 删除
	 * 
	 * @param student
	 */
	public Boolean delStudent(Student student) {
		Boolean isDel = false;
		String studentId = student.getStudentId();
		session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		try {
			session.delete(student);
			trans.commit();
			System.out.println("删除成功，删除ID为" + studentId);
			isDel = true;
		} catch (HibernateException e) {
			// trans.rollback();有异常内部会自行回滚
			System.out.println("删除异常，无法删除");
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return isDel;
	}

	/**
	 * 添加
	 * 
	 * @param student
	 */
	public Boolean addStudents(Student student) {
		Boolean isSuc = false;
		session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		try {
			session.saveOrUpdate(student);
			trans.commit();
			isSuc = true;
		} catch (HibernateException e) {
			System.out.println("插入或者更新异常");
			isSuc = false;
		} finally {
			// trans.rollback();有异常内部会自行回滚
			HibernateSessionFactory.closeSession();
		}
		return isSuc;
	}

}
