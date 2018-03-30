package cn.ccc.demo.dao;

import java.util.List;
import java.util.Map;

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
	 * ��ѯ����
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
			System.out.println("��ѯȫ���쳣���޷���ѯ\n" + e.getMessage());
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return list;
	}

	/**
	 * ��ѯ�ܼ�¼��(��ҳ)
	 * 
	 * @return
	 */
	public int queryStudentCount() {
		session = HibernateSessionFactory.getSession();
		String hql = "select count(*) from Student s";
		int count = 0;
		try {
			Query query = session.createQuery(hql);
			count = Integer.parseInt(query.list().get(0).toString());
		} catch (Exception e) {
			System.out.println("��ѯȫ���쳣���޷���ѯ\n" + e.getMessage());
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return count;
	}

	/**
	 * ����������ѯ
	 * 
	 * @param student
	 * @return
	 */
	public List<Student> queryStudents(Student student) {
		boolean f1 = false, f2 = false, f3 = false;
		session = HibernateSessionFactory.getSession();
		String hql = "from Student s where 1=1";
		if (student.getStudentId() != null && student.getStudentId() > 0) {
			hql += " and s.studentId =:studentId";
			f1 = true;
		}
		if (student.getStudentName() != null && student.getStudentName().trim().length() > 0) {
			hql += " and s.studentName =:studentName";
			f2 = true;
		}
		if (student.getDeptId() != null && student.getDeptId().trim().length() > 0) {
			hql += " and s.deptId =:deptId";
			f3 = true;
		}

		List<Student> list = null;
		try {
			Query query = session.createQuery(hql);
			if (f1) {
				query.setParameter("studentId", student.getStudentId());
			}
			if (f2) {
				query.setParameter("studentName", "%" + student.getStudentName().trim() + "%");
			}
			if (f3) {
				query.setParameter("deptId", student.getDeptId().trim());
			}
			list = query.list();
		} catch (Exception e) {
			System.out.println("��ѯȫ���쳣���޷���ѯ\n" + e.getMessage());
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return list;
	}

	/**
	 * ����ɾ��
	 * 
	 * @param hql
	 * @param map
	 * @return
	 */
	public Integer delStudents(String hql, Map<String, List<Integer>> map) {
		int delNum = 0;

		session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		Query query = session.createQuery(hql);
		if (map != null && map.size() > 0) {
			for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
				query.setParameterList(entry.getKey(), entry.getValue());
			}
		}
		try {
			delNum = query.executeUpdate();// ����ɾ������
			System.out.println("ɾ����" + delNum + "��¼");
			trans.commit();
		} catch (Exception e) {
			System.out.println("����ɾ���쳣���޷�ɾ��\n" + e);
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return delNum;
	}

	/**
	 * ����ɾ��
	 * 
	 * @param hql
	 * @param studentId
	 * @return
	 */
	public Boolean delStudent(String hql, Integer studentId) {
		int delNum = 0;
		boolean isDel = false;

		session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setParameter("studentId", studentId);
		try {
			delNum = query.executeUpdate();// ����ɾ������
			System.out.println("ɾ����" + delNum + "��¼");
			trans.commit();
			if (delNum > 0) {
				isDel = true;
			}
		} catch (Exception e) {
			System.out.println("ɾ�������쳣���޷�ɾ��\n" + e);
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return isDel;

	}

	/**
	 * ���
	 * 
	 * @param student
	 */
	public Boolean addStudents(Student student) {
		Boolean isSuc = false;
		session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		try {
			// session.saveOrUpdate(student);
			session.save(student);
			trans.commit();
			isSuc = true;
		} catch (HibernateException e) {
			System.out.println("�����쳣:" + e.getMessage());
			isSuc = false;
		} finally {
			// trans.rollback();���쳣�ڲ������лع�
			HibernateSessionFactory.closeSession();
		}
		return isSuc;
	}

	/**
	 * ִ����Ӳ���ǰ����studentId��֤
	 * 
	 * @param student
	 * @return
	 */
	public Boolean queryExistStudentId(Student student) {
		Boolean isExits = false;
		String hql = "from Student s where 1=1";
		session = HibernateSessionFactory.getSession();
		if (student.getStudentId() != null && student.getStudentId() > 0) {
			hql += " and s.studentId =:studentId";
		}

		List<Student> list = null;
		try {
			Query query = session.createQuery(hql);
			query.setParameter("studentId", student.getStudentId());
			list = query.list();
			if (list.size() == 0) {
				System.out.println("������IDΪ��" + student.getStudentId() + ",��ִ�в������");
				isExits = false;
			} else {
				System.out.println("���д���IDΪ��" + student.getStudentId());
				isExits = true;
			}
		} catch (Exception e) {
			System.out.println("��ѯ�쳣\n" + e.getMessage());
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return isExits;
	}

	public Boolean updateStudent(String hql, Student student) {
		boolean isUpdate = false;
		int updateNum = 0;

		session = HibernateSessionFactory.getSession();
		Transaction trans = session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setParameter("studentName", student.getStudentName());
		query.setParameter("age", student.getAge());
		query.setParameter("deptId", student.getDeptId());
		query.setParameter("studentId", student.getStudentId());
		try {
			updateNum = query.executeUpdate();// ��������
			trans.commit();
			if (updateNum > 0) {
				System.out.println("���³ɹ�������" + updateNum + "����¼");
				isUpdate = true;
			}else{
				System.out.println("����ʧ��");
			}
		} catch (Exception e) {
			System.out.println("�����쳣\n" + e.getMessage());
		} finally {
			HibernateSessionFactory.closeSession();
		}

		return isUpdate;
	}
}
