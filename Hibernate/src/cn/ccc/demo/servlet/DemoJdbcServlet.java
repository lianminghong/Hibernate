package cn.ccc.demo.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ccc.demo.service.DemoJdbcServiceImpl;
import cn.ccc.utils.BaseServlet;

/**
 * Servlet implementation class DemoJdbcServlet
 */
public class DemoJdbcServlet extends BaseServlet {
	DemoJdbcServiceImpl impl = new DemoJdbcServiceImpl();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	}

}
