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
		String section = request.getParameter("section");
		String errorMessage = null;

		try{
		SectionUtil deleteObject = new SectionUtil();
		Section sec = new Section();
		sec.setSectionId(sectionId);
		sec.setSectionName(section);
		deleteObject.deleteSection(sec);
		} catch(Exception ex){
			errorMessage = ex.getMessage();
			ex.printStackTrace();
		}
		if(errorMessage == null){
		%><script type="text/javascript">
			window.location.href = "./../SectionCreation/sectionCreation.jsp";
		</script>
		<%
		}
		else{
		%><script type="text/javascript">
			alert('<%=errorMessage%>');
			window.location.href = "./../SectionCreation/sectionCreation.jsp";
		</script>
		<%
		}
	%>
</body>
</html>