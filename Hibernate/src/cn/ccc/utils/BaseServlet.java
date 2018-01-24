package cn.ccc.utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String msg = request.getParameter("msg");
		// ���Ҫ���ʵķ���Ϊ�գ���Ĭ��ִ��execute����
		if (null == msg || msg.trim().equals("")) {
			msg = "execute";
		}

		Class c = this.getClass();
		try {
			Method method = c.getMethod(msg, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(c.newInstance(), request, response);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("û�д˷���" + e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("����ܷ�����һ��˽�еķ���" + e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("Ŀ�귽��ִ��ʧ��" + e.getMessage(), e);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	/**
	 * Ĭ��ִ��ȫ���ѯ
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
