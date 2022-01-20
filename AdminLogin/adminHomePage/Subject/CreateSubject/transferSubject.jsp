<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*,com.onlineExam.school.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title></title>
</head>
<body>
	<%
		String subject = request.getParameter("subject");

		if (subject == null) {
		%>
			<script type="text/javascript">
				window.location.href="./createSubject.jsp";
			</script>
		<%
		}
		else{
			String errorMessage = null;
			try{
				SubjectUtil sub = new SubjectUtil();
				sub.createSubject(subject);
			}catch(Exception ex){
				errorMessage = ex.getMessage();
				ex.printStackTrace();
			}
			if(errorMessage == null){
			%>
			<script type="text/javascript">
				window.location.href = "./createSubject.jsp";
			</script>
			<%
			}
			else{
			%><script type="text/javascript">
				alert('<%=errorMessage%>');
				window.location.href = "./createSubject.jsp";
			</script> 
			<%
			}
			%>
			<script type="text/javascript">
				window.location.href = "./createSubject.jsp";
			</script>
			<%
		}
	%>
</body>
</html>