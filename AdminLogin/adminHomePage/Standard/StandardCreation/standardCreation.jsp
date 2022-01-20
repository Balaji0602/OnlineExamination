<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*,com.onlineExam.school.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
</head>
<body>
	<script type="text/javascript">
		<%
			String standard = request.getParameter("std");
			
			if (standard == null) {
				%>window.location.href = "./standardCreationFE.jsp";<%
			}
			else{
				String errorMessage = null;
				try {
					StandardUtil std = new StandardUtil();
					std.createStandard(standard);
				} catch (Exception ex){
					errorMessage = ex.getMessage();
					ex.printStackTrace();
				}
					if (errorMessage == null) {
						%>window.location.href = "./standardCreationFE.jsp";<%
					}
					else{
					%>
					alert('<%=errorMessage%>');
					window.location.href = "./standardCreationFE.jsp";
				<%
				}
				%>
				window.location.href = "./standardCreationFE.jsp";
			<%
			}
			%>
	</script>
</body>
</html>