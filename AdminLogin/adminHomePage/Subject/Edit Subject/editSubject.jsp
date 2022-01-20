<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*,com.onlineExam.school.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Edit Standard</title>
</head>
<body>
	<h3>Edit Subject</h3>
	<div class="editSubject">
		
	<%
	String subjectId = request.getParameter("subjectId");
	String subject = request.getParameter("subject");
	if (subjectId == null) {
		%><script type="text/javascript">
			window.location.href="./createSubject";
		</script><%
	}
	else if (subject == null) {
		%><script type="text/javascript">
			window.location.href="./createSubject";
		</script><%	
	}
	else{
	%>
		<form action="updateSubject.jsp" method="POST">
			<h4>Subject Id : <%=subjectId%></h4>
			<p>Subject</p>
			<input type="hidden" name="subjectId" value="<%=subjectId%>">
			<input type="text" name="subject" value="<%=subject%>">
			<input type="submit" value="Change" onclick="editSubject();">
			<input type="button" value="Back" onclick="editBack();">
		</form>
	</div>
	<script type="text/javascript">
		function editSubject() {
			window.location.href = "./updateSubject.jsp";
		}
		function editBack() {
			window.location.href = "./../CreateSubject/createSubject.jsp";
		}
	</script>
	<%
	}
	%>
</body>
</html>