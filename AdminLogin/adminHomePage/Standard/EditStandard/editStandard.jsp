<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*,com.onlineExam.school.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Edit Standard</title>
</head>
<body>
	<h3>Edit Your Standard Here</h3>
	<div class="editStandard">
	<%
		String standardId = request.getParameter("standardId");
		String standardName = request.getParameter("standard");
	%>
		<form action="updateStandard.jsp" method="POST">
			<h4>Standard Id : <%=standardId%></h4>
			<p>Standard</p>
			<input type="hidden" name="standardId" value="<%=standardId%>">
			<input type="text" name="standardName" value="<%=standardName%>">
			<input type="submit" name="" value="Change" onclick="editStandard();">
			<input type="button" name="back" value="Back" onclick="editBack();">
		</form>
	</div>
	<script type="text/javascript">
		function editStandard() {
			window.location.href = "./updateStandard.jsp";
		}
		function editBack() {
			window.location.href = "./../StandardCreation/standardCreation.jsp";
		}
	</script>
</body>
</html>