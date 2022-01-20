<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*,com.onlineExam.school.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
</head>
<body>
	<script type="text/javascript">
	<%
		String newsection = request.getParameter("section");
		String errorMessage = null;
		try{
			SectionUtil sec = new SectionUtil();
			sec.createSection(newsection);
		}catch(Exception ex){
			errorMessage = ex.getMessage();
			ex.printStackTrace();
		}
		if (errorMessage == null) {
			%>window.location.href = "./sectionCreation.jsp";<%
		}
		else{
			%>alert('<%=errorMessage%>')
			window.location.href = "./sectionCreation.jsp";<%
		}
%>
	window.location.href = "./sectionCreation.jsp";
</script>
</body>
</html>
