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
		String examinationMode = request.getParameter("examinationMode");
		String errorMessage = null;

		if (examinationMode == null  || examinationMode == "") {
			%>
			<script type="text/javascript">
				window.location.href = "./createExamination.jsp";
			</script>
			<%
		}
		else{
		try{
			ExaminationMode examMode = new ExaminationMode();
			examMode.createExaminationMode(examinationMode);
		}catch(Exception ex){
			errorMessage = ex.getMessage();
			ex.printStackTrace();
		}
		if (errorMessage == null) {
			%><script type="text/javascript">
				window.location.href = "./createExamination.jsp";
			</script><%	
		}
		else{
		%><script type="text/javascript">
			alert("<%=errorMessage%>");
			window.location.href = "./createExamination.jsp";			
		</script>
		<%
		}
	}
	%>

</body>
</html>