<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*,com.onlineExam.school.*" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
</head>
<body>
	<h3>Edit The Section Here</h3>
	<div class="editSection">
	<%
		int sectionId = Integer.parseInt(request.getParameter("sectionId"));
		String section = request.getParameter("section");
	%>
	<form action="updateSection.jsp" method="post">
		<h4>Secction Id : <%=sectionId%></h4>
		<p>Section</p>
		<input type="hidden" name="sectionId" value="<%=sectionId%>">
		<input type="text" name="section" value="<%=section%>">
		<input type="submit" name="" value="Change" onclick="editSection();">
		<input type="button" name="" value="Back" onclick="backSection();">
	</form>
	</div>
<script type="text/javascript">
	function editSection() {
		window.location.href = "./updateSection.jsp";
	}
	function backSection() {
		window.location.href = "./../SectionCreation/sectionCreation.jsp";
	}
</script>

</body>
</html>