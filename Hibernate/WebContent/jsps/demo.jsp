<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>利用Hibernate进行单表的增删改查</title>
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

		//ajax提交  
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
			<td>学号</td>
			<td>姓名</td>
			<td>年龄</td>
			<td>学院编号</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${list}" var="student">
			<tr>
				<td>${student.studentId}</td>
				<td>${student.studentName}</td>
				<td>${student.age}</td>
				<td>${student.deptId}</td>
				<td><a
					href="<c:url value='/DemoJdbcServlet?msg=delStudent&studentId=${student.studentId}'/>">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>

	<h3>添加一个学生信息</h3>
	<form action="<c:url value='/DemoJdbcServlet?msg=addStudents'/>"
		method="post">
		<table>
			<tr>
				<td>学号<font color="red">*</font></td>
				<td><input type="text" name="studentId"></td>
			</tr>
			<tr>
				<td>姓名<font color="red">*</font></td>
				<td><input type="text" name="studentName"></td>
			</tr>
			<tr>
				<td>年龄<font color="red">*</font></td>
				<td><input type="text" name="age"></td>
			</tr>
			<tr>
				<td>学院编号<font color="red">*</font></td>
				<td><input type="text" name="deptId"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="添加/修改"></td>
			</tr>
		</table>
	</form>


	<hr />
	<h3>学生查询</h3>
	<table>
		<tr>
			<td>学号</td>
			<td><input type="text" name="studentId"></td>
		</tr>
		<tr>
			<td>姓名</td>
			<td><input type="text" name="studentName"></td>
		</tr>
		<tr>
			<td>学院编号</td>
			<td><input type="text" name="deptId"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="button"
				onclick="query();" value="查询"></td>
		</tr>
	</table>

	<c:if test="${!empty sessionScope.qlist }">
		<h3>查询结果</h3>
		<table>
			<tr>
				<td>学号</td>
				<td>姓名</td>
				<td>年龄</td>
				<td>学院编号</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${qlist}" var="student">
				<tr>
					<td>${student.studentId}</td>
					<td>${student.studentName}</td>
					<td>${student.age}</td>
					<td>${student.deptId}</td>
					<td><a
						href="<c:url value='/DemoJdbcServlet?msg=delStudent&studentId=${student.studentId}'/>">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>


</body>
</html>
