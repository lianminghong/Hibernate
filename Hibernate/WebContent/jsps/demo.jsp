<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>����Hibernate���е������ɾ�Ĳ�</title>
<style type="text/css">
table {
	border: 1px solid gray;
	border-collapse: collapse;
	width: 60%;
}

td {
	border: 1px solid gray;
	padding: 5px;
}
</style>

<script type="text/javascript" src="<c:url value='/js/ajax.js'/>"></script>
<script type="text/javascript">
	var path = "<c:url value='/'/>";
</script>

<script type="text/javascript">
	function query() {
		var studentId = document.getElementsByName("studentId")[1].value;
		studentId = studentId.trim();
		var studentName = document.getElementsByName("studentName")[1].value;
		studentName = studentName.trim();
		var deptId = document.getElementsByName("deptId")[1].value;
		deptId = deptId.trim();

		//ajax�ύ  
		var ajax = new Ajax();
		var url = path + "/DemoJdbcServlet";
		var params = "msg=queryStudents&studentId=" + studentId + "&studentName="
				+ studentName + "&deptId=" + deptId;
		ajax.post(url, params, function(data) {
			if (data == "1") {
				//alert(data);  
				window.parent.window.location.href = path;
			}
		});

	}
</script>


</head>

<body>
	<table>
		<tr>
			<td>ѧ��</td>
			<td>����</td>
			<td>����</td>
			<td>ѧԺ���</td>
			<td>����</td>
		</tr>
		<c:forEach items="${list}" var="student">
			<tr>
				<td>${student.studentId}</td>
				<td>${student.studentName}</td>
				<td>${student.age}</td>
				<td>${student.deptId}</td>
				<td><a
					href="<c:url value='/DemoJdbcServlet?msg=delStudent&studentId=${student.studentId}'/>">ɾ��</a>
				</td>
			</tr>
		</c:forEach>
	</table>

	<h3>���һ��ѧ����Ϣ</h3>
	<form action="<c:url value='/DemoJdbcServlet?msg=addStudents'/>"
		method="post">
		<table>
			<tr>
				<td>ѧ��<font color="red">*</font></td>
				<td><input type="text" name="studentId"></td>
			</tr>
			<tr>
				<td>����<font color="red">*</font></td>
				<td><input type="text" name="studentName"></td>
			</tr>
			<tr>
				<td>����<font color="red">*</font></td>
				<td><input type="text" name="age"></td>
			</tr>
			<tr>
				<td>ѧԺ���<font color="red">*</font></td>
				<td><input type="text" name="deptId"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="���/�޸�"></td>
			</tr>
		</table>
	</form>


	<hr />
	<h3>ѧ����ѯ</h3>
	<table>
		<tr>
			<td>ѧ��</td>
			<td><input type="text" name="studentId"></td>
		</tr>
		<tr>
			<td>����</td>
			<td><input type="text" name="studentName"></td>
		</tr>
		<tr>
			<td>ѧԺ���</td>
			<td><input type="text" name="deptId"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="button"
				onclick="query();" value="��ѯ"></td>
		</tr>
	</table>

	<c:if test="${!empty sessionScope.qlist }">
		<h3>��ѯ���</h3>
		<table>
			<tr>
				<td>ѧ��</td>
				<td>����</td>
				<td>����</td>
				<td>ѧԺ���</td>
				<td>����</td>
			</tr>
			<c:forEach items="${qlist}" var="student">
				<tr>
					<td>${student.studentId}</td>
					<td>${student.studentName}</td>
					<td>${student.age}</td>
					<td>${student.deptId}</td>
					<td><a
						href="<c:url value='/DemoJdbcServlet?msg=delStudent&studentId=${student.studentId}'/>">ɾ��</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>


</body>
</html>
