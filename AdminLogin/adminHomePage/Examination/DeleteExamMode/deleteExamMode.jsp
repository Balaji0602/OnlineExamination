<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*,com.onlineExam.school.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
</head>
<body>
<%
	int deleteExamMode = Integer.parseInt(request.getParameter("examModeId"));
	String errorMessage = null;
	try{
	ExaminationMode examMode = new ExaminationMode(); 
	examMode.deleteExamMode(deleteExamMode);
	}catch(Exception ex){
		errorMessage = ex.getMessage();
		ex.printStackTrace();
	}
%>
<script type="text/javascript">
	window.location.href = "./../CreateExamination/createExamination.jsp";
</script>
</body>
</html>