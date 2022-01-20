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
	String standard = request.getParameter("std");
	String section = request.getParameter("sec");

	if (standard == null) {
		%><script type="text/javascript">window.location.href = "./StdSecMap.jsp";</script><%
	}
	if (section == null) {
		%><script type="text/javascript">window.location.href = "./StdSecMap.jsp";</script><%		
	}

	String errorMessage = null;
	try{
	StandardSectionMapping stdsecs = new StandardSectionMapping();

		StandardSection stdsec = new StandardSection();
			stdsec.setStandardId(standard);
			stdsec.setSectionId(section);
			stdsecs.createStandardSection(stdsec);
	}catch(Exception ex){
		errorMessage = ex.getMessage();
		ex.printStackTrace();
	}
	if(errorMessage == null){
	%><script type="text/javascript">window.location.href = "./StdSecMap.jsp";</script><%
	}
	else{
		%><script type="text/javascript">
			alert("<%=errorMessage%>");
			window.location.href = "./StdSecMap.jsp";
			</script>
		<%
	}
%>

</script>
</body>
</html>