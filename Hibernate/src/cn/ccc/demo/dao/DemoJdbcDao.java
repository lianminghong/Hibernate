package cn.ccc.demo.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import cn.ccc.domain.Student;
import cn.hncu.hib.HibernateSessionFactory;
import net.sf.json.JSONArray;

public class DemoJdbcDao {

	/**
	 * ��ѯ����
	 * 
	 * @param student
	 * @return
	 */
	public List<Student> queryAllStudents() {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Student";

		List<Student> list = null;
		try {
			Query query = session.createQuery(hql);
			list = query.list();
		} catch (Exception e) {
			System.out.println("��ѯȫ���쳣���޷���ѯ");
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return list;
	}

	/**
	 * ����������ѯ
	 * 
	 * @param student
	 * @return
	 */
	public List<Student> queryStudents(Student student) {
		boolean f1 = false, f2 = false, f3 = false;
		Session session = HibernateSessionFactory.getSession();
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
	 * ɾ��
	 * 
	 * @param student
	 */
	public void delStudent(Student student) {
		String studentId = student.getStudentId();
		Session session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		try {
			session.delete(student);
			trans.commit();
			System.out.println("ɾ���ɹ���ɾ��IDΪ" + studentId);
		} catch (HibernateException e) {
			// trans.rollback();���쳣�ڲ������лع�
			System.out.println("ɾ���쳣���޷�ɾ��");
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	/**
	 * ���
	 * 
	 * @param student
	 */
	public void addStudents(Student student) {
		Session session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		try {
			session.saveOrUpdate(student);
			trans.commit();
		} catch (HibernateException e) {
			System.out.println("������߸����쳣");
		} finally {
			// trans.rollback();���쳣�ڲ������лع�
			HibernateSessionFactory.closeSession();
		}
	}

}
