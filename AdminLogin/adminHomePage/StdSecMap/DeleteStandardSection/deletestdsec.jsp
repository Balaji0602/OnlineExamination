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
	String stdSecId = request.getParameter("stdsecId");

	StandardSectionMapping stdsec = new StandardSectionMapping();
	stdsec.deleteStandardSection(stdSecId);
%>

<script type="text/javascript">
	alert('Deleted Sucessfully');
	window.location.href =  "./../CreateStandardSection/StdSecMap.jsp";
</script>
</body>
</html>