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
	 * Ĭ��ִ��ȫ���ѯ
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// response.setContentType("text/plain;charset=utf-8");// ���ǰ�˽�����������

		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		// ��ҳ��ѯ
		List<Student> list = impl.queryAllStudents(pageBean);
		// ͳ������
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
	 * ����������ѯ
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
	 * ���
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/plain;charset=utf-8");// ���ǰ�˽�����������

		PrintWriter out = response.getWriter();
		JSONObject result = new JSONObject();

		String studentId = request.getParameter("studentId");
		String studentName = request.getParameter("studentName");
		String age = request.getParameter("age");
		String deptId = request.getParameter("deptId");

		Student student = new Student();
		student.setStudentId(Integer.parseInt(studentId));

		// �������ѧ�Ž��в�ѯ�Ƿ�����ظ�
		Boolean isStudentIdExist = impl.queryExistStudentId(student);

		// �����ظ�studentId�����������Ӳ���
		if (!isStudentIdExist) {
			student.setStudentName(studentName);
			student.setAge(Integer.parseInt(age));
			student.setDeptId(deptId);

			Boolean isSuc = impl.addStudents(student);// ����true��ʾ����ɹ���false����ʧ��
			if (isSuc) {
				result.put("success", true);
				// out.print("1");
			} else {
				result.put("success", false);
				result.put("errorMsg", "����ʧ��");
				// out.print("2");
			}
		} else {
			result.put("success", false);
			result.put("errorMsg", "����ѧ���ظ�������������");
		}

		out.print(result);
		out.flush();
		out.close();
		// response.sendRedirect(getServletContext().getContextPath());
	}

	/**
	 * ɾ��
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
				isDel = impl.delStudent(Integer.parseInt(ids));// ִ�е�����¼ɾ��
				if (isDel) {
					result.put("success", true);
					result.put("sucMsg", "ɾ��1����¼");
				} else {
					result.put("success", false);
					result.put("errorMsg", "ɾ��ʧ�ܣ�ɾ��0�м�¼");
				}
			} else {
				delNum = impl.delStudents(ids);// ִ������ɾ��
				if (delNum > 0) {
					result.put("success", true);
					result.put("sucMsg", "ɾ��" + delNum + "����¼");
				} else {
					result.put("success", false);
					result.put("errorMsg", "ɾ��ʧ�ܣ�ɾ��0�м�¼");
				}
			}
		} else {
			result.put("success", false);
			result.put("errorMsg", "ɾ��ʧ������ʧ�ܣ���ȡ����Ҫɾ������");
		}

		out.print(result);
		out.flush();
		out.close();
		// request.getRequestDispatcher("/jsps/demo.jsp").forward(request,
		// response);
	}
	
	/**
	 * ����
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
		/*�ϴ�codding*/
		
		out.print(result);
		out.flush();
		out.close();
		
		
	}

}
