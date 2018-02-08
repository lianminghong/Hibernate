package cn.ccc.demo.servlet;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ccc.demo.service.DemoJdbcServiceImpl;
import cn.ccc.domain.Student;
import cn.ccc.utils.BaseServlet;
import net.sf.json.JSONArray;

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
		// request.getSession().setAttribute("list", list);
		// request.getRequestDispatcher("/jsps/demo.jsp").forward(request,
		// response);
		// request.getRequestDispatcher("/demo/htmls/demo.html").forward(request,
		// response);
		PrintWriter out = response.getWriter();
		JSONArray jb = JSONArray.fromObject(list);
		out.print(jb);
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
		out.flush();
		out.close();
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
		String age = request.getParameter("age");
		String deptId = request.getParameter("deptId");

		Student student = new Student();
		student.setStudentId(studentId);
		student.setStudentName(studentName);
		student.setAge(Integer.parseInt(age));
		student.setDeptId(deptId);

		PrintWriter out = response.getWriter();
		Boolean isSuc = impl.addStudents(student);// 返回true表示插入成功，false插入失败
		if (isSuc) {
			out.print("1");
		} else {
			out.print("2");
		}
		out.flush();
		out.close();
		// response.sendRedirect(getServletContext().getContextPath());
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

		PrintWriter out = response.getWriter();
		Boolean isDel = impl.delStudent(student);
		if (isDel) {
			out.print("1");
		} else {
			out.print("2");
		}
		out.flush();
		out.close();
		// request.getRequestDispatcher("/jsps/demo.jsp").forward(request,
		// response);
	}

}
