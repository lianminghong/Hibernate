package cn.ccc.demo.servlet;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.ccc.demo.service.DemoJdbcServiceImpl;
import cn.ccc.domain.Student;
import cn.ccc.utils.BaseServlet;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class DemoJdbcServlet
 */
public class DemoJdbcServlet extends BaseServlet {
	DemoJdbcServiceImpl impl = new DemoJdbcServiceImpl();

	/**
	 * 默认执行全表查询
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Student> list = impl.queryAllStudents();
		request.getSession().setAttribute("list", list);
//		request.getRequestDispatcher("/jsps/demo.jsp").forward(request, response);
//		request.getRequestDispatcher("/demo/htmls/demo.html").forward(request, response);
		PrintWriter out = response.getWriter();
		
//		out.print();
		out.flush();
		out.close();
	}

	/**
	 * 根据条件查询
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void queryStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String studentId = request.getParameter("studentId");
		String studentName = request.getParameter("studentName");
		Integer age = Integer.valueOf(request.getParameter("age"));
		String deptId = request.getParameter("deptId");

		Student student = new Student();
		student.setStudentId(studentId);
		student.setStudentName(studentName);
		student.setAge(age);
		student.setDeptId(deptId);

		List<Student> list = impl.queryStudents(student);
		request.setAttribute("list", list);
		PrintWriter out = response.getWriter();
		out.print("1");
	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String studentId = request.getParameter("studentId");
		String studentName = request.getParameter("studentName");
		Integer age = Integer.valueOf(request.getParameter("age"));
		String deptId = request.getParameter("deptId");

		Student student = new Student();
		student.setStudentId(studentId);
		student.setStudentName(studentName);
		student.setAge(age);
		student.setDeptId(deptId);

		impl.addStudents(student);
		response.sendRedirect(getServletContext().getContextPath());
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void delStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String studentId = request.getParameter("studentId");

		Student student = new Student();
		student.setStudentId(studentId);

		impl.delStudent(student);
/*		response.sendRedirect(getServletContext().getContextPath());*/
		request.getRequestDispatcher("/jsps/demo.jsp").forward(request, response);
	}
	
}
