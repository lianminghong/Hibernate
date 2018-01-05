package cn.hncu.hib;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class HibernateSessionFactory {
	private static String configFile = "/hibernate.cfg.xml";
	private static Configuration config = new Configuration();

	// ����sessionFactory
	private static SessionFactory sessionFactroy = null;

	// �����ֲ߳̾�����t ��������hibernate��session
	private static final ThreadLocal<Session> t = new ThreadLocal<Session>();

	static {
		try {
			config.configure(configFile);
			sessionFactroy = config.buildSessionFactory();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ��ǰ�߳��е�Session
	 * @return session
	 */
	public static Session getSession() {
		Session session = t.get();
		// ���sessionû�д�,���¿�һ��session
		if (session == null || !session.isOpen()) {
			if (sessionFactroy == null) {
				rebuildSessionFactory();
			}
			session = (sessionFactroy != null) ? sessionFactroy.openSession() : null;
			t.set(session);// ���´򿪵�session���浽�ֲ߳̾�������
		}

		return session;
	}

	/**
	 * ���´�sessionFactory
	 */
	private static void rebuildSessionFactory() {
		try {
			config.configure(configFile);
			sessionFactroy = config.buildSessionFactory();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �ر����ݿ�����
	 * @throws HibernateException
	 */
	public static void closeSession() throws HibernateException{
		Session session = t.get();
		t.set(null);
		if(session != null){
			session.close();
		}
	}

}
