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
	int standardId = Integer.parseInt(request.getParameter("standardId"));
	String standard = request.getParameter("standard");

	String errorMessage = null;
	try{
	StandardUtil deleteObject = new StandardUtil();
	Standard std = new Standard(); 
	std.setStandardId(standardId);
	std.setStandardName(standard);

	deleteObject.deleteStandard(std);
	}catch(Exception ex){
		errorMessage = ex.getMessage();
		ex.printStackTrace();
	}

	if (errorMessage == null) {
		%><script type="text/javascript">
			alert('Sucessfully Deleted');
			window.location.href = "./../StandardCreation/standardCreationFE.jsp";
		</script><%
	}
	else{
	%><script type="text/javascript">
		alert('<%=errorMessage%>');
		window.location.href = "./../StandardCreation/standardCreationFE.jsp";
	</script><%
	}
%>
</body>
</html>