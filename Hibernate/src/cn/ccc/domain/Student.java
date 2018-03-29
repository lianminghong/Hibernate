package cn.ccc.domain;

public class Student {
	private int Id;
	private Integer studentId;
	private String studentName;
	private int age;
	private String deptId;

	public Student(Integer studentId, String studentName, int age, String deptId) {
		// super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.age = age;
		this.deptId = deptId;
	}

	public Student() {

	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

//	@Override
//	public String toString() {
//		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", age=" + age + ", deptId="
//				+ deptId + "]";
//	}

}
