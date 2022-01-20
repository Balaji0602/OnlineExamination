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
		int sectionId = Integer.parseInt(request.getParameter("sectionId"));
		String sectionName = request.getParameter("section");
		String errorMessage = null;

		try{
		SectionUtil sec = new SectionUtil();
		Section section = new Section();

		section.setSectionId(sectionId);
		section.setSectionName(sectionName);

		sec.editSection(section);
		}catch(Exception ex){
			errorMessage = ex.getMessage();
			ex.printStackTrace();
		}%>
		<script type="text/javascript"><%

		if(errorMessage == null) {
			%>alert('Section Changed Sucessfully');
			window.location.href = "../SectionCreation/sectionCreation.jsp";<%
		}
		else{
			%>alert('<%=errorMessage%>');
			window.location.href = "../SectionCreation/sectionCreation.jsp";<%
		}
	%>
	
		
	</script>
</body>
</html>