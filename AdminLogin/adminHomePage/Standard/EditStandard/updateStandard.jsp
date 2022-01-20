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
	String newStandard = request.getParameter("standardName");
	int standardId =  Integer.parseInt(request.getParameter("standardId"));
	String errorMessage = null;
	try{
		StandardUtil std = new StandardUtil();
 		Standard standard = new Standard();

		standard.setStandardName(newStandard);
		standard.setStandardId(standardId);

		std.editStandard(standard);
	}catch(Exception ex){
		errorMessage = ex.getMessage();
		ex.printStackTrace();
	}
%>

<script type="text/javascript">
	<%if (errorMessage == null) {%>
	alert('Standard Changed Sucessfully');
	window.location.href = "./../StandardCreation/standardCreationFE.jsp";
	<%}
	else{%>
		alert('<%=errorMessage%>');
	<%}%>
</script>
</body>
</html>