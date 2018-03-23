package cn.ccc.demo.servlet;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.activation.URLDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ccc.demo.service.DemoJdbcServiceImpl;
import cn.ccc.domain.PageBean;
import cn.ccc.domain.Student;
import cn.ccc.utils.BaseServlet;
import net.sf.json.JSONArray;
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
		response.setContentType("text/plain;charset=utf-8");//解决前端接收中文乱码
		response.setCharacterEncoding("utf-8");//解决前端接收中文乱码
		
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		//分页查询
		List<Student> list = impl.queryAllStudents(pageBean);
		//统计总数
		int total = impl.queryStudentCount();
		
		PrintWriter out = response.getWriter();
		JSONArray jb = JSONArray.fromObject(list);
		JSONObject result = new JSONObject();
		result.put("rows", jb);
		result.put("total", total);
		out.print(result);
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
