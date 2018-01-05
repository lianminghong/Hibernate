package cn.hncu.hib;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class HibernateSessionFactory {
	private static String configFile = "/hibernate.cfg.xml";
	private static Configuration config = new Configuration();

	// 定义sessionFactory
	private static SessionFactory sessionFactroy = null;

	// 创建线程局部变量t 用来保存hibernate的session
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
	 * 获取当前线程中的Session
	 * @return session
	 */
	public static Session getSession() {
		Session session = t.get();
		// 如果session没有打开,则新开一个session
		if (session == null || !session.isOpen()) {
			if (sessionFactroy == null) {
				rebuildSessionFactory();
			}
			session = (sessionFactroy != null) ? sessionFactroy.openSession() : null;
			t.set(session);// 将新打开的session保存到线程局部变量中
		}

		return session;
	}

	/**
	 * 重新打开sessionFactory
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
	 * 关闭数据库连接
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
