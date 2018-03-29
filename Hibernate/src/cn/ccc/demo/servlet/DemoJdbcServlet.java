package cn.ccc.demo.servlet;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.activation.URLDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ccc.demo.service.impl.DemoJdbcServiceImpl;
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
		// response.setContentType("text/plain;charset=utf-8");// 解决前端接收中文乱码

		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		// 分页查询
		List<Student> list = impl.queryAllStudents(pageBean);
		// 统计总数
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
		student.setStudentId(Integer.parseInt(studentId));
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
		response.setContentType("text/plain;charset=utf-8");// 解决前端接收中文乱码

		PrintWriter out = response.getWriter();
		JSONObject result = new JSONObject();

		String studentId = request.getParameter("studentId");
		String studentName = request.getParameter("studentName");
		String age = request.getParameter("age");
		String deptId = request.getParameter("deptId");

		Student student = new Student();
		student.setStudentId(Integer.parseInt(studentId));

		// 将插入的学号进行查询是否存在重复
		Boolean isStudentIdExist = impl.queryExistStudentId(student);

		// 如无重复studentId，便继续做添加操作
		if (!isStudentIdExist) {
			student.setStudentName(studentName);
			student.setAge(Integer.parseInt(age));
			student.setDeptId(deptId);

			Boolean isSuc = impl.addStudents(student);// 返回true表示插入成功，false插入失败
			if (isSuc) {
				result.put("success", true);
				// out.print("1");
			} else {
				result.put("success", false);
				result.put("errorMsg", "保存失败");
				// out.print("2");
			}
		} else {
			result.put("success", false);
			result.put("errorMsg", "插入学号重复，请重新输入");
		}

		out.print(result);
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
	public void delStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ids = request.getParameter("studentIds");
		boolean isDel = false;
		int delNum = 0;

		PrintWriter out = response.getWriter();
		JSONObject result = new JSONObject();

		// String[] arrIds = ids.split(",");
		if (ids != null && ids != "" && ids.trim().length() > 0) {
			if (ids.split(",").length == 1) {
				isDel = impl.delStudent(Integer.parseInt(ids));// 执行单条记录删除
				if (isDel) {
					result.put("success", true);
					result.put("sucMsg", "删除1条记录");
				} else {
					result.put("success", false);
					result.put("errorMsg", "删除失败，删除0行记录");
				}
			} else {
				delNum = impl.delStudents(ids);// 执行批量删除
				if (delNum > 0) {
					result.put("success", true);
					result.put("sucMsg", "删除" + delNum + "条记录");
				} else {
					result.put("success", false);
					result.put("errorMsg", "删除失败，删除0行记录");
				}
			}
		} else {
			result.put("success", false);
			result.put("errorMsg", "删除失败请求失败，获取不到要删除的行");
		}

		out.print(result);
		out.flush();
		out.close();
		// request.getRequestDispatcher("/jsps/demo.jsp").forward(request,
		// response);
	}
	
	/**
	 * 更新
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String studentId = request.getParameter("studentId");
		String studentName = request.getParameter("studentName");
		String age = request.getParameter("age");
		String deptId = request.getParameter("deptId");
		
		PrintWriter out = response.getWriter();
		JSONObject result = new JSONObject();
		
		Student student = new Student();
		student.setStudentId(Integer.parseInt(studentId));
		student.setStudentName(studentName);
		student.setAge(Integer.parseInt(age));
		student.setDeptId(deptId);
		
		boolean isUpdate = impl.updateStudent(student);
		/*上次codding*/
		
		out.print(result);
		out.flush();
		out.close();
		
		
	}

}
