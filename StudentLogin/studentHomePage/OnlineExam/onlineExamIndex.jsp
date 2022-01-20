<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*,com.onlineExam.school.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Online Exam</title>
	<script type="text/javascript">
		function validateform() {

		}
	</script>
</head>
<body>
	<h1>ZYXW Higher Secondary School</h1>
	<h2>Online Exam</h2>

	<select>
		<option id="-1">Select Standard</option>
		<% 
		List<Standard> standards = StandardUtil.showStandard();
		for(int i = 0;i<standards.size(); i++){
			Standard standard = standards.get(i);
	%>
		<option id="<%=standard.getStandardId()%>"><%=standard.getStandardName()%></option>
	<%
	}
	%>
	</select>
	<select>	
	<option id="-1">Select Section</option>
	<%
	List<Section> sections = SectionUtil.showSection();
	for (int i=0;i<sections.size();i++) {
		Section section = sections.get(i);
	%>
	<option id="<%=section.getSectionId()%>"><%=section.getSectionName()%></option>
	<%
	}
	%>
	</select>
	<select>
	<option id="-1">Select Subject</option>
	<%
	List<Subject> subjects = SubjectUtil.showSubject();
	for(int i=0;i<subjects.size();i++){
		Subject subject = subjects.get(i);
	%>
	<option id="<%=subject.getSubjectId()%>"><%=subject.getSubjectName()%></option>
	<%
	}
	%>
	</select>
	<input type="button" value="Start Exam" onclick="validateform();">
</body>
</html>